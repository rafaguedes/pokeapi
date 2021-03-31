package br.com.yourapp.pokeapi.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Abilities : Serializable {
    @SerializedName("ability")
    var ability: Ability? = null

    @SerializedName("is_hidden")
    var is_hidden = false

    @SerializedName("slot")
    var slot = 0

    inner class Ability : Serializable {
        @SerializedName("name")
        var name: String? = null

        @SerializedName("url")
        var url: String? = null
    }
}