package br.com.yourapp.pokeapi.screens

import android.content.Context

interface BaseView<T> {
    fun context(): Context?
    fun setCurrentPresenter(presenter: T)
}