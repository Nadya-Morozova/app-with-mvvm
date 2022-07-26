package com.example.applicationwithmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.applicationwithmvvm.network.SharedPreferencesRepository

class MainViewModelFactory(private val pref: SharedPreferencesRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(pref) as T
    }

}