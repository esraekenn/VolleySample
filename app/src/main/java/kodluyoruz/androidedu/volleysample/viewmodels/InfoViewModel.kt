package kodluyoruz.androidedu.volleysample.viewmodels

import com.google.gson.annotations.SerializedName

/**
 * Created by Gökhan ÖZTÜRK
 * 9.04.2017
 * CodeProject.G@gmail.com
 */
class InfoViewModel {

    var name: String? = null
    var age: Int = 0

    @SerializedName("email")
    var e_mail: String? = null
}
