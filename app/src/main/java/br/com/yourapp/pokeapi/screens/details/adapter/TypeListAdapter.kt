package br.com.yourapp.pokeapi.screens.details.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.yourapp.pokeapi.R
import br.com.yourapp.pokeapi.models.PokemonInfo
import java.util.*

class TypeListAdapter(var typesArrayList: ArrayList<PokemonInfo.Type>, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_type, parent, false)
        return TypeHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder = holder as TypeHolder?
        val type = typesArrayList[position]
        holder!!.txtName.text = type.name
    }

    override fun getItemCount(): Int {
        return typesArrayList.size
    }

    inner class TypeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtName)

    }

    fun add(type: PokemonInfo.Type) {
        typesArrayList.add(type)
        notifyDataSetChanged()
    }
}