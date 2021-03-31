package br.com.yourapp.pokeapi.screens.list.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.yourapp.pokeapi.R
import br.com.yourapp.pokeapi.interfaces.OnPokemonInfoLoad
import br.com.yourapp.pokeapi.models.Pokemon
import br.com.yourapp.pokeapi.models.PokemonInfo
import br.com.yourapp.pokeapi.screens.details.PokemonDetailsActivity
import com.squareup.picasso.Picasso


class PokemonListAdapter(private val context: Context, private val pokemonList: ArrayList<Pokemon?>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_list, parent, false)
        return PokemonHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val pokemonHolder = viewHolder as PokemonHolder
        val pokemon = pokemonList?.get(position)
        pokemonHolder.imageView.setImageResource(R.drawable.picture_placeholder)
        pokemon?.loadPokemonInfo(context, object : OnPokemonInfoLoad {
            override fun onLoad(pokemonInfo: PokemonInfo?) {
                pokemonHolder.txtName.text = pokemonInfo?.name!!.toUpperCase()
                Picasso.get()
                        .load(pokemonInfo.sprites!!.other!!.official_artwork!!.front_default)
                        .placeholder(R.drawable.picture_placeholder)
                        .error(R.drawable.error_placeholder)
                        .into(pokemonHolder.imageView)
            }
        })

        pokemonHolder.imageView.setOnClickListener { button: View? ->
            val intent = Intent(context, PokemonDetailsActivity::class.java)
            intent.putExtra("pokemon", pokemon)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation((context as Activity), pokemonHolder.imageView, "imageView")
            context.startActivity(intent, options.toBundle())
        }

    }

    override fun getItemCount(): Int {
        return pokemonList?.size!!
    }

    fun add(newPokemonList: List<Pokemon>) {
        for (pokemon in newPokemonList) {
            pokemonList?.add(pokemon)
        }
        notifyDataSetChanged()
    }

    inner class PokemonHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val txtName: TextView = itemView.findViewById(R.id.txtName)
    }

}