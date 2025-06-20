package com.example.yemek_kitabim

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.yemek_kitabim.databinding.FragmentAnaSayfaBinding


class Ana_Sayfa : Fragment() {
    private var _binding: FragmentAnaSayfaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnaSayfaBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.Yemek.setOnClickListener { listeye_gec(it,"yemek") }
        binding.Tatli.setOnClickListener { listeye_gec(it,"tatli") }
        binding.Kahve.setOnClickListener { listeye_gec(it,"kahve") }
        binding.Aperatif.setOnClickListener { listeye_gec(it,"aperatif") }
    }

    fun listeye_gec(view: View,id: String){
        val action = Ana_SayfaDirections.actionAnaSayfaToYemekListesi(id)
        Navigation.findNavController(view).navigate(action)
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}