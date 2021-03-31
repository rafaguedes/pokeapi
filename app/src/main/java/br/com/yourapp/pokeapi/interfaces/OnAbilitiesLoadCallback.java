package br.com.yourapp.pokeapi.interfaces;

import java.util.ArrayList;

import br.com.yourapp.pokeapi.models.AbilityDetails;

public interface OnAbilitiesLoadCallback {
    void onLoad(ArrayList<AbilityDetails> abilities);
}
