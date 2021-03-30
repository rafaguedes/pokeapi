package br.com.yourapp.pokeapi.screens.list;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import br.com.yourapp.pokeapi.R;
import br.com.yourapp.pokeapi.utils.ActivityUtils;
import br.com.yourapp.pokeapi.utils.Constants;

public class PokemonListActivity extends AppCompatActivity {

    private PokemonListFragment pokemonListFragment;
    private PokemonListPresenter pokemonListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);

        pokemonListFragment = (PokemonListFragment) getSupportFragmentManager().findFragmentById(R.id.frameContent);
        if (pokemonListFragment == null) {
            pokemonListFragment = PokemonListFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), pokemonListFragment, R.id.frameContent);
        }
        pokemonListPresenter = new PokemonListPresenter(pokemonListFragment);
    }

    @Override
    protected void onSaveInstanceState(Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putParcelable(Constants.BUNDLE_RECYCLER_STATE, pokemonListFragment.getLayoutManager().onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            pokemonListFragment.getLayoutManager().onRestoreInstanceState(savedInstanceState.getParcelable(Constants.BUNDLE_RECYCLER_STATE));
        }
    }

}
