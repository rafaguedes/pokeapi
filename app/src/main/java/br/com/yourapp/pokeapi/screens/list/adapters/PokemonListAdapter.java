package br.com.yourapp.pokeapi.screens.list.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.yourapp.pokeapi.R;
import br.com.yourapp.pokeapi.interfaces.OnImageClickedCallback;
import br.com.yourapp.pokeapi.models.Pokemon;

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

        View contactView = inflater.inflate(R.layout.item_gallery, parent, false);
        RecyclerView.ViewHolder viewHolder = new PokemonListAdapter.GalleryHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        GalleryHolder galleryViewHolder = (GalleryHolder) viewHolder;
        Pokemon pokemon = pokemonList.get(position);
        galleryViewHolder.imageView.setImageResource(R.drawable.picture_placeholder);

        pokemon.loadPokemonInfo(context, pokemonInfo -> {
            galleryViewHolder.txtName.setText(pokemonInfo.name.toUpperCase());
            Picasso.get()
                    .load(pokemonInfo.sprites.other.official_artwork.front_default)
                    .placeholder(R.drawable.picture_placeholder)
                    .error(R.drawable.error_placeholder)
                    .into(galleryViewHolder.imageView);
        });

        galleryViewHolder.imageView.setOnClickListener(button -> {

        });
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public void addPage(List<Pokemon> pokemonList) {
        this.pokemonList.addAll(pokemonList);
    }

    public class GalleryHolder extends RecyclerView.ViewHolder {
        public final ImageView imageView;
        public final TextView txtName;
        public GalleryHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageView);
            this.txtName = itemView.findViewById(R.id.txtName);
        }
    }
}
