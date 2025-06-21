package com.example.yemek_kitabim

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yemek_kitabim.databinding.YemekSatirBinding

class kisisel_liste_adapter(private val yemek_liste : ArrayList<Tarif>): RecyclerView.Adapter<kisisel_liste_adapter.TarifHolder>() {
    class TarifHolder(val binding: YemekSatirBinding) : RecyclerView.ViewHolder(binding.root) {}

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
    }





}