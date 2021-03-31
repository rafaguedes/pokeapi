package br.com.yourapp.pokeapi.screens.details;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import br.com.yourapp.pokeapi.R;
import br.com.yourapp.pokeapi.utils.ActivityUtils;
import br.com.yourapp.pokeapi.utils.Constants;

public class PokemonDetailsActivity extends AppCompatActivity {

    private PokemonDetailsFragment pokemonDetailsFragment;
    private PokemonDetailsPresenter pokemonDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);

        pokemonDetailsFragment = (PokemonDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.frameContent);
        if (pokemonDetailsFragment == null) {
            pokemonDetailsFragment = PokemonDetailsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), pokemonDetailsFragment, R.id.frameContent);
        }
        pokemonDetailsPresenter = new PokemonDetailsPresenter(pokemonDetailsFragment);
    }

    @Override
    public void onBackPressed() {
        pokemonDetailsFragment.onBackPressed();
        super.onBackPressed();
    }

}
