package br.com.yourapp.pokeapi.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AbilityDetails implements Serializable {
    @SerializedName("name")
    public String name;
}
