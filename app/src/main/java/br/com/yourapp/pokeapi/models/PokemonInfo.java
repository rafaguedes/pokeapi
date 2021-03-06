package br.com.yourapp.pokeapi.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Types;
import java.util.List;

public class PokemonInfo implements Serializable {
    @SerializedName("abilities")
    public List<Abilities> abilities;

    @SerializedName("base_experience")
    public String base_experience;

    @SerializedName("forms")
    public List<Forms> forms;

    @SerializedName("game_indices")
    public List<GameIndex> game_indices;

    @SerializedName("height")
    public int height;

    @SerializedName("weight")
    public int weight;

    @SerializedName("id")
    public int id;

    @SerializedName("id_default")
    public boolean id_default;

    @SerializedName("location_area_encounters")
    public String location_area_encounters;

    @SerializedName("name")
    public String name;

    @SerializedName("order")
    public int order;

    @SerializedName("sprites")
    public Sprites sprites;

    public class Sprites implements Serializable {
        @SerializedName("back_default")
        public String back_default;

        @SerializedName("back_female")
        public String back_female;

        @SerializedName("back_shiny")
        public String back_shiny;

        @SerializedName("back_shiny_female")
        public String back_shiny_female;

        @SerializedName("front_default")
        public String front_default;

        @SerializedName("front_female")
        public String front_female;

        @SerializedName("front_shiny")
        public String front_shiny;

        @SerializedName("front_shiny_female")
        public String front_shiny_female;

        @SerializedName("other")
        public Other other;
    }

    @SerializedName("types")
    public List<Types> types;

    public class Types implements Serializable {
        @SerializedName("slot")
        public String slot;

        @SerializedName("type")
        public Type type;
    }

    public class Type implements Serializable {
        @SerializedName("name")
        public String name;

        @SerializedName("url")
        public String url;
    }

    public class Other implements Serializable {
        @SerializedName("dream_world")
        public DreamWorld dream_world;

        @SerializedName("official-artwork")
        public OfficialArtwork official_artwork;
    }

    public class DreamWorld implements Serializable {
        @SerializedName("front_default")
        public String front_default;

        @SerializedName("front_female")
        public String front_female;
    }

    public class OfficialArtwork implements Serializable {
        @SerializedName("front_default")
        public String front_default;
    }
}
