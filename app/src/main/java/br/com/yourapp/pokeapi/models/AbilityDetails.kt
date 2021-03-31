package br.com.yourapp.pokeapi.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class AbilityDetails : Serializable {
    @SerializedName("name")
    var name: String? = null
}