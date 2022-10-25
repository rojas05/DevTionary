package sena.yamboro.devtionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import sena.yamboro.devtionary.databinding.ActivityManualBinding


class Manual : AppCompatActivity() {
    private lateinit var binding: ActivityManualBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityManualBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //constante para verificar el idioma
        val idioma = getString(R.string.Saludo)

        //comprovando en que idioma se encuentra el telefono
        if(idioma == "Bienvenido"){

            //vinculo con el webView
            //segun el idioma carga un archivo html5 que contiene el manual
            binding.webView.loadUrl("file:///android_asset/index.html")
        }else{

            //vinculo con el webView
            //segun el idioma carga un archivo html5 que contiene el manual
            binding.webView.loadUrl("file:///android_asset/indexIngles.html")
        }

    }
}