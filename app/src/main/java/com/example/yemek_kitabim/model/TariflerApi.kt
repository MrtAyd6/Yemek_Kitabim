package com.example.yemek_kitabim.model

import retrofit2.Call
import retrofit2.http.*

interface TariflerApi {
    @GET("yemek_al.php")
    fun getTarifler(): Call<List<Tarif>>

    @FormUrlEncoded
    @POST("yemek_ekle.php")
    fun tarif_ekle(
        @Field("tur") tur : String,
        @Field("gorsel") gorsel : String,
        @Field("isim") isim: String,
        @Field("malzemeler") malzemeler: String,
        @Field("yapilis") yapilis: String
    ): Call<String>
}