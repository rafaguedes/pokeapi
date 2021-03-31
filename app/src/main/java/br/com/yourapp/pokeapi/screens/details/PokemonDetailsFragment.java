package br.com.yourapp.pokeapi.screens.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.yourapp.pokeapi.R;
import br.com.yourapp.pokeapi.interfaces.OnAbilitiesLoadCallback;
import br.com.yourapp.pokeapi.models.AbilityDetails;
import br.com.yourapp.pokeapi.models.Pokemon;
import br.com.yourapp.pokeapi.models.PokemonInfo;
import br.com.yourapp.pokeapi.screens.details.adapter.AbilityListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

import static dagger.internal.Preconditions.checkNotNull;

public class PokemonDetailsFragment extends Fragment implements PokemonDetailsContract.View {

    public PokemonDetailsContract.Presenter presenter;
    private Pokemon pokemon;

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.txtName)
    public TextView txtName;

    @BindView(R.id.txtHeight)
    public TextView txtHeight;

    @BindView(R.id.txtWeight)
    public TextView txtWeight;

    @BindView(R.id.txtOrder)
    public TextView txtOrder;

    @BindView(R.id.content)
    public LinearLayout content;

    @BindView(R.id.abilitiesList)
    public RecyclerView abilitiesList;

    @BindView(R.id.txtLoadingAbilities)
    public TextView txtLoadingAbilities;

    @BindView(R.id.typesList)
    public RecyclerView typesList;

    @BindView(R.id.txtLoadingTypes)
    public TextView txtLoadingTypes;

    Animation slideUp;
    Animation slideDown;

    public static PokemonDetailsFragment newInstance() {
        PokemonDetailsFragment pokemonDetailsFragment = new PokemonDetailsFragment();
        return pokemonDetailsFragment;
    }

    @Override
    public void setPresenter(PokemonDetailsContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        slideUp = AnimationUtils.loadAnimation(context(), R.anim.slide_up);
        slideDown = AnimationUtils.loadAnimation(context(), R.anim.slide_down);
        content.startAnimation(slideUp);

        Intent intent = getActivity().getIntent();
        pokemon = (Pokemon) intent.getSerializableExtra("pokemon");

        txtName.setText(pokemon.name.toUpperCase());
        txtHeight.setText(pokemon.pokemonInfo.height + " inches");
        txtWeight.setText(pokemon.pokemonInfo.weight + " lbs");
        txtOrder.setText(pokemon.pokemonInfo.order + "");

        Picasso.get()
                .load(pokemon.pokemonInfo.sprites.other.official_artwork.front_default)
                .placeholder(R.drawable.picture_placeholder)
                .error(R.drawable.error_placeholder)
                .into(imageView);

        presenter.loadPokemonAbilities(pokemon.pokemonInfo.abilities, abilitiesList, txtLoadingAbilities);

        for (PokemonInfo.Types type: pokemon.pokemonInfo.types) {
            presenter.loadPokemonTypes(type.type, typesList, txtLoadingTypes);
        }

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                content.startAnimation(slideDown);
                getActivity().supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        content.startAnimation(slideDown);
    }

}
