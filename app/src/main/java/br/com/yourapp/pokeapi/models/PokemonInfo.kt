package br.com.yourapp.pokeapi.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PokemonInfo : Serializable {
    @SerializedName("abilities")
    var abilities: List<Abilities>? = null

    @SerializedName("base_experience")
    var base_experience: String? = null

    @SerializedName("forms")
    var forms: List<Forms>? = null

    @SerializedName("game_indices")
    var game_indices: List<GameIndex>? = null

    @SerializedName("height")
    var height = 0

    @SerializedName("weight")
    var weight = 0

    @SerializedName("id")
    var id = 0

    @SerializedName("id_default")
    var id_default = false

    @SerializedName("location_area_encounters")
    var location_area_encounters: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("order")
    var order = 0

    @SerializedName("sprites")
    var sprites: Sprites? = null

    inner class Sprites : Serializable {
        @SerializedName("back_default")
        var back_default: String? = null

        @SerializedName("back_female")
        var back_female: String? = null

        @SerializedName("back_shiny")
        var back_shiny: String? = null

        @SerializedName("back_shiny_female")
        var back_shiny_female: String? = null

        @SerializedName("front_default")
        var front_default: String? = null

        @SerializedName("front_female")
        var front_female: String? = null

        @SerializedName("front_shiny")
        var front_shiny: String? = null

        @SerializedName("front_shiny_female")
        var front_shiny_female: String? = null

        @SerializedName("other")
        var other: Other? = null
    }

    @SerializedName("types")
    var types: List<Types>? = null

    inner class Types : Serializable {
        @SerializedName("slot")
        var slot: String? = null

        @SerializedName("type")
        var type: Type? = null
    }

    inner class Type : Serializable {
        @SerializedName("name")
        var name: String? = null

        @SerializedName("url")
        var url: String? = null
    }

    inner class Other : Serializable {
        @SerializedName("dream_world")
        var dream_world: DreamWorld? = null

        @SerializedName("official-artwork")
        var official_artwork: OfficialArtwork? = null
    }

    inner class DreamWorld : Serializable {
        @SerializedName("front_default")
        var front_default: String? = null

        @SerializedName("front_female")
        var front_female: String? = null
    }

    inner class OfficialArtwork : Serializable {
        @SerializedName("front_default")
        var front_default: String? = null
    }
}