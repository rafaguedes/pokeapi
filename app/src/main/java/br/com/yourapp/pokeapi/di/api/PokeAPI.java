package br.com.yourapp.pokeapi.di.api;

import br.com.yourapp.pokeapi.models.PokemonInfo;
import br.com.yourapp.pokeapi.models.PokemonSearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface PokeAPI {
    @GET("pokemon")
    Call<PokemonSearchResponse> getPokemonsWithLimit(@Query("limit") int limit, @Query("offset") int offset);

    @GET
    Call<PokemonInfo> getPokemonInfo(@Url String url);
}
