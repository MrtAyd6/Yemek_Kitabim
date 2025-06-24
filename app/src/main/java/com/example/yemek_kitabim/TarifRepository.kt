package com.example.yemek_kitabim

class TarifRepository(private val dao: YemekDao) {
    suspend fun tarifEkle(yemek: Yemek) = dao.tarifEkle(yemek)
    suspend fun tarifSil(yemek: Yemek) = dao.tarifSil(yemek)
    suspend fun tarifGuncelle(yemek: Yemek) = dao.tarifGuncelle(yemek)
    suspend fun tumTarifleriGetir() = dao.tumTarifleriGetir()
}