package com.example.yemek_kitabim

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.yemek_kitabim.databinding.FragmentTarifDetayRoomBinding
import com.example.yemek_kitabim.databinding.FragmentYemekBilgiBinding


class Tarif_Detay_room : Fragment() {
    private var _binding : FragmentTarifDetayRoomBinding? = null
    private val binding get() = _binding!!
    private lateinit var yemek : Yemek


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTarifDetayRoomBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            yemek = Tarif_Detay_roomArgs.fromBundle(it).yemek
        }
        val gorselUrl = yemek.gorsel
        Glide.with(this).load(gorselUrl).into(binding.yemekGorsel)

        binding.yemekAdi.text = yemek.isim
        binding.malzemeler.text = yemek.malzemeler
        binding.tarif.text = yemek.tarif


    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}