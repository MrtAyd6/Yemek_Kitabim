package com.example.yemek_kitabim

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yemek_kitabim.databinding.FragmentBenimListemBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class benim_listem : Fragment() {
    private var _binding : FragmentBenimListemBinding? = null
    private val binding get() = _binding!!
    private var tarifler = ArrayList<Tarif>()
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

        val retrofit = Retrofit.Builder().baseUrl("http://192.168.154.79/").addConverterFactory(
            GsonConverterFactory.create()).build()

        val api = retrofit.create(KisiselTariflerApi::class.java)

        api.getTarifler().enqueue(object : Callback<List<Tarif>> {
            override fun onResponse(call: Call<List<Tarif>>,response: Response<List<Tarif>>){
                if(response.isSuccessful){
                    tarifler.clear()
                    for (yemek in response.body()!!){
                        tarifler.add(yemek)

                    //recyclerviewda göster
                    adapter = kisisel_liste_adapter(tarifler)
                    binding.kisiselRecycler.layoutManager = LinearLayoutManager(requireContext())
                    binding.kisiselRecycler.adapter = adapter

                    }
                }
            }

            override fun onFailure(call : Call<List<Tarif>>, t: Throwable) {
                Log.e("API", "Hata: ${t.message}")

            }
        })
    }
}