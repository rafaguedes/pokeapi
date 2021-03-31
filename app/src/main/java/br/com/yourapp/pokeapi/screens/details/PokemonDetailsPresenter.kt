package br.com.yourapp.pokeapi.screens.details

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.yourapp.pokeapi.di.component.DaggerPokeComponent
import br.com.yourapp.pokeapi.di.modules.ContextModule
import br.com.yourapp.pokeapi.models.Abilities
import br.com.yourapp.pokeapi.models.AbilityDetails
import br.com.yourapp.pokeapi.models.PokemonInfo
import br.com.yourapp.pokeapi.screens.details.adapter.AbilityListAdapter
import br.com.yourapp.pokeapi.screens.details.adapter.TypeListAdapter
import dagger.internal.Preconditions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class PokemonDetailsPresenter(pokemonDetailsFragment: PokemonDetailsFragment) : PokemonDetailsContract.Presenter {
    private val pokeViewDetailsView: PokemonDetailsContract.View

    companion object {
        private const val TAG = "PokemonDetailsPresenter"
    }

    init {
        pokeViewDetailsView = Preconditions.checkNotNull(pokemonDetailsFragment, "pokeViewDetailsView cannot be null!")
        pokeViewDetailsView.setCurrentPresenter(this)
    }

    override fun loadPokemonAbilities(abilities: List<Abilities?>?, abilitiesList: RecyclerView?, txtLoadingAbilities: TextView?) {
        val details = ArrayList<AbilityDetails>()
        val adapter: RecyclerView.Adapter<*> = AbilityListAdapter(details, pokeViewDetailsView.context())
        abilitiesList?.adapter = adapter

        for (ability in abilities!!) {
            val pokeAPI = DaggerPokeComponent.builder().contextModule(ContextModule(pokeViewDetailsView.context()!!)).build().service
            val call = pokeAPI?.getAbilityDetails(ability?.ability?.url)
            call?.enqueue(object : Callback<AbilityDetails?> {
                override fun onResponse(call: Call<AbilityDetails?>, response: Response<AbilityDetails?>) {
                    val abilityDetails = response.body()
                    if (abilityDetails != null) {
                        txtLoadingAbilities?.visibility = View.GONE
                        abilitiesList?.visibility = View.VISIBLE
                        val currentAdapter = adapter as AbilityListAdapter
                        currentAdapter.add(abilityDetails)
                    }
                }

                override fun onFailure(call: Call<AbilityDetails?>, t: Throwable) {
                    Log.e(TAG, "Retrofit error")
                    call.cancel()
                }
            })
        }
        val layout: RecyclerView.LayoutManager = LinearLayoutManager(pokeViewDetailsView.context(), LinearLayoutManager.VERTICAL, false)
        abilitiesList?.layoutManager = layout
    }

    override fun loadPokemonTypes(type: PokemonInfo.Type?, typesList: RecyclerView?, txtLoadingTypes: TextView?) {
        val typeArrayList = ArrayList<PokemonInfo.Type>()
        val adapter: RecyclerView.Adapter<*> = TypeListAdapter(typeArrayList, pokeViewDetailsView.context()!!)
        typesList?.adapter = adapter

        val pokeAPI = DaggerPokeComponent.builder().contextModule(ContextModule(pokeViewDetailsView.context()!!)).build().service
        val call = pokeAPI?.getType(type?.url)
        call?.enqueue(object : Callback<PokemonInfo.Type?> {
            override fun onResponse(call: Call<PokemonInfo.Type?>, response: Response<PokemonInfo.Type?>) {
                val type = response.body()
                if (type != null) {
                    txtLoadingTypes?.visibility = View.GONE
                    typesList?.visibility = View.VISIBLE
                    val currentAdapter = adapter as TypeListAdapter
                    currentAdapter.add(type)
                }
            }

            override fun onFailure(call: Call<PokemonInfo.Type?>, t: Throwable) {
                Log.e(TAG, "Retrofit error")
                call.cancel()
            }
        })
        val layout: RecyclerView.LayoutManager = LinearLayoutManager(pokeViewDetailsView.context(), LinearLayoutManager.VERTICAL, false)
        typesList?.layoutManager = layout
    }
}