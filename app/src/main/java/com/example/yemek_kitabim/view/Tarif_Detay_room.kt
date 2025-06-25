package com.example.yemek_kitabim.view


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.example.yemek_kitabim.R
import com.example.yemek_kitabim.model.Yemek
import com.example.yemek_kitabim.databinding.FragmentTarifDetayRoomBinding
import com.example.yemek_kitabim.model.TarifVeritabani
import kotlinx.coroutines.launch
import java.io.File


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
        Glide.with(this).load(gorselUrl).signature(ObjectKey(File(gorselUrl).lastModified())).into(binding.yemekGorsel)

        binding.yemekAdi.text = yemek.isim
        binding.malzemeler.text = yemek.malzemeler
        binding.tarif.text = yemek.tarif

        binding.menuButton.setOnClickListener {
            val popup = PopupMenu(requireContext(),binding.menuButton)
            val inflater : MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.menu_room,popup.menu)
            popup.setOnMenuItemClickListener{ item ->
                when(item.itemId){
                    R.id.duzenle -> {
                        //duzenle(item)
                        true
                    }
                    R.id.sil -> {
                        //sil(item)
                        val db = TarifVeritabani.getDatabase(requireContext())
                        val dao = db.yemekDao()
                        val silinecek_yemek = Yemek(yemek.id,yemek.tur,yemek.gorsel,yemek.isim,yemek.malzemeler,yemek.tarif)
                        lifecycleScope.launch {
                            resimSil(yemek.gorsel, requireContext())
                            dao.tarifSil(silinecek_yemek)
                        }
                        val navOps = NavOptions.Builder().setPopUpTo(R.id.benim_listem, true).build()
                        val action = Tarif_Detay_roomDirections.actionTarifDetayRoomToBenimListem()
                        Navigation.findNavController(view).navigate(action,navOps)

                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }

    }


    fun resimSil(dosyaYolu : String, context: Context){
        val dosya = File(dosyaYolu)
        if(dosya.exists()){
            val silindi = dosya.delete()
            Toast.makeText(requireContext(), if(silindi) "Silindi" else "Silinemedi", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(requireContext(), "Dosya bulunamadı", Toast.LENGTH_LONG).show()
        }
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

