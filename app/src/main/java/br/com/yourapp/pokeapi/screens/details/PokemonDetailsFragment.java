package br.com.yourapp.pokeapi.screens.details;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import br.com.yourapp.pokeapi.R;
import butterknife.BindView;
import butterknife.ButterKnife;

import static dagger.internal.Preconditions.checkNotNull;

public class PokemonDetailsFragment extends Fragment implements PokemonDetailsContract.View {

    @BindView(R.id.galleryList)
    public RecyclerView galleryList;

    @BindView(R.id.lottieAnimationView)
    public LottieAnimationView lottieAnimationView;

    public PokemonDetailsContract.Presenter presenter;

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

}
