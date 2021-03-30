package br.com.yourapp.pokeapi.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Forms implements Serializable {
    @SerializedName("name")
    public String name;

    @SerializedName("url")
    public String url;
}
