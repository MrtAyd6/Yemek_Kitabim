package com.example.yemek_kitabim

import android.content.Context
import androidx.room.*

@Database(entities = [Yemek::class], version = 1)
abstract class TarifVeritabani : RoomDatabase(){
    abstract fun yemekDao(): YemekDao
    companion object{
        @Volatile private var INSTANCE : TarifVeritabani? = null
        fun getDatabase(context: Context) : TarifVeritabani{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TarifVeritabani::class.java,
                    "tarif_veritabani"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}