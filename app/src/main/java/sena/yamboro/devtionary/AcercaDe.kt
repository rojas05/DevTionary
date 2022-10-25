package sena.yamboro.devtionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import sena.yamboro.devtionary.databinding.ActivityAcercaDeBinding

class AcercaDe : AppCompatActivity() {
    private lateinit var binding: ActivityAcercaDeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAcercaDeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}