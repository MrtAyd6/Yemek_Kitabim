package com.example.yemek_kitabim.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.yemek_kitabim.databinding.YemekSatirBinding
import com.example.yemek_kitabim.model.Yemek
import com.example.yemek_kitabim.view.benim_listemDirections

class kisisel_liste_adapter(private val yemek_liste: List<Yemek>): RecyclerView.Adapter<kisisel_liste_adapter.TarifHolder>() {
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
        holder.binding.yemekAdi.setOnClickListener { tarifDetay(it,yemek_liste[position]) }
    }

    private fun tarifDetay(view: View,secilenYemek: Yemek){
        val action = benim_listemDirections.actionBenimListemToTarifDetayRoom(secilenYemek)
        Navigation.findNavController(view).navigate(action)
    }




}