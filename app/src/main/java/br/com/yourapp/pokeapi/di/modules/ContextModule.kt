package br.com.yourapp.pokeapi.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(var context: Context) {
    @Provides
    fun context(): Context {
        return context.applicationContext
    }

}