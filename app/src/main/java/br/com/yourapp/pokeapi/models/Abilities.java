package br.com.yourapp.pokeapi.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Abilities implements Serializable {
    @SerializedName("ability")
    public Ability ability;

    @SerializedName("is_hidden")
    public boolean is_hidden;

    @SerializedName("slot")
    public int slot;

    public class Ability implements Serializable {
        @SerializedName("name")
        public String name;

        @SerializedName("url")
        public String url;
    }
}
