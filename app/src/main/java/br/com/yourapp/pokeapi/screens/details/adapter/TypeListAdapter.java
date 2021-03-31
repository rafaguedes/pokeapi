package br.com.yourapp.pokeapi.screens.details.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.yourapp.pokeapi.R;
import br.com.yourapp.pokeapi.models.AbilityDetails;
import br.com.yourapp.pokeapi.models.PokemonInfo;

public class TypeListAdapter extends RecyclerView.Adapter {

    public ArrayList<PokemonInfo.Type> typesArrayList;
    private Context context;

    public TypeListAdapter(ArrayList<PokemonInfo.Type> typesArrayList, Context context) {
        this.typesArrayList = typesArrayList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_type, parent, false);
        TypeHolder holder = new TypeHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        TypeHolder holder = (TypeHolder) viewHolder;
        PokemonInfo.Type type  = typesArrayList.get(position);
        holder.txtName.setText(type.name);
    }

    @Override
    public int getItemCount() {
        return typesArrayList.size();
    }

    public class TypeHolder extends RecyclerView.ViewHolder {
        public final TextView txtName;
        public TypeHolder(View itemView) {
            super(itemView);
            this.txtName = itemView.findViewById(R.id.txtName);
        }
    }

    public void add(PokemonInfo.Type type){
        typesArrayList.add(type);
        notifyDataSetChanged();
    }
}
