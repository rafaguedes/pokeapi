package br.com.yourapp.pokeapi.screens.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.yourapp.pokeapi.R
import br.com.yourapp.pokeapi.screens.details.PokemonDetailsFragment.Companion.newInstance
import br.com.yourapp.pokeapi.utils.ActivityUtils.addFragmentToActivity

class PokemonDetailsActivity : AppCompatActivity() {
    private var pokemonDetailsFragment: PokemonDetailsFragment? = null
    private var pokemonDetailsPresenter: PokemonDetailsPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standard)
        pokemonDetailsFragment = supportFragmentManager.findFragmentById(R.id.frameContent) as PokemonDetailsFragment?
        if (pokemonDetailsFragment == null) {
            pokemonDetailsFragment = newInstance()
            addFragmentToActivity(supportFragmentManager, pokemonDetailsFragment!!, R.id.frameContent)
        }
        pokemonDetailsPresenter = PokemonDetailsPresenter(pokemonDetailsFragment!!)
    }

    override fun onBackPressed() {
        pokemonDetailsFragment!!.onBackPressed()
        super.onBackPressed()
    }
}