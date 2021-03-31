package br.com.yourapp.pokeapi.screens.details.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.yourapp.pokeapi.R
import br.com.yourapp.pokeapi.models.AbilityDetails
import java.util.*

class AbilityListAdapter(var abilityDetails: ArrayList<AbilityDetails>, private val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_ability, parent, false)
        return AbilityHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder = holder as AbilityListAdapter.AbilityHolder?
        val type = abilityDetails[position]
        holder!!.txtName.text = type.name
    }

    override fun getItemCount(): Int {
        return abilityDetails.size
    }

    inner class AbilityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtName)
    }

    fun add(ability: AbilityDetails) {
        abilityDetails.add(ability)
        notifyDataSetChanged()
    }

}