package kodluyoruz.androidedu.volleysample.viewmodels

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Gökhan ÖZTÜRK
 * 9.04.2017
 * CodeProject.G@gmail.com
 */
class InfoViewModelJackson {

    var name: String? = null
    var age: Int = 0

    @JsonProperty("email")
    var e_mail: String? = null
}
