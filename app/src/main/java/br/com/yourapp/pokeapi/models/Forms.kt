package br.com.yourapp.pokeapi.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Forms : Serializable {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("url")
    var url: String? = null
}