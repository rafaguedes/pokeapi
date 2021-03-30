package br.com.yourapp.pokeapi.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PokemonSearchResponse implements Serializable {
    @SerializedName("count")
    public int photos;

    @SerializedName("next")
    public String next;

    @SerializedName("previous")
    public String previous;

    @SerializedName("results")
    public List<Pokemon> results;

    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
