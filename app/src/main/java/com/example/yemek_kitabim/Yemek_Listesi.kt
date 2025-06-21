package com.example.yemek_kitabim

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ActionMode
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yemek_kitabim.databinding.FragmentAnaSayfaBinding
import com.example.yemek_kitabim.databinding.FragmentYemekListesiBinding
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.awaitAll
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Yemek_Listesi : Fragment() {
    private var _binding: FragmentYemekListesiBinding? = null
    private val binding get() = _binding!!
    private lateinit var yemek_turu : String
    private var tarifler : List<Tarif>? = null
    private var adapter : Yemekler_adapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentYemekListesiBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            yemek_turu = Yemek_ListesiArgs.fromBundle(it).yemekTuru
        }

        val retrofit = Retrofit.Builder().baseUrl("http://192.168.154.79/").addConverterFactory(
            GsonConverterFactory.create()).build()

        val api = retrofit.create(TariflerApi::class.java)

        api.getTarifler().enqueue(object : Callback<List<Tarif>> {
            override fun onResponse(call: Call<List<Tarif>>,response: Response<List<Tarif>>){
                if(response.isSuccessful){
                    tarifler = response.body()

                    //recyclerviewda göster
                    adapter = Yemekler_adapter(tarifler!!)
                    binding.yemekListesiRecycler.layoutManager = LinearLayoutManager(requireContext())
                    binding.yemekListesiRecycler.adapter = adapter
                }
            }

            override fun onFailure(call : Call<List<Tarif>>, t: Throwable){
                Log.e("API","Hata: ${t.message}")
            }
        })




        /*
        api.tarif_ekle("images/menemen.jpg","menemen","sogan, yumurta, domates","asdasdadasdasdada").enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                Log.e("API","Cevap: ${response.body()}")
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                Log.e("API","Hata: ${t.message}")
            }
        })
        */


    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}