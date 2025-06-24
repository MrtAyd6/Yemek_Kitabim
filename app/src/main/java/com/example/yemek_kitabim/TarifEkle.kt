package com.example.yemek_kitabim

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.yemek_kitabim.databinding.FragmentBenimListemBinding
import com.example.yemek_kitabim.databinding.FragmentTarifEkleBinding
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream


class TarifEkle : Fragment() {
    private var _binding : FragmentTarifEkleBinding? = null
    private val binding get() = _binding!!
    private lateinit var gorselSecici: ActivityResultLauncher<String>
    private var yemekTur : String = ""
    private var yemekAdi: String = "a"
    private var malzemeler : String = "a"
    private var yemekTarif : String = "a"
    private var kayitYolu : String = "a"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gorselSecici = registerForActivityResult(ActivityResultContracts.GetContent()) {uri: Uri? ->
            uri?.let {
                val dosyaAdi = "${yemekAdi.replace (" ","_")}.jpg"
                kayitYolu = requireContext().kopyalaVeKaydet(it,dosyaAdi)

                Glide.with(requireContext()).load(uri).into(binding.yemekGorsel)

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTarifEkleBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.yemekGorsel.setOnClickListener { gorselSec(it) }
        binding.tarifEkleButton.setOnClickListener { tarif_ekle(it) }

    }

    private fun gorselSec(view: View){
        val ad = binding.yemekAdiText.text.toString()
        if(ad.isNotEmpty()){
            yemekAdi = ad
            gorselSecici.launch("image/*")
        }else{
            Toast.makeText(requireContext(),"Lütfen yemek adını girin", Toast.LENGTH_LONG).show()
        }
    }

    fun Context.kopyalaVeKaydet(uri: Uri, dosyaAdi: String): String{
        val inputStream = contentResolver.openInputStream(uri) ?: return ""
        val file = File(filesDir, dosyaAdi)
        val outputStream = FileOutputStream(file)

        inputStream.copyTo(outputStream)
        inputStream.close()
        outputStream.close()

        return file.absolutePath
    }

    private fun tarif_ekle(view: View) {

        yemekAdi = binding.yemekAdiText.text.toString()
        malzemeler = binding.malzemelerText.text.toString()
        yemekTarif = binding.tarifText.text.toString()

        val tarif = Yemek(0, "anayemek", kayitYolu, yemekAdi, malzemeler, yemekTarif)
        val db = TarifVeritabani.getDatabase(requireContext())
        val dao = db.yemekDao()

        lifecycleScope.launch {

            dao.tarifEkle(tarif)
            Toast.makeText(requireContext(),"Tarif eklendi", Toast.LENGTH_LONG).show()
            val action = TarifEkleDirections.actionTarifEkleToBenimListem()
            Navigation.findNavController(view).navigate(action)
        }
    }







    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}