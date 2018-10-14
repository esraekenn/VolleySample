package kodluyoruz.androidedu.volleysample.volley

import android.app.Application
import android.text.TextUtils

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.Volley

/**
 * Created by Gökhan ÖZTÜRK
 * 8.04.2017
 * CodeProject.G@gmail.com
 */
class AppController : Application() {

    private var mRequestQueue: RequestQueue? = null
    private var mImageLoader: ImageLoader? = null

    //requesti kuyruga ekler. Asagidaki methodlar icinde kullanilir.
    val requestQueue: RequestQueue
        get() {
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(applicationContext)
            }

            return mRequestQueue!!
        }

    //image requestleri olusturmak icin ozel yapi.
    val imageLoader: ImageLoader
        get() {
            requestQueue
            if (mImageLoader == null) {
                mImageLoader = ImageLoader(this.mRequestQueue,
                        LruBitmapCache())
            }
            return this.mImageLoader!!
        }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    //requesti kuyruga eklemek icin kullanilacak. Iptal etmek istenirse tag parametresi verilir.
    fun <T> addToRequestQueue(req: Request<T>, tag: String) {

        //tag bos ise default tag degeri verilir.
        req.tag = if (TextUtils.isEmpty(tag)) TAG else tag
        requestQueue.add(req)
    }

    //Üstteki method ile aynı islem requesti kuyruga eklemek icin kullanilacak. Iptal etmek istenirse defaul tag verilir.
    fun <T> addToRequestQueue(req: Request<T>) {
        req.tag = TAG
        requestQueue.add(req)
    }

    //verilen tag'e gore requesti iptal eder. Activity olurse request otomatik iptal olur.
    fun cancelPendingRequests(tag: Any) {
        if (mRequestQueue != null) {
            mRequestQueue!!.cancelAll(tag)
        }
    }

    companion object {

        val TAG = AppController::class.java.simpleName

        //singleton pattern
        @get:Synchronized
        var instance: AppController? = null
            private set
    }
}