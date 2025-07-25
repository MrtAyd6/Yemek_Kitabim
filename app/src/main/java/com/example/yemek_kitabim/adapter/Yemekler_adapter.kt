package com.example.yemek_kitabim.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.yemek_kitabim.databinding.YemekSatirBinding
import com.example.yemek_kitabim.model.Tarif
import com.example.yemek_kitabim.view.Yemek_ListesiDirections

class Yemekler_adapter(private val yemek_liste: ArrayList<Tarif>) : RecyclerView.Adapter<Yemekler_adapter.TarifHolder>() {
    class TarifHolder(val binding : YemekSatirBinding) : RecyclerView.ViewHolder(binding.root) {}
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TarifHolder {
        val binding = YemekSatirBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TarifHolder(binding)
    }

    override fun getItemCount(): Int {
        return yemek_liste.size
    }
    override fun onBindViewHolder(
        holder: TarifHolder,
        position: Int
    ) {
        holder.binding.yemekAdi.text = yemek_liste[position].isim
        holder.binding.yemekAdi.setOnClickListener { yemek_detay(it, yemek_liste[position]) }
    }

    fun yemek_detay(view: View, yemek : Tarif){
        val action = Yemek_ListesiDirections.actionYemekListesiToYemekBilgi(yemek)
        Navigation.findNavController(view).navigate(action)
    }




}