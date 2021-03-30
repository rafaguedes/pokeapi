package br.com.yourapp.pokeapi.di.component;

import br.com.yourapp.pokeapi.di.api.PokeAPI;
import br.com.yourapp.pokeapi.di.modules.RetrofitModule;
import dagger.Component;

@Component(modules = {RetrofitModule.class})
public interface PokeComponent {
    PokeAPI getService();
}
