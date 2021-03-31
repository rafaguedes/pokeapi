package br.com.yourapp.pokeapi.screens.list.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.yourapp.pokeapi.R;
import br.com.yourapp.pokeapi.models.Pokemon;
import br.com.yourapp.pokeapi.screens.details.PokemonDetailsActivity;

public class PokemonListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Pokemon> pokemonList;
    private Context context;

    public PokemonListAdapter(Context context, ArrayList<Pokemon> pokemonList) {
        this.context = context;
        this.pokemonList = pokemonList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_list, parent, false);
        RecyclerView.ViewHolder viewHolder = new PokemonHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        PokemonHolder pokemonHolder = (PokemonHolder) viewHolder;
        Pokemon pokemon = pokemonList.get(position);
        pokemonHolder.imageView.setImageResource(R.drawable.picture_placeholder);

        pokemon.loadPokemonInfo(context, pokemonInfo -> {
            pokemonHolder.txtName.setText(pokemonInfo.name.toUpperCase());
            Picasso.get()
                    .load(pokemonInfo.sprites.other.official_artwork.front_default)
                    .placeholder(R.drawable.picture_placeholder)
                    .error(R.drawable.error_placeholder)
                    .into(pokemonHolder.imageView);
        });

        pokemonHolder.imageView.setOnClickListener(button -> {
            Intent intent = new Intent(context, PokemonDetailsActivity.class);
            intent.putExtra("pokemon", pokemon);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, pokemonHolder.imageView, "imageView");
            context.startActivity(intent, options.toBundle());
        });
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public void add(List<Pokemon> newPokemonList) {
        for (Pokemon pokemon: newPokemonList) {
            pokemonList.add(pokemon);
        }
        notifyDataSetChanged();
    }

    public class PokemonHolder extends RecyclerView.ViewHolder {
        public final ImageView imageView;
        public final TextView txtName;
        public PokemonHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageView);
            this.txtName = itemView.findViewById(R.id.txtName);
        }
    }
}
