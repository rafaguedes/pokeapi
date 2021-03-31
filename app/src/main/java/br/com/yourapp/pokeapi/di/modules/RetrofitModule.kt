package br.com.yourapp.pokeapi.di.modules

import br.com.yourapp.pokeapi.di.api.PokeAPI
import br.com.yourapp.pokeapi.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [OkHttpClientModule::class, ContextModule::class])
class RetrofitModule {
    @Provides
    fun pokeApi(retrofit: Retrofit): PokeAPI {
        return retrofit.create(PokeAPI::class.java)
    }

    @Provides
    fun retrofit(okHttpClient: OkHttpClient?, gsonConverterFactory: GsonConverterFactory?): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.API_URL)
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

    @Provides
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder().setLenient()
        return gsonBuilder.create()
    }

    @Provides
    fun gsonConverterFactory(gson: Gson?): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }
}