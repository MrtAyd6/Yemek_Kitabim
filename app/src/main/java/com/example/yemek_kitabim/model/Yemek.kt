package com.example.yemek_kitabim.model

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "tarifler")
data class Yemek(
    @PrimaryKey(autoGenerate = true)val id: Int = 0,
    val tur : String,
    val gorsel : String,
    var isim : String,
    var malzemeler : String,
    var tarif : String
) : Serializable
