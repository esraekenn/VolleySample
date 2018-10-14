package kodluyoruz.androidedu.volleysample

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.*
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.GsonBuilder
import kodluyoruz.androidedu.volleysample.viewmodels.InfoViewModel
import kodluyoruz.androidedu.volleysample.volley.AppController
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.util.*

class MainActivity : AppCompatActivity() {

    /*http://myjson.com//
     *https://www.w3schools.com/
     * http://json.parser.online.fr/
     *JSON (JavaScript Object Notation).
     * https://github.com/ihsanbal/LoggingInterceptor
     *
     * GET method'u kullanilirsa parametreleri url icerisinde gonderiyoruz. Basit yapıda parametre gonderilirken kullanilir. Link uzerinde gorulur.
     *
     * POST method'u kullanilirsa parametreleri getParams() icerisinde veriyoruz. Karmasik parametre gonderilirken kullanilir. Link uzerinde gorulmez.
     * Bilgi form'lar uzerinden gider. "Kayıt Formu" ekranlari gibi.
     *
     * PUT method'u kullanilirsa parametreleri yine getParams() icerisinde veriyoruz. Genelde server'a dosya gondermek icin kullanilir.
     * Gonderilen dosya server'da varsa yenisi ile degistirilir yoksa yenisi yaratilir.
     *
     * */

    private val TAG = "AndroidBootCamp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jsonObjectRequest()

        jsonArrayRequest()

        jsonStringRequest()
    }

    private fun jsonObjectRequest() {

        // daha sonra iptal etmek istenirse // activity ölürse otomatik istek iptal olur.
        val tag_json_obj = "AndroidBootCampJsonObjectRequest"

        //adsoyad, yas ve email bilgisi icerir.
        val url = "https://api.myjson.com/bins/x8yrj"

        val pDialog = ProgressDialog(this)
        pDialog.setMessage("Loading...")
        pDialog.show()

        val jsonObjReq = JsonObjectRequest(Request.Method.GET,
                url, null,
                Response.Listener { response ->
                    Log.e("Json Object : ", response.toString())
                    pDialog.hide()
                }, Response.ErrorListener { error ->
            VolleyLog.e(TAG, "Error: " + error.message)

            pDialog.hide()
        })

        // requesti kuyruga ekler.
        AppController.instance!!.addToRequestQueue(jsonObjReq, tag_json_obj)
    }

    private fun jsonArrayRequest() {

        // daha sonra iptal etmek istenirse // activity ölürse otomatik istek iptal olur.
        val tag_json_arry = "AndroidBootCampJsonArrayRequest"

        val url = "https://api.myjson.com/bins/16pyzz"

        val pDialog = ProgressDialog(this)
        pDialog.setMessage("Loading...")
        pDialog.show()

        val req = JsonArrayRequest(url,
                Response.Listener { response ->
                    Log.e("Json Array : ", response.toString())
                    pDialog.hide()
                }, Response.ErrorListener { error ->
            VolleyLog.e(TAG, "Error: " + error.message)
            pDialog.hide()
        })

        // requesti kuyruga ekler.
        AppController.instance!!.addToRequestQueue(req, tag_json_arry)
    }

    private fun jsonStringRequest() {

        // daha sonra iptal etmek istenirse // activity ölürse otomatik istek iptal olur.
        val tag_string_req = "AndroidBootCampJsonStringRequest"

        //object mi array mi oldugu farketmez.
        val url = "https://api.myjson.com/bins/x8yrj"
        val url2 = "https://api.myjson.com/bins/16pyzz"

        val pDialog = ProgressDialog(this)
        pDialog.setMessage("Loading...")
        pDialog.show()

        val strReq = object : StringRequest(Request.Method.GET,
                url, Response.Listener { response ->
            /*
                 * Gson Samples
                 * */
            val gsonBuilder = GsonBuilder()
            val gson = gsonBuilder.create()

            //object request cast islemi icin. // url
            val post = gson.fromJson<InfoViewModel>(response, InfoViewModel::class.java)
            Toast.makeText(this@MainActivity, post.name, Toast.LENGTH_SHORT).show()

            //array request cast islemi icin. // url2
            //List<InfoViewModel> posts = Arrays.asList(gson.fromJson(response, InfoViewModel[].class));
            //Toast.makeText(MainActivity.this, posts.get(0).getName(), Toast.LENGTH_SHORT).show();


            /*
                 * Jackson Samples
                 * */

            val mapper = ObjectMapper()

            //                try {
            //                    //object request cast islemi icin. // url
            ////                    InfoViewModelJackson jackson = mapper.readValue(response.toString(), InfoViewModelJackson.class);
            ////                    Toast.makeText(MainActivity.this, jackson.getName(), Toast.LENGTH_SHORT).show();
            //
            //                    //array request cast islemi icin. // url2
            ////                    List<InfoViewModelJackson> jackson = Arrays.asList(mapper.readValue(response.toString(), InfoViewModelJackson[].class));
            ////                    Toast.makeText(MainActivity.this, jackson.get(0).getName(), Toast.LENGTH_SHORT).show();
            //
            //                } catch (IOException e) {
            //                    e.printStackTrace();
            //                }

            Log.e("Json String : ", response.toString())
            pDialog.hide()
        }, Response.ErrorListener { error ->
            VolleyLog.e(TAG, "Error: " + error.message)
            pDialog.hide()
        }) {

            //encoding ayarlamak icin yazilmali. Silip deneyelim bir de.
            override fun parseNetworkResponse(
                    response: NetworkResponse): Response<String> {

                val parsed = try {
                    String(response.data, Charset.forName("UTF-8"))
                } catch (e: UnsupportedEncodingException) {
                    String(response.data)
                }

                return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response))
            }
        }

        // requesti kuyruga ekler.
        AppController.instance!!.addToRequestQueue(strReq, tag_string_req)
    }

    private fun paramRequest() {

        // daha sonra iptal etmek istenirse // activity ölürse otomatik istek iptal olur.
        val tag_json_obj = "AndroidBootCampJsonObjectParamRequest"

        val url = "http://api.androidhive.info/volley/person_object.json"

        val pDialog = ProgressDialog(this)
        pDialog.setMessage("Loading...")
        pDialog.show()

        val jsonObjReq = object : JsonObjectRequest(Request.Method.POST,
                url, null,
                Response.Listener { response ->
                    Log.e(TAG, response.toString())
                    pDialog.hide()
                }, Response.ErrorListener { error ->
            VolleyLog.e(TAG, "Error: " + error.message)
            pDialog.hide()
        }) {
            /**
             * name, email, password bilgileri parametre olarak veriliyor.
             */
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["name"] = "Gökhan ÖZTÜRK"
                params["email"] = "GokhanOZTURK@AndroidEdu.IO"
                params["password"] = "AndroidBootCamp"

                return params
            }

        }

        // requesti kuyruga ekler.
        AppController.instance!!.addToRequestQueue(jsonObjReq, tag_json_obj)
    }

    private fun headerRequest() {

        // daha sonra iptal etmek istenirse // activity ölürse otomatik istek iptal olur.
        val tag_json_obj = "AndroidBootCampJsonObjectHeaderRequest"

        val url = "http://api.androidhive.info/volley/person_object.json"

        val pDialog = ProgressDialog(this)
        pDialog.setMessage("Loading...")
        pDialog.show()

        val jsonObjReq = object : JsonObjectRequest(Request.Method.POST,
                url, null,
                Response.Listener { response ->
                    Log.e(TAG, response.toString())
                    pDialog.hide()
                }, Response.ErrorListener { error ->
            VolleyLog.e(TAG, "Error: " + error.message)
            pDialog.hide()
        }) {

            /**
             * api key header'a paslaniyor.
             */
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["apiKey"] = "xxxxxxxxxxxxxxx"
                return headers
            }

        }

        // requesti kuyruga ekler.
        AppController.instance!!.addToRequestQueue(jsonObjReq, tag_json_obj)
    }

    private fun requestPrioritization() {

        //onceliklendirme "Normal, Low, Immediate ve High" olarak kullanilabilir.
        val priority = Request.Priority.HIGH

        val url = "http://api.androidhive.info/volley/string_response.html"

        val strReq = object : StringRequest(Request.Method.GET,
                url, Response.Listener { response -> Log.e(TAG, response.toString()) }, Response.ErrorListener { error -> VolleyLog.e(TAG, "Error: " + error.message) }) {
            //onemli olan kisim.
            override fun getPriority(): Request.Priority {
                return priority
            }

        }
    }
}
