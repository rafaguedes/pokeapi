package br.com.yourapp.pokeapi.models

import android.content.Context
import br.com.yourapp.pokeapi.di.component.DaggerPokeComponent
import br.com.yourapp.pokeapi.di.modules.ContextModule
import br.com.yourapp.pokeapi.interfaces.OnPokemonInfoLoad
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class Pokemon : Serializable {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("url")
    var url: String? = null
    var pokemonInfo: PokemonInfo? = null
    fun loadPokemonInfo(context: Context?, onPokemonInfoLoad: OnPokemonInfoLoad) {
        val pokeAPI = DaggerPokeComponent.builder().contextModule(ContextModule(context!!)).build().service
        val call = pokeAPI!!.getPokemonInfo(url)
        call!!.enqueue(object : Callback<PokemonInfo?> {
            override fun onResponse(call: Call<PokemonInfo?>, response: Response<PokemonInfo?>) {
                pokemonInfo = response.body()
                onPokemonInfoLoad.onLoad(pokemonInfo)
            }

            override fun onFailure(call: Call<PokemonInfo?>, throwable: Throwable) {
                call.cancel()
            }
        })
    }
}