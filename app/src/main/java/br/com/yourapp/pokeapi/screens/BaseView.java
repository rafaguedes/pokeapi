package br.com.yourapp.pokeapi.screens;

import android.content.Context;

public interface BaseView<T> {
    Context context();
    void setPresenter(T presenter);
}
