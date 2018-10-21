package kodluyoruz.androidedu.volleysample.entitiy

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty


// Code with ❤️
//┌─────────────────────────────┐
//│ Created by Gökhan ÖZTÜRK    │
//│ ─────────────────────────── │
//│ GokhanOzturk@AndroidEdu.IO  │            
//│ ─────────────────────────── │
//│ 20.10.2018 - 13:52          │
//└─────────────────────────────┘
@JsonIgnoreProperties(ignoreUnknown = true)
data class ProminentDTO(@JsonProperty("summary") val summary: String,
                        @JsonProperty("weight") val weight: Int,
                        @JsonProperty("updatedDate") val updatedDate: String,
                        @JsonProperty("title") val title: String,
                        @JsonProperty("url") val url: String,
                        @JsonProperty("seoLink") val seoLink: String,
                        @JsonProperty("id") val id: Int,
                        @JsonProperty("publishedDate") val publishedDate: String,
                        @JsonProperty("presentationType") val presentationType: String,
                        @JsonProperty("publishedDateFormatted") val publishedDateFormatted: String,
                        @JsonProperty("updatedDateFormatted") val updatedDate_Formatted: String,
                        @JsonProperty("status") val status: Int)