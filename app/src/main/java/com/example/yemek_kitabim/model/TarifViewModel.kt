package com.example.yemek_kitabim.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TarifViewModel(private val repository : TarifRepository) : ViewModel() {
    fun tarifEkle(yemek: Yemek) = viewModelScope.launch {
        repository.tarifEkle(yemek)
    }

    fun tumTarifleriYukle(callback: (List<Yemek>) -> Unit) = viewModelScope.launch {
        val liste = repository.tumTarifleriGetir()
        callback(liste)
    }
}