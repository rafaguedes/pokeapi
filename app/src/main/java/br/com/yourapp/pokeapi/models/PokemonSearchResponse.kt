package br.com.yourapp.pokeapi.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PokemonSearchResponse : Serializable {
    @SerializedName("count")
    var photos = 0

    @SerializedName("next")
    var next: String? = null

    @SerializedName("previous")
    var previous: String? = null

    @SerializedName("results")
    var results: List<Pokemon>? = null
    fun toJSON(): String {
        val gson = Gson()
        return gson.toJson(this)
    }
}