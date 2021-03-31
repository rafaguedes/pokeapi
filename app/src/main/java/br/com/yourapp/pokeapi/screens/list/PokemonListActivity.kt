package br.com.yourapp.pokeapi.screens.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import br.com.yourapp.pokeapi.R
import br.com.yourapp.pokeapi.screens.details.PokemonDetailsFragment.Companion.newInstance
import br.com.yourapp.pokeapi.utils.ActivityUtils.addFragmentToActivity
import br.com.yourapp.pokeapi.utils.Constants

class PokemonListActivity : AppCompatActivity() {
    private var pokemonListFragment: PokemonListFragment? = null
    private var pokemonListPresenter: PokemonListPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standard)

        pokemonListFragment = supportFragmentManager.findFragmentById(R.id.frameContent) as PokemonListFragment?
        if (pokemonListFragment == null) {
            pokemonListFragment = PokemonListFragment.newInstance()
            addFragmentToActivity(supportFragmentManager, pokemonListFragment!!, R.id.frameContent)
        }
        pokemonListPresenter = PokemonListPresenter(pokemonListFragment!!)
    }

    override fun onSaveInstanceState(instanceState: Bundle) {
        super.onSaveInstanceState(instanceState)
        instanceState.putParcelable(Constants.BUNDLE_RECYCLER_STATE, pokemonListFragment!!.getCurrentLayoutManager()!!.onSaveInstanceState())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            pokemonListFragment!!.getCurrentLayoutManager()!!.onRestoreInstanceState(savedInstanceState.getParcelable(Constants.BUNDLE_RECYCLER_STATE))
        }
    }
}