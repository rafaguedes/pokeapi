package br.com.yourapp.pokeapi.interfaces

import br.com.yourapp.pokeapi.models.AbilityDetails
import java.util.*

interface OnAbilitiesLoadCallback {
    fun onLoad(abilities: ArrayList<AbilityDetails?>?)
}