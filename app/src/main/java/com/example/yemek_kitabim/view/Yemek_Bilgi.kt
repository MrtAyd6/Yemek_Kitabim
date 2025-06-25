package com.example.yemek_kitabim.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.yemek_kitabim.model.Tarif
import com.example.yemek_kitabim.databinding.FragmentYemekBilgiBinding

class Yemek_Bilgi : Fragment() {
    private var _binding: FragmentYemekBilgiBinding? = null
    private val binding get() = _binding!!
    private lateinit var yemek : Tarif

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentYemekBilgiBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            yemek = Yemek_BilgiArgs.fromBundle(it).yemek
        }

        val gorsel_url = "http://192.168.72.79:80/" + yemek.gorsel
        Glide.with(this).load(gorsel_url).into(binding.yemekGorsel)

        binding.yemekAdi.text = yemek.isim
        binding.malzemeler.text = yemek.malzemeler
        binding.tarif.text = yemek.yapilis



    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}