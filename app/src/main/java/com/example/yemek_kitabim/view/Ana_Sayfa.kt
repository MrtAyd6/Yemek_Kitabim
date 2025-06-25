package com.example.yemek_kitabim.view

import android.os.Bundle
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
        binding.Yemek.setOnClickListener { listeye_gec(it,"anayemek") }
        binding.Tatli.setOnClickListener { listeye_gec(it,"tatli") }
        binding.Kahve.setOnClickListener { listeye_gec(it,"kahve") }
        binding.Aperatif.setOnClickListener { listeye_gec(it,"aperatif") }

        binding.benimKitabim.setOnClickListener { kitaba_gec(it) }
    }

    fun listeye_gec(view: View,id: String){
        val action = Ana_SayfaDirections.actionAnaSayfaToYemekListesi(id)
        Navigation.findNavController(view).navigate(action)
    }

    fun kitaba_gec(view: View){
        val action = Ana_SayfaDirections.actionAnaSayfaToBenimListem()
        Navigation.findNavController(view).navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}