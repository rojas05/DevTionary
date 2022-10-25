package sena.yamboro.devtionary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import sena.yamboro.devtionary.databinding.ActivityCategoriasBinding

class Categorias : AppCompatActivity() {

    lateinit var binding: ActivityCategoriasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityCategoriasBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //vinculo con boton de categoria,
        //evento setOnClickListener que permite enviar un parametro a la siguiente activity
        binding.ibmsql.setOnClickListener {

            //enviando a la siguiente activity
            val intent = Intent(this,ListaPalabras::class.java)

            //pasando parametro, en este caso la categoria o lenguaje de programacion
            intent.putExtra("lenguaje","Mysql")

            //ejecucion de lo anterior
            startActivity(intent)
        }

        binding.ibkotlin.setOnClickListener {
            val intent = Intent(this,ListaPalabras::class.java)
            intent.putExtra("lenguaje","Kotlin")
            startActivity(intent)
        }

        binding.ibhtml.setOnClickListener {
            val intent = Intent(this,ListaPalabras::class.java)
            intent.putExtra("lenguaje","Html5")
            startActivity(intent)
        }

        binding.ibnode.setOnClickListener {
            val intent = Intent(this,ListaPalabras::class.java)
            intent.putExtra("lenguaje","Node.js")
            startActivity(intent)
        }

        binding.ibionic.setOnClickListener {
            val intent = Intent(this,ListaPalabras::class.java)
            intent.putExtra("lenguaje","Ionic")
            startActivity(intent)
        }

        binding.ibjs.setOnClickListener {
            val intent = Intent(this,ListaPalabras::class.java)
            intent.putExtra("lenguaje","JavaScript")
            startActivity(intent)
        }

    }

    //importando las opciones del menu superior
    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        //llamada del menu
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    //llamada de cada uno de los items del menu,
    //dentro de la funcion propia de kotlin onOptionsItemSelected()
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            //llamada de cada item por su id,
            //seguido de la funcion que este deve de cumplir
            R.id.info->info()
            R.id.manual->manual()
            R.id.salir->onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    //declaracion de la funcion que cumple un item
    //en este caso cambiar de activity
    private fun info() {
        startActivity(Intent(this,AcercaDe::class.java))
    }
    private fun manual(){
        startActivity(Intent(this,Manual::class.java))
    }
}