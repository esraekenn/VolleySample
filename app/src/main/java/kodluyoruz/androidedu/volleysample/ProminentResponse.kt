package kodluyoruz.androidedu.volleysample

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kodluyoruz.androidedu.volleysample.entitiy.ProminentDTO


// Code with ❤️
//┌─────────────────────────────┐
//│ Created by Gökhan ÖZTÜRK    │
//│ ─────────────────────────── │
//│ GokhanOzturk@AndroidEdu.IO  │            
//│ ─────────────────────────── │
//│ 20.10.2018 - 13:51          │
//└─────────────────────────────┘
@JsonIgnoreProperties(ignoreUnknown = true)
data class ProminentResponse(@JsonProperty("data") val data: ArrayList<ProminentDTO>)