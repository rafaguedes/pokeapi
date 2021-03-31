package br.com.yourapp.pokeapi.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class GameIndex : Serializable {
    @SerializedName("game_index")
    var game_index: String? = null

    @SerializedName("url")
    var url: List<Version>? = null
}