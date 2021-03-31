package br.com.yourapp.pokeapi.di.api

import br.com.yourapp.pokeapi.models.AbilityDetails
import br.com.yourapp.pokeapi.models.PokemonInfo
import br.com.yourapp.pokeapi.models.PokemonSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokeAPI {
    @GET("pokemon")
    fun getPokemonsWithLimit(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<PokemonSearchResponse?>?

    @GET
    fun getPokemonInfo(@Url url: String?): Call<PokemonInfo?>?

    @GET
    fun getAbilityDetails(@Url url: String?): Call<AbilityDetails?>?

    @GET
    fun getType(@Url url: String?): Call<PokemonInfo.Type?>?

    @GET
    fun getPokemonsWithUrl(@Url url: String?): Call<PokemonSearchResponse?>?
}