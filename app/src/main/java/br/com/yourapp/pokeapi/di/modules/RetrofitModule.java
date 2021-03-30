package br.com.yourapp.pokeapi.di.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.yourapp.pokeapi.di.api.PokeAPI;
import br.com.yourapp.pokeapi.utils.Constants;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {OkHttpClientModule.class, ContextModule.class})
public class RetrofitModule {

    @Provides
    public PokeAPI pokeApi(Retrofit retrofit){
        return retrofit.create(PokeAPI.class);
    }

    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.API_URL)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder().setLenient();
        return gsonBuilder.create();
    }

    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }
}
