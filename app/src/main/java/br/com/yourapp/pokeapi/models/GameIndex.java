package br.com.yourapp.pokeapi.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GameIndex implements Serializable {
    @SerializedName("game_index")
    public String game_index;

    @SerializedName("url")
    public List<Version> url;
}
