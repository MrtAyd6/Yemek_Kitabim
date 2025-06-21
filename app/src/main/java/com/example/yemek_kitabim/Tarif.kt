package com.example.yemek_kitabim

import java.io.Serializable


data class Tarif(
    val tur : String,
    val gorsel: String,
    val isim: String,
    val malzemeler: String,
    val yapilis: String
    ) : Serializable
