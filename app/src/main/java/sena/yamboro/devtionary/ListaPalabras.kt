package sena.yamboro.devtionary

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.text.method.KeyListener
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.Toast
import sena.yamboro.devtionary.databinding.ActivityListaPalabrasBinding
import sena.yamboro.devtionary.databinding.ItemTerminosBinding


class ListaPalabras : AppCompatActivity() {
    lateinit var binding: ActivityListaPalabrasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityListaPalabrasBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //carga de los terminos
        termino()


        //vinculo con la barra de busqueda.
        //evento setOnKeyListener que permite capturar key y event del teclado.
        //se usa para hacer la busqueda mas dinamica.
        binding.tiSearch.setOnKeyListener(object : View.OnKeyListener {

            //onKey() funcion que permite identificar cada event y keyCode
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {

                //cuando la tecla se suelte realizara la busqueda,
                //esto es pocible con el ACTION_UP
                if (event.action == KeyEvent.ACTION_UP &&

                    //para optener la tecla ENTER en especifio se realiza de la siguiente manera,
                    //dentro del condicional
                    keyCode == KeyEvent.KEYCODE_ENTER) {

                    //si la barra de busqueda esta bacia
                    //recuerda al usuario poe medio de un mensaje que debe ingresar un termino
                    if(TextUtils.isEmpty(binding.tiSearch.text.toString())){
                        binding.tiSearch.error = getString(R.string.error)
                        binding.tiSearch.requestFocus()

                        //refresca los terminos
                        termino()
                    }else{

                        //busqueda de los terminos
                        search()
                    }
                    return true
                }
                return false
            }
        })

        //vinculo con el boton de busqueda
        //setOnClickListener permite realizar la busqueda
        binding.btSearch.setOnClickListener {

            //si la barra de busqueda esta bacia
            //recuerda al usuario poe medio de un mensaje que debe ingresar un termino
            if(TextUtils.isEmpty(binding.tiSearch.text.toString())){
                binding.tiSearch.error = getString(R.string.error)
                binding.tiSearch.requestFocus()

                //refresca los terminos
                termino()
            }else{

                //busqueda de los terminos
                search()
            }
        }
    }

    //carga de terminos
    fun termino(){

        //constante para verificar el idioma
        val idioma = getString(R.string.Saludo)

        //obteniendo el parametro que llega
        val bundle = intent.extras
        val dato = bundle?.getString("lenguaje")

        //apertura a la base de datos
        val admin = DevTionaryBd(this, "devTionaryBd", null, 1)

        //apertura a la base de datos en modo de lectura y escritura
        val bd = admin.writableDatabase

        //la constante fila permite comprovar si la consulta fu exitosa
        //rawQuery() permite ejecutar consultas sql
        val fila = bd.rawQuery(
            "select nombre,descripcion,descripcion_ingles from categoria where nombre like '${dato}'",
            null
        )

        //verificando si la consulta si se realizo
        if(fila.moveToFirst()){

            //vinculo con el texView y otorgarle su respectivo valor
            binding.tvlangtitle.setText(fila.getString(0))

            //comprovando en que idioma se encuentra el telefono
            if (idioma == "Bienvenido"){

                //si el telefono se encuentra en español envia el parametro adecuado
                binding.txLangContent.setText(fila.getString(1))
            }else{

                //si el telefono se encuentra en ingles envia el parametro adecuado
                binding.txLangContent.setText(fila.getString(2))
            }
        }

        //consulta a la base de datos
        val ursor= bd.rawQuery("select *from termino where categoria='${dato}'", null)

        //invocando el adapter para el listView
        val adapter= CursorAdapterlv(this, ursor)
        binding.verterminos.adapter= adapter

        //cerrando de la base de datos
        bd.close()
    }

    //busqueda de terminos
    @SuppressLint("SuspiciousIndentation")
    fun search(){

        //constante para verificar el idioma
        val idioma = getString(R.string.Saludo)

        //obteniendo el parametro que llega
        val bundle = intent.extras
        val dato = bundle?.getString("lenguaje")

        //apertura a la base de datos
        val admin = DevTionaryBd(this, "devTionaryBd", null, 1)

        //apertura a la base de datos en modo de lectura y escritura
        val bd = admin.writableDatabase

        //constante para obtener el texto de la barra de busqueda y convertirlo a string
        val search = binding.tiSearch.text.toString()

            //comprovando en que idioma se encuentra el telefono
            if (idioma == "Bienvenido"){

                //si el idioma es español realiza la consulta con ete idioma
                val consulta = bd.rawQuery(
                    "SELECT * FROM termino WHERE nombre like '%${search}%' AND categoria = '${dato}' OR descripcion like '%${search}%' OR ejemplo like '%${search}%' AND categoria = '${dato}'",
                    null
                )

                //invocando el adapter para el listView
                val adapter= CursorAdapterlv(this, consulta)
                binding.verterminos.adapter= adapter

                //cerrando de la base de datos
                bd.close()
            }else{

                //si el idioma es ingles realiza la consulta con ete idioma
                val consulta = bd.rawQuery(
                    "SELECT * FROM termino WHERE nombre like '%${search}%' AND categoria = '${dato}' OR descripcion_ingles like '%${search}%' OR ejemplo like '%${search}%' AND categoria = '${dato}'",
                    null
                )

                //invocando el adapter para el listView
                val adapter= CursorAdapterlv(this, consulta)
                binding.verterminos.adapter= adapter

                //cerrando de la base de datos
                bd.close()
        }
    }

    //clase imersa que permite crear el adapter para el lisView
    //esta clase recive dos parametros
    inner class CursorAdapterlv(context: Context, cursor: Cursor):

        //CursorAdapter permite acceder a cada compnente del item
        CursorAdapter(context, cursor, FLAG_REGISTER_CONTENT_OBSERVER){

        //con la siguiente funcion se llama el item
        override fun newView(p0: Context?, p1: Cursor?, p2: ViewGroup?): View {
            val inflater= LayoutInflater.from(p0)

            //llamada del item
            return inflater.inflate(R.layout.item_terminos, p2, false)
        }

        //bindView() permite crear un viculo para acceder a cada componente del item
        override fun bindView(p0: View?, p1: Context?, p2: Cursor?) {

            //creando el vinculo con los componentres del item
            val bindingItems= ItemTerminosBinding.bind(p0!!)

            //llamada con el vinculo de los texView del item y otorgando su respectivo contenido
            bindingItems.termino.text= cursor!!.getString(1)
            bindingItems.tvEjemplo.text = cursor!!.getString(4)

            //constante para verificar el idioma
            val idioma = getString(R.string.Saludo)

            //comprovando en que idioma se encuentra el telefono
            if (idioma == "Bienvenido"){

                //llamada con el vinculo de los texView del item y otorgando su respectivo contenido
                bindingItems.descipcion.text= cursor!!.getString(2)
            }else{

                //llamada con el vinculo de los texView del item y otorgando su respectivo contenido
                bindingItems.descipcion.text= cursor!!.getString(3)
            }

            //vinculo con el boton de copiar
            //setOnClickListener en este caso permite copiar en el portapapeles
            bindingItems.ibCopy.setOnClickListener {

                //constante para guardar el texto por copiar
                val clip= ClipData.newPlainText("text",

                    //vinculo con los texVieww para copiar el contenido adecuado.
                    //con la ayuda de \n se estructura la informacion
                    bindingItems.termino.text.toString() + "\n" +
                            bindingItems.descipcion.text.toString() + "\n" +
                            bindingItems.textView2.text.toString() +  "\n" +
                            bindingItems.tvEjemplo.text.toString()
                )

                //variable para obtener el servicio de portapapeles del sistema
                //CLIPBOARD_SERVICE permite guardar el texto
                val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

                //pasando el texto copyado al portapapeles
                clipboard.setPrimaryClip(clip)

                //mensage para indicar que el texto se copio
                Toast.makeText(this@ListaPalabras, getString(R.string.copy), Toast.LENGTH_SHORT).show()
            }
        }

    }
}