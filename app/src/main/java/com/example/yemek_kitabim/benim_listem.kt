package com.example.yemek_kitabim

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yemek_kitabim.databinding.FragmentBenimListemBinding
import kotlinx.coroutines.launch


class benim_listem : Fragment() {
    private var _binding : FragmentBenimListemBinding? = null
    private val binding get() = _binding!!
    private lateinit var tarifler: List<Yemek>
    private var adapter : kisisel_liste_adapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBenimListemBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = TarifVeritabani.getDatabase(requireContext())
        val dao = db.yemekDao()

        lifecycleScope.launch {
            tarifler = dao.tumTarifleriGetir()
            adapter = kisisel_liste_adapter(tarifler)
            binding.kisiselRecycler.layoutManager = LinearLayoutManager(requireContext())
            binding.kisiselRecycler.adapter = adapter
        }

        binding.tarifEkleButton.setOnClickListener { tarif_ekle(it) }

    }

    private fun tarif_ekle(view: View){
        val action = benim_listemDirections.actionBenimListemToTarifEkle()
        Navigation.findNavController(view).navigate(action)
    }






    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}