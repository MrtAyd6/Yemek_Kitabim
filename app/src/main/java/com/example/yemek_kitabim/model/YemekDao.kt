package com.example.yemek_kitabim.model

import androidx.room.*

@Dao
interface YemekDao {
    @Insert
    suspend fun tarifEkle(yemek: Yemek)

    @Update
    suspend fun tarifGuncelle(yemek: Yemek)

    @Delete
    suspend fun tarifSil(yemek : Yemek)

    @Query("SELECT * FROM tarifler")
    suspend fun tumTarifleriGetir(): List<Yemek>

    @Query("SELECT * FROM tarifler WHERE id = :id")
    suspend fun tarifGetir(id: Int): Yemek
}