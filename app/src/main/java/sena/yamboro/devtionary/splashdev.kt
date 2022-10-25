package sena.yamboro.devtionary

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast

class splashdev : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //llamada a SharedPreferences
        val preferences = getSharedPreferences("app", Context.MODE_PRIVATE)

        //invocando variable SharedPreferences para ejecutar la insercion
        val estad = preferences.getString("estado","")

        //combrobando el estado para que los datos no se repliquen
        if (estad == ""){

            //ejecutando la insercion
            insertarCategorias()
        }else{

            //cambio de activity
            starTimer()
        }
    }

    //funcion para suspender la app
    fun starTimer() {

        //tiempo de espera en milisegundos de la app
        object: CountDownTimer(1500,1000){
            override fun onTick(p0: Long) {
            }

            //cambio de activity
            override fun onFinish() {

                //ejecutando el cambio de activity
                val intent = Intent(applicationContext, Categorias::class.java).apply { }
                startActivity(intent)

                //finalizando para que la app no retorne al splash
                finish()
            }

        //.start() ejecuta de lo anterior
        }.start()

    }
    fun insertarCategorias() {

        //llamada a SharedPreferences
        val preferences = getSharedPreferences("app", Context.MODE_PRIVATE)

        //llamada a SharedPreferences de modo editable
        val editor = preferences.edit()

        //apertura a la base de datos
        val admin = DevTionaryBd(this, "devTionaryBd", null, 1)

        //apertura a la base de datos en modo de lectura y escritura
        val bd = admin.writableDatabase

        //ContentValues() permite almacenar distintos valores para la insercion
        //dentro de una constante en este caso registro
        val registro = ContentValues()

        //put() permite indicar a donde se inserta el valor e indicar el valor
        registro.put("nombre","Kotlin")
        registro.put("Descripcion","nuevo lenguaje de programación de código abierto como Java, JavaScript, etc. Es un lenguaje de alto nivel fuertemente tipado estáticamente que combina parte funcional y técnica en un mismo lugar. Actualmente, Kotlin apunta a Java y JavaScript.")
        registro.put("descripcion_ingles","New open source programming language like Java, JavaScript, etc. It is a strongly statically typed high-level language that combines functional and technical parts in one place. Kotlin currently targets Java and JavaScript.")

        //insert() permite insertar los valores e indicar la tabla o entidad a la que se dirigen
        bd.insert("categoria", null, registro)

        registro.put("nombre","Mysql")
        registro.put("descripcion","Sistema de gestión de bases de datos relacional desarrollado bajo licencia dual: Licencia pública general/Licencia comercial por Oracle Corporation y está considerada como la base de datos de código abierto más popular del mundo, y una de las más populares en general junto a Oracle y Microsoft SQL Server, todo para entornos de desarrollo web.")
        registro.put("descripcion_ingles","Relational database management system developed under a dual license: General Public License/Commercial License by Oracle Corporation and is considered the most popular open source database in the world, and one of the most popular in general along with Oracle and Oracle. Microsoft SQL Server, all for web development environments.")
        bd.insert("categoria", null, registro)

        registro.put("nombre","Html5")
        registro.put("descripcion","Es un estándar para estructurar y presentar contenido en la World Wide Web. HTML5 es una cooperación entre el Consorcio World Wide Web (W3C) y el Grupo de Trabajo de Tecnología de Aplicaciones de Hipertexto Web (WHATWG).")
        registro.put("descripcion_ingles","It is a standard for structuring and presenting content on the World Wide Web. HTML5 is a cooperation between the World Wide Web Consortium (W3C) and the Web Hypertext Applications Technology Working Group (WHATWG).")
        bd.insert("categoria", null, registro)

        registro.put("nombre","Node.js")
        registro.put("descripcion","Es un entorno en tiempo de ejecución multiplataforma, de código abierto, para la capa del servidor (pero no limitándose a ello) basado en el lenguaje de programación JavaScript, asíncrono, con E/S de datos en una arquitectura orientada a eventos y basado en el motor V8 de Google.")
        registro.put("descripcion_ingles","It is an open source, cross-platform runtime environment for the server layer (but not limited to) based on the JavaScript programming language, asynchronous, with data I/O in an event-driven architecture and based on Google's V8 engine.")
        bd.insert("categoria", null, registro)

        registro.put("nombre","Ionic")
        registro.put("descripcion","Es un framework para crear aplicaciones híbridas. A día de hoy estamos en la versión 3, y Ionic está basado en Javscript, o lo que es lo mismo, para hacer una aplicación híbrida con Ionic solo tienes que controlar de HTML, CSS y Javascript.")
        registro.put("descripcion_ingles","It is a framework for creating hybrid applications. Today we are in version 3, and Ionic is based on Javscript, or what is the same, to make a hybrid application with Ionic you only have to control HTML, CSS and Javascript.")
        bd.insert("categoria", null, registro)

        registro.put("nombre","JavaScript")
        registro.put("descripcion","Lenguaje de programación interpretado, dialecto del estándar ECMAScript. Se define como orientado a objetos, basado en prototipos, imperativo, débilmente tipado y dinámico")
        registro.put("descripcion_ingles","Interpreted programming language, dialect of the ECMAScript standard. It is defined as object-oriented, prototype-based, imperative, weakly typed, and dynamic.")
        bd.insert("categoria", null, registro)

        //llamada a la funcion de insertar los terminos
        insertarTerminos()

        //editando el estado, para que este codogo no se ejecute nunca mas
        editor.putString("estado","1")
        editor.apply()

        //mensage de bienvenida que indica que la base de datos se cargo
        Toast.makeText(this, getString(R.string.Saludo), Toast.LENGTH_LONG).show()


        //ejecutando el cambio de activity
        startActivity(Intent(this,Categorias::class.java))

        //finalizando para que la app no retorne al splash
        finish()
    }

    //insertar terminos
    fun insertarTerminos(){
        val admin = DevTionaryBd(this,"devTionaryBd", null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()

//<-------------------------------------------TERMINOS MYSQL--------------------------------------------------->

        registro.put("nombre","DROP")
        registro.put("descripcion","Declaración para eliminar una tabla o base de datos existente.")
        registro.put("descripcion_ingles","used to eliminate a table, a database or all databases")
        registro.put("ejemplo","DROP TABLE table_name;\n" +
                "\tDROP DATABASE database_name;")
        registro.put("categoria","Mysql")
        bd.insert("termino", null, registro)

        registro.put("nombre","USE")
        registro.put("descripcion","USE se utiliza para usar una base de datos.")
        registro.put("descripcion_ingles","Statement for use the database.")
        registro.put("ejemplo","USE db_name;")
        registro.put("categoria","Mysql")
        bd.insert("termino", null, registro)

        registro.put("nombre","INSERT INTO")
        registro.put("descripcion","Sentencia que se utiliza para agrega datos a una tabla.")
        registro.put("descripcion_ingles","Used to insert data in a table")
        registro.put("ejemplo","INSERT INTO name_table (content) values (description)")
        registro.put("categoria","Mysql")
        bd.insert("termino", null, registro)

        registro.put("nombre","ALTER")
        registro.put("descripcion","Modificar elementos en las tablas o entidades.")
        registro.put("descripcion_ingles","Change elements from the table")
        registro.put("ejemplo","alter table name_table change column name_column name_incorporated")
        registro.put("categoria","Mysql")
        bd.insert("termino", null, registro)

        registro.put("nombre","JOIN")
        registro.put("descripcion","Permite combinar registros de una o más tablas en una base de datos.")
        registro.put("descripcion_ingles","Used to connect tables")
        registro.put("ejemplo"," Select * from usuarios\n" +
                "->join tables on pk_id = fk_id;")
        registro.put("categoria","Mysql")
        bd.insert("termino", null, registro)

        registro.put("nombre","PRIMARY KEY")
        registro.put("descripcion","Campo que identifica de forma única a cada fila de una tabla")
        registro.put("descripcion_ingles","It's an identifier used to differentiate the unique values")
        registro.put("ejemplo","identification int not null primary key;")
        registro.put("categoria","Mysql")
        bd.insert("termino",null,registro)

        registro.put("nombre","UPDATE")
        registro.put("descripcion","Comando utilizado para cambia los datos de uno o más registros en una tabla")
        registro.put("descripcion_ingles","Update table content")
        registro.put("ejemplo","update name_table set name_Column = “add information” where primaryKey;")
        registro.put("categoria","Mysql")
        bd.insert("termino",null,registro)

        registro.put("nombre","VALUES")
        registro.put("descripcion","Información a guardar en una determinada tabla ")
        registro.put("descripcion_ingles","Part of code to insert data in a table")
        registro.put("ejemplo","Insert into nombreTabla(Content) values (‘description’);")
        registro.put("categoria","Mysql")
        bd.insert("termino",null,registro)

        registro.put("nombre","CONSTRAINT")
        registro.put("descripcion","Se usa en las instrucciones ALTER TABLE y CREATE TABLE para crear o eliminar restricciones")
        registro.put("descripcion_ingles","Part of code to create a connection between two tables")
        registro.put("ejemplo","ALERT table vender add constraint nameRelación foreign key(id _fk)   references entidad(Pk);")
        registro.put("categoria","Mysql")
        bd.insert("termino",null,registro)

        registro.put("nombre","FOREIGN KEY")
        registro.put("descripcion","En bases de datos relacional una foreign key o llave foránea en una tabla corresponde a los valores de una llave primaria en otra tabla,  indicando  relacion entre ellas.")
        registro.put("descripcion_ingles","Used to connect tables with the primary key from patern table")
        registro.put("ejemplo","alter table vender add constraint nameRelación foreign key (id _fk)   references    entidad(Pk);")
        registro.put("categoria","Mysql")
        bd.insert("termino",null,registro)

        registro.put("nombre","AUTO_INCREMENT")
        registro.put("descripcion","El valor se incrementa automaticamnete con cada registro  creado.")
        registro.put("descripcion_ingles","Used to increment one to one the value of a data")
        registro.put("ejemplo","category_id` int (11) AUTO_INCREMENT,")
        registro.put("categoria","Mysql")
        bd.insert("termino",null,registro)

        registro.put("nombre","VARCHAR")
        registro.put("descripcion","Es un tipo de dato  que permite almacenar una cadena de caracteres hasta 265.")
        registro.put("descripcion_ingles","Datatype used to hold any type of character")
        registro.put("ejemplo","create table test(col1 varchar(max));")
        registro.put("categoria","Mysql")
        bd.insert("termino",null,registro)

        registro.put("nombre","SHOW")
        registro.put("descripcion","Proporciona información sobre bases de datos, tablas, columnas o información.")
        registro.put("descripcion_ingles","Used to show the structure of a database or a table.")
        registro.put("ejemplo","SHOW DATABASES;\n" +
                "\tSHOW TABLES;")
        registro.put("categoria","Mysql")
        bd.insert("termino",null,registro)

        registro.put("nombre","ADD")
        registro.put("descripcion","adiciona columnas a una tabla es importante informar la ubicación de la nueva columna. Para ello se utiliza AFTER.")
        registro.put("descripcion_ingles","used to alter data and add the new data")
        registro.put("ejemplo","alter table nombre_tabla ADD nueva_Columna Tipo_dato Modificar AFTER Nombre_columna;")
        registro.put("categoria","Mysql")
        bd.insert("termino",null,registro)

        registro.put("nombre","SELECT")
        registro.put("descripcion","Permite realizar consultas en una o varias tablas de una base de datos.  ")
        registro.put("descripcion_ingles","Used to show the data from from any table")
        registro.put("ejemplo","SELECT * FROM usuarios;\n"+
                "SELECT nombre, apellidos FROM usuarios")
        registro.put("categoria","Mysql")
        bd.insert("termino",null,registro)

        registro.put("nombre","INSERT")
        registro.put("descripcion","Permite insertar datos en la tablas de una base de datos.")
        registro.put("descripcion_ingles","allows to insert data into the tables of a database.")
        registro.put("ejemplo","INSERT INTO nombtabla\n" +
                "VALUES (valor1, valor2, ...)")
        registro.put("categoria","Mysql")
        bd.insert("termino",null,registro)

        registro.put("nombre","WHERE")
        registro.put("descripcion","En una instrucción SQL, la cláusula WHERE especifica criterios que tienen que cumplir los valores de campo para que los registros que contienen los valores se incluyan en los resultados de la consulta.")
        registro.put("descripcion_ingles","Used  to show, update or delete an specific value")
        registro.put("ejemplo","UPDATE \"nombre_tabla\" SET \"columna_1\" =[nuevo valor]  WHERE \"condicion\";")
        registro.put("categoria","Mysql")
        bd.insert("termino",null,registro)

        registro.put("nombre","SET")
        registro.put("descripcion","Indica los campos que se van a actualizar y con qué valores lo vamos a hacer.")
        registro.put("descripcion_ingles","Used to indicate the values to update.")
        registro.put("ejemplo","UPDATE \"nombre_tabla\" SET \"columna_1\" =[nuevo valor]  WHERE \"condicion\";")
        registro.put("categoria","Mysql")
        bd.insert("termino",null,registro)

        registro.put("nombre","RENAME")
        registro.put("descripcion","Cambia el nombre de una tabla.")
        registro.put("descripcion_ingles","Used to change the name of a table.")
        registro.put("ejemplo","alter table personas rename clientes;")
        registro.put("categoria","Mysql")
        bd.insert("termino",null,registro)

        registro.put("nombre","CREATE")
        registro.put("descripcion","Comando utilizado para crear una base de datos o una tabla.")
        registro.put("descripcion_ingles","Used to create a database or a table.")
        registro.put("ejemplo","CREATE DATABASE mydb;\n" +
                "CREATE TABLE mi tabla ( id INT PRIMARY KEY, nombre VARCHAR(20) );")
        registro.put("categoria","Mysql")
        bd.insert("termino",null,registro)

        registro.put("nombre","TABLE")
        registro.put("descripcion","Used  to show, update or delete an specific valueused  to show, update or delete an specific valueused  to show, update or delete an specific value")
        registro.put("descripcion_ingles","Element used to store the data from the entities composed by rows and columns")
        registro.put("ejemplo","show table;")
        registro.put("categoria","Mysql")
        bd.insert("termino",null,registro)



//<-------------------------------------------TERMINOS KOTLIN--------------------------------------------------->
        registro.put("nombre","Intent")
        registro.put("descripcion","El Intent representa la intención que tiene una app de realizar una tarea.")
        registro.put("descripcion_ingles","This part of the function it's used to start a new apllication view.")
        registro.put("ejemplo","Intent(this,mainactivity::class.java)")
        registro.put("categoria","Kotlin")
        bd.insert("termino", null, registro)

        registro.put("nombre","close()")
        registro.put("descripcion","Cierra la conexión de la bade de datos sqlite.")
        registro.put("descripcion_ingles","Used to close the database.")
        registro.put("ejemplo","val = bd.close()")
        registro.put("categoria","Kotlin")
        bd.insert("termino", null, registro)

        registro.put("nombre","setOnClickListener()")
        registro.put("descripcion","Detector de eventos, se invocará cuando se produzca un evento, como un clic en un botón.")
        registro.put("descripcion_ingles","Used to do some action when on click on the button")
        registro.put("ejemplo","boton.setOnClickListener{Intent(this,mainactivity::class.java)}")
        registro.put("categoria","Kotlin")
        bd.insert("termino", null, registro)

        registro.put("nombre","Binding")
        registro.put("descripcion","Es la vinculacion de vistas generando una vinculacion para cada XML presente en el modulo")
        registro.put("descripcion_ingles","Used to concects Activitis")
        registro.put("ejemplo"," private lateinit var binding: ActivityMainBinding\n" +
                "override fun onCreate(savedInstanceState: Bundle?) {\n" +
                "super.onCreate(savedInstanceState)\n" +
                "binding = ActivityMainBinding.inflate(layoutInflater)\n" +
                "setContentView(binding.root)\n" +
                "}")
        registro.put("categoria","Kotlin")
        bd.insert("termino", null, registro)


        registro.put("nombre","putExtra()")
        registro.put("descripcion","Permite enviar datos de una actividad a otra actividad.")
        registro.put("descripcion_ingles","Let send data from an activity to orther activity.")
        registro.put("ejemplo","Intent i = new Intent(FirstScreen.this, SecondScreen.class);\n" +
                "String keyIdentifer = null; i.putExtra(strName, keyIdentifer ); ) ")
        registro.put("categoria","Kotlin")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","put")
        registro.put("descripcion","Sirve para actualizar o insertar un recurso.")
        registro.put("descripcion_ingles","Used to insert or update a resource.")
        registro.put("ejemplo","abstract fun put(key: K, value: V): V?\n" +
                "(source)")
        registro.put("categoria","Kotlin")
        bd.insert("termino",null,registro)

        registro.put("nombre","fun()")
        registro.put("descripcion","Sire para definir un funcion, entre paréntesis se pueden indicar los parámetros que recibe la función")
        registro.put("descripcion_ingles","Used to define a function.")
        registro.put("ejemplo","fun onFinish(){finish()}")
        registro.put("categoria","Kotlin")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","import")
        registro.put("descripcion","El proceso de importación de las dependencias, bibliotecas y proyectos a Android Studio.")
        registro.put("descripcion_ingles","Used to import dependencies,librarys or proyects.")
        registro.put("ejemplo","import org.example.Message // Message is accessible\n" +
                "import org.test.Message as testMessage // testMessage stands for 'org.test.Message'")
        registro.put("categoria","Kotlin")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","class")
        registro.put("descripcion","Directiva para crear una clase dentro de una aplicación de Android que contiene todos los demás componentes, como actividades y servicios.")
        registro.put("descripcion_ingles","Content all components, like activities and services.")
        registro.put("ejemplo","class Main: ActivityMain(){}")
        registro.put("categoria","Kotlin")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","onBackPressed")
        registro.put("descripcion","Se utiliza para poder navegar hacia atrás en una aplicación")
        registro.put("descripcion_ingles","Used to come back in an app.")
        registro.put("ejemplo","override fun onBackPressed() {\n" +
                "        AlertDialog.Builder(this).apply {\n" +
                "            setTitle(\"Please confirm.\")\n" +
                "            setMessage(\"Are you want to exit the app?\")\n" +
                "\n" +
                "            setPositiveButton(\"Yes\") { _, _ ->\n" +
                "                // if user press yes, then finish the current activity\n" +
                "                super.onBackPressed()\n" +
                "            ")
        registro.put("categoria","Kotlin")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","override")
        registro.put("descripcion","Le indicará al compilador que se trata de un método que está en la clase principal y se cambiará su funcionamiento en ese momento.")
        registro.put("descripcion_ingles","Inform to the compilator about the method in the principal class.")
        registro.put("ejemplo","overraide fun onCreate(savedIntanceState: Bundle?){}")
        registro.put("categoria","Kotlin")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","if()")
        registro.put("descripcion","Es un condicional que nos permite ejecutar un bloque de código concreto si se cumple cierta condición.")
        registro.put("descripcion_ingles","It's the first part of a condicional with Kotlin.")
        registro.put("ejemplo","if(idioma==”es”){\n" +
                "esta en español\n" +
                "}else{it is in english})")
        registro.put("categoria","Kotlin")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","pacKage")
        registro.put("descripcion","Forma muy efectiva de organizar nuestro código para ser utilizado en nuestro proyecto o reutilizado por otros")
        registro.put("descripcion_ingles","Used to organize the code.")
        registro.put("ejemplo","package org.example\n" +
                "\n" +
                "fun printMessage() { /*...*/ }\n" +
                "class Message { /*...*/ }\n")
        registro.put("categoria","Kotlin")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","inner class")
        registro.put("descripcion","Se utiliza para crear una clase inmersa dentro de otra clase.")
        registro.put("descripcion_ingles","Used to create a class inside of orther class.")
        registro.put("ejemplo","class Outer {\n" +
                "    private val bar: Int = 1\n" +
                "    inner class Inner {\n" +
                "        fun foo() = bar\n" +
                "    }\n" +
                "}\n")
        registro.put("categoria","Kotlin")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","move Tofrist")
        registro.put("descripcion","Permite probar si la consulta devolvió un conjunto vacío")
        registro.put("descripcion_ingles","Let to proof if the consult is empty.")
        registro.put("ejemplo","public abstract boolean moveToFirst ()")
        registro.put("categoria","Kotlin")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","rawQuery")
        registro.put("descripcion","Permite realizar una consulyta SQLite.")
        registro.put("descripcion_ingles","Used to do a SQLite consult.")
        registro.put("ejemplo","")
        registro.put("categoria","Kotlin")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","val")
        registro.put("descripcion","Se usa para definir una variable cuyo valor no cambia nunca.")
        registro.put("descripcion_ingles","Used to define a variable with a fixed value.")
        registro.put("ejemplo","val idioma= getString(R.id.saludo)")
        registro.put("categoria","Kotlin")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","var")
        registro.put("descripcion","Se usa para definir una variable cuyo valor puede cambiarse")
        registro.put("descripcion_ingles","It's used to declare a variable(can be modified,updated and redeclared)")
        registro.put("ejemplo","var  nombre = string()")
        registro.put("categoria","Kotlin")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","imagenButton")
        registro.put("descripcion","Permitirá utilizar una imagen cualquiera como un botón, sobre el cual el usuario puede pulsar para ir, por ejemplo, a otra actividad.")
        registro.put("descripcion_ingles","Used to let  use an image like a button.")
        registro.put("ejemplo","  <ImageButton\n" +
                "        android:id=\"@+id/imageBtn\"\n" +
                "        android:layout_width=\"wrap_content\"\n" +
                "        android:layout_height=\"wrap_content\"\n" +
                "        android:layout_marginLeft=\"100dp\"\n" +
                "        android:src=\"@android:drawable/btn_plus\" />")
        registro.put("categoria","Kotlin")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","getString")
        registro.put("descripcion","Se utilizan para octener un texto")
        registro.put("descripcion_ingles","used to get a text")
        registro.put("ejemplo","val string: String = getString(R.string.hello)")
        registro.put("categoria","Kotlin")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","StartActivity")
        registro.put("descripcion","Esta funcion se utiliza para iniciar un nueva pantalla en una aplicaion movil, para ello hace uso de la  funcion Intent")
        registro.put("descripcion_ingles","This  part of the function it's used to start a new apllication view.")
        registro.put("ejemplo","StartActivity(Intent(this,mainactiviy::class.java))")
        registro.put("categoria","Kotlin")
        bd.insert   ("termino", null, registro)




//<-------------------------------------------TERMINOS HTML5--------------------------------------------------->


        registro.put("nombre","<br>")
        registro.put("descripcion","Se usa para definir un salto de línea")
        registro.put("descripcion_ingles","Make a line break")
        registro.put("ejemplo","<br><div class=\"text\">DevTionary (Diccionario para desarrolladores)</div><br>")
        registro.put("categoria","Html5")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","<! DOCTYPE html>")
        registro.put("descripcion","Comando utilizado para declarar un archivo como html5 ")
        registro.put("descripcion_ingles","Used to declare an archive like an html5")
        registro.put("ejemplo","<!DOCTYPE html>\n" +
                "<html>" +
                "\n" +
                "  <head>" +
                "\n" +
                "    <title>La casa de Jhon Weilers</title>" +
                "\n" +
                "  </head>" +
                "\n" +
                "  <body>" +
                "\n" +
                "    <h1>Beinvenido!</h1>" +
                "\n" +
                "    <p>Beinvenido a la casa de Jhon Weilers...</p>" +
                "\n" +
                "  </body>" +
                "\n" +
                "</html>")
        registro.put("categoria","Html5")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","<h1>a<h6>")
        registro.put("descripcion","Define encabezados o títulos")
        registro.put("descripcion_ingles","Define headings or title")
        registro.put("ejemplo","<h1> Título de la página </h1>\n" +
                "\n" +
                "<h2> Sutítulo de la página </h2>\n" +
                "<h3> Título de tercer nivel</h3>")
        registro.put("categoria","Html5")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","<body></body>")
        registro.put("descripcion","Define el cuerpo del documento HTML")
        registro.put("descripcion_ingles","Define the body of the document ")
        registro.put("ejemplo","<body>\n" +
                "</body>")
        registro.put("categoria","Html5")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","<head></head>")
        registro.put("descripcion","Define la cabecera del archivo HTML")
        registro.put("descripcion_ingles","Defines the file header")
        registro.put("ejemplo","<head>\n" +
                "    <title>Document title</title>\n" +
                "  </head>")
        registro.put("categoria","Html5")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","<title></title>")
        registro.put("descripcion","Muestra el título en la cabecera del archivo HTML")
        registro.put("descripcion_ingles","Show title in file header")
        registro.put("ejemplo"," <title>Document</title>")
        registro.put("categoria","Html5")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","<video>")
        registro.put("descripcion","Permite incluir un video o película.")
        registro.put("descripcion_ingles","Allows you to include a video or movie.")
        registro.put("ejemplo","<video src=\"/assets/videos/A_Flight_To_Mars.ogg\" width=\"384\" height=\"288\" controls>\n" +
                "\n" +
                "  <p>Tecnico en programación de aplicaciones móviles.</p>\n" +
                "\n" +
                "</video>")
        registro.put("categoria","Html5")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","<a></a>")
        registro.put("descripcion","Crea un enlace a otra página html o parte de la misma página.")
        registro.put("descripcion_ingles","Defines a type the hyperlink")
        registro.put("ejemplo","<a href=”https://www.nombredelaweb.com/” target=”_blank”>aquí</a>")
        registro.put("categoria","Html5")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","<article>")
        registro.put("descripcion","Permite separar diversos elementos independientes que puedan estar contenidos en uno mismo.")
        registro.put("descripcion_ingles","Allows you to separate various independent elements that may be contained in the same")
        registro.put("ejemplo","<article>\n" +
                "\n" +
                "  <h1>Organización Internacional para la Estandarización</h1>\n" +
                "\n" +
                "  <p>La Organización Internacional para la Estandarización conocida como ISO, es un cuerpo internacional de establecimiento de estándares compuesto por representantes de varias organizaciones nacionales de estándares.</p>\n" +
                "\n" +
                "  <p>...</p>\n" +
                "\n" +
                "</article>")
        registro.put("categoria","Html5")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","<audio>")
        registro.put("descripcion","Define contenido de sonido.")
        registro.put("descripcion_ingles","Defines the contento f the sond")
        registro.put("ejemplo","<audio src=\"/assets/audio/Jahzzar_The_Flowers_Are_Still_Standing.mp3\" controls>\n" +
                "\n" +
                "  <p>Tu navegador no soporta la reproducción nativa de audio. </p>\n" +
                "\n" +
                "</audio>")
        registro.put("categoria","Html5")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","<b></b>")
        registro.put("descripcion","Define un texto en negrilla")
        registro.put("descripcion_ingles","Sets the text to blod")
        registro.put("ejemplo","<p>\n" +
                "       Texto normal y... <b>Texto en negrita</b>\n" +
                "</p>")
        registro.put("categoria","Html5")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","<button></button>")
        registro.put("descripcion","Se utiliza para crear un botón en el archivo de HTML")
        registro.put("descripcion_ingles","Is used to create a button in the HTML file")
        registro.put("ejemplo","<button type=\"button\">Estoy en huelga...</button>")
        registro.put("categoria","Html5")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","<embed></embed>")
        registro.put("descripcion","El elemento embed provee los medios para insertar aplicaciones externas, típicamente aquellas que requieren un plugin, en el documento")
        registro.put("descripcion_ingles","Allows to insert an external")
        registro.put("ejemplo","<embed src=\"/assets/flash/light-bulb.swf\" type=\"application/x-shockwave-flash\" width=\"180\" height=\"350\" quality=\"high\" wmode=\"transparent\">")
        registro.put("categoria","Html5")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","<input>")
        registro.put("descripcion","Se usa para crear controles interactivos para formularios basados en la web con el fin de recibir datos del usuario.")
        registro.put("descripcion_ingles","Is used to create interactive controls for web-based forms to receive user input.")
        registro.put("ejemplo","<input type=\"submit\" value=\"Enviar datos\">")
        registro.put("categoria","Html5")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","<p>")
        registro.put("descripcion","Distribuye texto en párrafos.")
        registro.put("descripcion_ingles","Is appropriate for distributing the text in paragraphs.")
        registro.put("ejemplo","<p>El año 1866 quedó caracterizado por un extraño acontecimiento, por un fenómeno inexplicable e inexplicado que nadie, sin duda, ha podido olvidar.</p>")
        registro.put("categoria","Html5")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","<form> ")
        registro.put("descripcion","Representa una sección de un documento que contiene controles interactivos que permiten a un usuario enviar información a un servidor web.")
        registro.put("descripcion_ingles","Represents a section of a document that contains interactive controls that allow a user to submit information to a web server.")
        registro.put("ejemplo","<form action=\"../../form-result.php\" method=\"post\" target=\"_blank\">\n" +
                "\n" +
                "  <p>Nombre de usuario: <input type=\"text\" name=\"usuario\"></p>\n" +
                "\n" +
                "  <p>Contraseña: <input type=\"password\" name=\"pass\"></p>\n" +
                "\n" +
                "  <p><input type=\"submit\" value=\"Enviar datos\"></p>\n" +
                "\n" +
                "</form>")
        registro.put("categoria","Html5")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","<label>")
        registro.put("descripcion","Representa una etiqueta para un elemento en una interfaz de usuario. ")
        registro.put("descripcion_ingles","Represents a label for an element in a user interface.")
        registro.put("ejemplo","<p><label>Nombre de usuario: <input type=\"text\" name=\"usuario\"></label></p>\n" +
                "\n" +
                "  <p><label>Contraseña: <input type=\"password\" name=\"pass\"></label></p>")
        registro.put("categoria","Html5")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","<select>")
        registro.put("descripcion","Representa un control que muestra un menú de opciones")
        registro.put("descripcion_ingles","Represents a control that displays a menu of options.")
        registro.put("ejemplo","<select name=\"deporte\">\n" +
                "\n" +
                "      <option>Fútbol</option>\n" +
                "\n" +
                "      <option>Críquet</option>\n" +
                "\n" +
                "    </select>")
        registro.put("categoria","Html5")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","<img>")
        registro.put("descripcion","Etiqueta para mostrar una imagen en un documento html.")
        registro.put("descripcion_ingles","Renders an image in the document.")
        registro.put("ejemplo","<img src=\"/assets/images/dominica-flag-icon.gif\" alt=\"Dominica\">")
        registro.put("categoria","Html5")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","<div>")
        registro.put("descripcion","Define las divisiones existentes en el contenido de una página web.")
        registro.put("descripcion_ingles","Defines the existing logical divisions in the content of a web page.")
        registro.put("ejemplo","<h1>Tim Berners-Lee</h1>\n" +
                "\n" +
                "<div style=\"color: #040; font-style: italic\">\n" +
                "\n" +
                "  <p>Timothy \"Tim\" John Berners-Lee es un científico de la computación británico.</p>\n" +
                "\n" +
                "</div>")
        registro.put("categoria","Html5")
        bd.insert   ("termino", null, registro)

//<-------------------------------------------TERMINOS IONIC--------------------------------------------------->



        registro.put("nombre","ionic start")
        registro.put("descripcion","Comando utilizado para crea una aplicación Ionic que instala dependencias por usted y configura su proyecto.")
        registro.put("descripcion_ingles","Comand used to create an ionic proyect")
        registro.put("ejemplo","ionic start myApp blank")
        registro.put("categoria","Ionic")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","ionic serve")
        registro.put("descripcion","Activa fácilmente un servidor de desarrollo que se inicia en tu navegador")
        registro.put("descripcion_ingles","Used to start the ionic proyect and show it in a browser")
        registro.put("ejemplo","\$ ionic serve ")
        registro.put("categoria","Ionic")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","ion-text")
        registro.put("descripcion","Componente para implementar un texto en Ionic.")
        registro.put("descripcion_ingles","Used to create a text in ionic")
        registro.put("ejemplo","<ion-text color=\"primary\">\n" +
                "  <h1>H1: The quick brown fox jumps over the lazy dog</h1>\n" +
                "</ion-text>")
        registro.put("categoria","Ionic")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","ion-alert")
        registro.put("descripcion","Una alerta es un cuadro de diálogo que presenta a los usuarios información o recopila información del usuario mediante entradas.")
        registro.put("descripcion_ingles","It's a pop-up window where can do questions or shows variables")
        registro.put("ejemplo","<IonAlert>\n" +
                "</IonAlert>")
        registro.put("categoria","Ionic")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","ion-button")
        registro.put("descripcion","El componente Buttons es un elemento contenedor. Los botones colocados en una barra de herramientas deben colocarse dentro del <ion-buttons>elemento.")
        registro.put("descripcion_ingles","Component to create a button")
        registro.put("ejemplo","<ion-buttons slot=\"start\">\n" +
                "    <ion-back-button></ion-back-button>\n" +
                "  </ion-buttons>")
        registro.put("categoria","Ionic")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","ion-item")
        registro.put("descripcion","Contenedor de elementos como imagenes, avatares, iconos y texto.")
        registro.put("descripcion_ingles","Content for pictures,avatars,icons or text.")
        registro.put("ejemplo","<ion-item>\n" +
                "      <ion-label>Sliding Item with End Options</ion-label>\n" +
                "    </ion-item>")
        registro.put("categoria","Ionic")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","ion-menu")
        registro.put("descripcion","Componente para implementar un menu desplegable en una aplicacion Ionic.")
        registro.put("descripcion_ingles","Used to implement a slide-menu on the ionic app")
        registro.put("ejemplo","<ion-menu >\n" +
                "  </ion-menu>\n")
        registro.put("categoria","Ionic")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","ion-avatar")
        registro.put("descripcion","Los avatares son componentes circulares que normalmente envuelven una imagen o icono. Se pueden utilizar para representar a una persona o un objeto.")
        registro.put("descripcion_ingles","Componenet to create an avatar to represent a person or an object")
        registro.put("ejemplo","<ion-avatar>\n" +
                "  <img src=\" \" />\n" +
                "</ion-avatar>")
        registro.put("categoria","Ionic")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","ion-checkbox")
        registro.put("descripcion","Las casillas de verificación se pueden usar para que el usuario sepa que necesita tomar una decisión binaria.")
        registro.put("descripcion_ingles","It let the user check a box with a binary  desicion")
        registro.put("ejemplo","<ion-checkbox>\n" +
                "</ion-checkbox>\n")
        registro.put("categoria","Ionic")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","ion-infinite-scroll")
        registro.put("descripcion","El desplazamiento infinito le permite cargar nuevos datos a medida que el usuario se desplaza por su aplicación.")
        registro.put("descripcion_ingles","used to let the page scroll infinite")
        registro.put("ejemplo","<ion-infinite-scroll> </ion-infinite-scroll>")
        registro.put("categoria","Ionic")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","ion-segment")
        registro.put("descripcion","Los segmentos proporcionan un conjunto de botones exclusivos que se pueden usar como filtro o selector de vista.\n")
        registro.put("descripcion_ingles","Segments provide a set of unique buttons that can be used as a filter or view selectorSegments provide a set of unique buttons that can be used as a filter or view selector.")
        registro.put("ejemplo","<!-- Default Segment -->\n" +
                "<ion-segment (ionChange)=\"segmentChanged(\$event)\">\n" +
                "  <ion-segment-button value=\"friends\">\n" +
                "    <ion-label>Friends</ion-label>\n" +
                "  </ion-segment-button>")
        registro.put("categoria","Ionic")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","ion-refresher")
        registro.put("descripcion","Refresher proporciona la funcionalidad de extracción para actualizar en un componente de contenido.\n")
        registro.put("descripcion_ingles","Component that let update the page")
        registro.put("ejemplo","<ion-refresher slot=\"fixed\">\n" +
                "    <ion-refresher-content></ion-refresher-content>\n" +
                "  </ion-refresher>\n")
        registro.put("categoria","Ionic")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","ng-app")
        registro.put("descripcion","Esta es la marca que indica el elemento raíz de la aplicación. Es una directiva que autoarranca la aplicación web AngularJS.")
        registro.put("descripcion_ingles","Component to start angularJs")
        registro.put("ejemplo","<ng-app\n" +
                "  ng-app=\"angular.Module\"\n" +
                "  [ng-strict-di=\"boolean\"]>\n" +
                "...\n" +
                "</ng-app>")
        registro.put("categoria","Ionic")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","*ngIf")
        registro.put("descripcion","En Ionic es un condicional que implementa restricciones.")
        registro.put("descripcion_ingles","Used to create a conditional with restrictions")
        registro.put("ejemplo","<div *ngIf=\"condition\">Content to render when condition is true.</div>")
        registro.put("categoria","Ionic")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","Build")
        registro.put("descripcion","Compila una aplicación Angular en un directorio de salida llamado dist/ en la ruta de salida dada. Debe ejecutarse dentro de un directorio de espacio de trabajo.")
        registro.put("descripcion_ingles","Compile an Angular app in an output directory.")
        registro.put("ejemplo","ng build")
        registro.put("categoria","Ionic")
        bd.insert("termino",null,registro)

        registro.put("nombre","ion-card")
        registro.put("descripcion","Excelente manera de mostrar una parte importante del contenido y pueden contener imágenes, botones, texto y más.")
        registro.put("descripcion_ingles","It´s like a  content box where can contein  any component.")
        registro.put("ejemplo","<ion-card>\n" +
                "  <ion-card-header>\n" +
                "    <ion-card-subtitle>Card Subtitle</ion-card-subtitle>\n" +
                "    <ion-card-title>Card Title</ion-card-title>\n" +
                "  </ion-card-header>\n")
        registro.put("categoria","Ionic")
        bd.insert("termino",null,registro)

        registro.put("nombre","*ngFor")
        registro.put("descripcion","A directiva *ngFor genera muchos elementos repetidos")
        registro.put("descripcion_ingles","It's used  to make cycles de code with Angular")
        registro.put("ejemplo","<ion-item *ngFor=\"let item of items; trackBy:trackItems\">\n" +
                "  <ion-label>{{ item.value }}</ion-label>\n" +
                "</ion-item>")
        registro.put("categoria","Ionic")
        bd.insert("termino",null,registro)

        registro.put("nombre","<Ion-Footer> ")
        registro.put("descripcion","Etiqueta para agregar el pie de pagina de una aplicación con el framework de ionic.")
        registro.put("descripcion_ingles","It's the label footer for an app in the ionic framework.")
        registro.put("ejemplo","<!-- Footer without a border -->\n" +
                "<ion-footer class=\"ion-no-border\">\n" +
                "  <ion-toolbar>\n" +
                "    <ion-title>Footer - No Border</ion-title>\n" +
                "  </ion-toolbar>\n" +
                "</ion-footer>")
        registro.put("categoria","Ionic")
        bd.insert("termino",null,registro)

        registro.put("nombre","ng serve ")
        registro.put("descripcion","Construye su aplicación, e inicia con los cambios actualizados en los archivos.")
        registro.put("descripcion_ingles","Build and start the apllication  with file changes")
        registro.put("ejemplo","ng serve")
        registro.put("categoria","Ionic")
        bd.insert   ("termino", null, registro)



//<-------------------------------------------TERMINOS JAVAACRIPT--------------------------------------------------->


        registro.put("nombre","Funcion")
        registro.put("descripcion","Permite agrupar líneas de código en tareas con un nombre, para que, posteriormente,  hacer referencia a ese nombre para realizar todo lo que se agrupe en dicha tarea. Para usar la  funcion hay que hacer 2 cosas:")
        registro.put("descripcion_ingles","Let group lines of code at works with a name. then use that name to refer to that work")
        registro.put("ejemplo","//Declaración de la función \"saludar\"\n" +
                "function saludar() {\n" +
                "  // Contenido de la función\n" +
                "  console.log(\"Hola, soy una función\");\n" +
                "}\n" +
                "// Ejecución de la función\n" +
                "saludar();")
        registro.put("categoria","JavaScript")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","Generate")
        registro.put("descripcion","Es un objeto que sirve para decirle a`JavaScript`que nuestra función es un generador y se debe indicar con un asterisco de la siguiente forma:")
        registro.put("descripcion_ingles","It is an object used to tell `JavaScript` that our function is in a generator and should be indicated with an asterisk like this:")
        registro.put("ejemplo","\n" +
                "function* generador() { \n" +
                "   yield 1;\n" +
                "   yield 2;\n" +
                "   yield 3;\n" +
                "}\n" +
                "var g = generador();   // \"Generador { }\"    ")
        registro.put("categoria","JavaScript")
        bd.insert   ("termino", null, registro)

        registro.put("nombre","Click")
        registro.put("descripcion","Se activa cuando se presiona el botón del mouse y ejecuta una función que recibe como parámetro, dicha función recibe el evento.")
        registro.put("descripcion_ingles","Used to run some function when click it the button")
        registro.put("ejemplo","const button = document.getElementById(\"test\")\n" +
                "button.addEventListener(\"click\",function(event{\n" +
                "  console.log('hola mundo')\n" +
                "}\n")
        registro.put("categoria","JavaScript")
        bd.insert("termino", null, registro)

        registro.put("nombre","Const")
        registro.put("descripcion","Define una constante que no va a cambiar su valor durante el tiempo    ")
        registro.put("descripcion_ingles","it's used to declare variables where the value couldn't change, therefore it can't be declared more than one timeit's used to declare variables where the value couldn't change, therefore it can't be declared more than one time")
        registro.put("ejemplo","const nombre  = 'Juanito'")
        registro.put("categoria","JavaScript")
        bd.insert("termino", null, registro)

        registro.put("nombre","Constructor")
        registro.put("descripcion","El método constructor es un método especial para crear e inicializar un objeto creado a partir de una clase.")
        registro.put("descripcion_ingles","The constructor method is a special method to create and initialize an object created from a class.")
        registro.put("ejemplo","Sintaxis: constructor([arguments]) {...}")
        registro.put("categoria","JavaScript")
        bd.insert("termino", null, registro)

        registro.put("nombre","Document.getElementById")
        registro.put("descripcion","Devuelve el elemento por su id; id que se usa para identificar de forma única el elemento, que se encuentra en el html con el atributo`id`.")
        registro.put("descripcion_ingles","Used to return the id (differentiator) of an element")
        registro.put("ejemplo","<div id=\"ejemplo\"></div>\n" +
                "let element = document.getElementById('ejemplo');\n")
        registro.put("categoria","JavaScript")
        bd.insert("termino", null, registro)

        registro.put("nombre","Document.getElementsByClassName")
        registro.put("descripcion","Devuelve una colección que contiene todos los elementos que contenga la clase del atributo, por ejemplo.")
        registro.put("descripcion_ingles","Return a collection that contains the class of attribute")
        registro.put("ejemplo","<div class=\"ejemplo\"></div>\n" +
                "<div class=\"ejemplo\"></div>\n" +
                "var ejemplo= document.getElementsByClassName(\"ejemplo\")\n" +
                "  ejemplo[0].innerHTML = \"Soy la clase ejemplo del primer div!\"\n")
        registro.put("categoria","JavaScript")
        bd.insert("termino", null, registro)

        registro.put("nombre","Element.setAttribute")
        registro.put("descripcion","Agrega el valor de un atributo en el elemento especificado. Si el atributo ya existe, el valor se actualiza, en el caso contrario se agrega el atributo con el nombre y el valor especificado.")
        registro.put("descripcion_ingles","Adds the value of an attribute on the specified element")
        registro.put("ejemplo","element.setAttribute (nombre, value);\n" +
                "name // especifica el nombre del atributo que se va a establecer. string\n" +
                "value // contiene el valor a asignar al atributo. string\n")
        registro.put("categoria","JavaScript")
        bd.insert("termino", null, registro)

        registro.put("nombre","Event")
        registro.put("descripcion","Permiten la interacción entre las aplicaciones JavaScript y los usuarios. Cuando se toca un botón, cuando se pulsa una tecla determinada, se produce un evento. Sin embargo no todos los eventos los produce el usuario, cuando se carga una pagina también se produce un evento.")
        registro.put("descripcion_ingles","Let the interaction between the javascript apps and users")
        registro.put("categoria","JavaScript")
        bd.insert("termino", null, registro)

        registro.put("nombre","var")
        registro.put("descripcion","Son espacios de memoria donde almacenamos temporalmente datos, que podemos acceder en algún momento de la ejecución de nuestro código.")
        registro.put("descripcion_ingles","It's used to declare a variable(can be modified,updated and redeclared)")
        registro.put("ejemplo","`var Numero;`\n" +
                "`var numero = 5;`\n")
        registro.put("categoria","JavaScript")
        bd.insert("termino", null, registro)

        registro.put("nombre","Let")
        registro.put("descripcion","Se usa para declarar variables.")
        registro.put("descripcion_ingles","Used to declare variable")
        registro.put("ejemplo","let saludar = \"dice Hola\"\n" +
                "        \tconsole.log(hola);// \"dice Hola tambien\"")
        registro.put("categoria","JavaScript")
        bd.insert("termino", null, registro)

        registro.put("nombre","console.log")
        registro.put("descripcion","Se utiliza para mostrar texto o variables en la consola, función utilizada para los desarrolladores.  ")
        registro.put("descripcion_ingles","It's used to show a text or variables in the consolev")
        registro.put("ejemplo","console.log(edad)//19")
        registro.put("categoria","JavaScript")
        bd.insert("termino", null, registro)

        registro.put("nombre","alert")
        registro.put("descripcion","Muestra información o resultados de una operación por pantalla. También es usada para mostrar alertas de advertencia al usuario.")
        registro.put("descripcion_ingles","It's a pop-up window where can do questions or shows variablesit's a pop-up window where can do questions or shows variables")
        registro.put("ejemplo","let nombre = “Gabriel” \n" +
                "\talert(‘usted se llama: ‘+nombre)")
        registro.put("categoria","JavaScript")
        bd.insert("termino", null, registro)

        registro.put("nombre","<SCRIPT>")
        registro.put("descripcion","Atributo que sirve para indicar el lenguaje que estamos utilizando, así como la versión de este.")
        registro.put("descripcion_ingles","Indicate when the languaje begin and end")
        registro.put("ejemplo","< script language=\"javascript\">")
        registro.put("categoria","JavaScript")
        bd.insert("termino", null, registro)

        registro.put("nombre","Date")
        registro.put("descripcion","Permite  trabajar de forma fácil y práctica con fechas")
        registro.put("descripcion_ingles","Datatype used to hold in number the  year,month,day and hour")
        registro.put("ejemplo","variable date\n" +
                "const date = new Date();")
        registro.put("categoria","JavaScript")
        bd.insert("termino", null, registro)

        registro.put("nombre","push")
        registro.put("descripcion","Es usado para agregar un elemento al final de un arreglo.")
        registro.put("descripcion_ingles","Used to add an element in the bottom of  an arrangement")
        registro.put("ejemplo","const arr = ['First item', 'Second item', 'Third item'];\n" +
                "\n" +
                "arr.push('Fourth item');")
        registro.put("categoria","JavaScript")
        bd.insert("termino", null, registro)

        registro.put("nombre","toString")
        registro.put("descripcion","Retorna una cadena representando el código fuente de la función.")
        registro.put("descripcion_ingles","Return a string using the source code  from the function")
        registro.put("ejemplo","var objeto = new Object();\n" +
                "objeto.toString(); // Devuelve [object Object]")
        registro.put("categoria","JavaScript")
        bd.insert("termino", null, registro)

        registro.put("nombre","Number.parseInt")
        registro.put("descripcion","Analiza un argumento de cadena y devuelve un entero de la raíz o base especificada.")
        registro.put("descripcion_ingles","Analize an argument")
        registro.put("ejemplo","function roughScale(x, base) {\n" +
                "  const parsed = Number.parseInt(x, base);\n" +
                "  if (Number.isNaN(parsed)) {\n" +
                "    return 0;\n" +
                "  }\n" +
                "  return parsed * 100;\n" +
                "}\n")
        registro.put("categoria","JavaScript")
        bd.insert("termino", null, registro)

        registro.put("nombre","String")
        registro.put("descripcion","Se utiliza para representar datos textuales. Puedes crear cadenas simples utilizando comillas simples o dobles:")
        registro.put("descripcion_ingles","used to represent text")
        registro.put("ejemplo","'programacion'\n" +
                "\"dispositivos moviles\"")
        registro.put("categoria","JavaScript")
        bd.insert("termino", null, registro)

        registro.put("nombre","Array")
        registro.put("descripcion","Los arrays son objetos similares a una lista.")
        registro.put("descripcion_ingles","The  arrays are some like a list.")
        registro.put("ejemplo","let frutas = {\"Manzana\": \"verde\", \"Banana\":\"amarilla\"}")
        registro.put("categoria","JavaScript")
        bd.insert("termino", null, registro)



//<-------------------------------------------TERMINOS NODE.JS--------------------------------------------------->



        registro.put("nombre","Nodemon")
        registro.put("descripcion","Vigila la ejecución de la aplicación y la reinicia automáticamente para implementar los cambios que se van incluyendo en la aplicación.")
        registro.put("descripcion_ingles","Monitors the execution of the application and automatically restarts it to implement the changes that are included in the application.")
        registro.put("ejemplo","\$  npm install nodemon -g")
        registro.put("categoria","Node.js")
        bd.insert("termino", null, registro)

        registro.put("nombre","console.log")
        registro.put("descripcion","Función que muestra al desarrollador  la iteración del código para buscar o corregir errores")
        registro.put("descripcion_ingles","Function that shows the developer the iteration of code to find or fix bugs.")
        registro.put("ejemplo","<script>\n" +
                "var str = \"GeeksforGeeks\";\n" +
                "console.log(str);\n" +
                "</script>\n")
        registro.put("categoria","Node.js")
        bd.insert("termino", null, registro)

        registro.put("nombre","if")
        registro.put("descripcion","La sentencia if, evalúa si se cumple una condición para ejecutar una tarea específica.")
        registro.put("descripcion_ingles","The if statement evaluates whether a condition is met to execute a specific task.")
        registro.put("ejemplo","if (hour < 18) {\n" +
                "  greeting = \"Good day\";\n" +
                "}")
        registro.put("categoria","Node.js")
        bd.insert("termino", null, registro)

        registro.put("nombre","if/else")
        registro.put("descripcion","La sentencia else ejecuta un código alternativo si en la sentencia if no se cumple la condición, adicionando una tarea diferente.")
        registro.put("descripcion_ingles","the else statement executes alternative code if the condition is not met in the if statement, adding a different task.")
        registro.put("ejemplo","function testNum(a) {\n" +
                "  let result;\n" +
                "  if (a > 0) {\n" +
                "    result = 'positive';\n" +
                "  } else {\n" +
                "    result = 'NOT positive';\n" +
                "  }\n" +
                "  return result;\n" +
                "}")
        registro.put("categoria","Node.js")
        bd.insert("termino", null, registro)

        registro.put("nombre","Const")
        registro.put("descripcion","Se utiliza para declarar una constante es decir, toma un valor que no cambia durante toda la ejecución")
        registro.put("descripcion_ingles","It is used to declare a constant, that is, it takes a value that does not change during the entire execution.")
        registro.put("ejemplo","const number = 42;")
        registro.put("categoria","Node.js")
        bd.insert("termino", null, registro)

        registro.put("nombre","var")
        registro.put("descripcion","Permite declara una variable, opcionalmente se puede inicializar esta variable con un valor determinado. ")
        registro.put("descripcion_ingles","Allows you to declare a variable, you can optionally initialize this variable with a given value.")
        registro.put("ejemplo","var x = 5;\n" +
                "var y = 6;\n" +
                "var z = x + y;")
        registro.put("categoria","Node.js")
        bd.insert("termino", null, registro)

        registro.put("nombre","let")
        registro.put("descripcion","Permite declarar variables limitando su alcance al bloque, declaración, o expresión donde se está usando, let es una actualización de la función var en ejx.")
        registro.put("descripcion_ingles","Allows you to declare variables by limiting their scope to the block, statement, or expression in which they are being used. let is an update to the var function in ejx.")
        registro.put("ejemplo","let x = 1;\n" +
                "if (x === 1) {\n" +
                "  let x = 2;")
        registro.put("categoria","Node.js")
        bd.insert("termino", null, registro)

        registro.put("nombre","Express")
        registro.put("descripcion","Permite que se desarrollen funcionalidades tales como el enrutamiento, que se utiliza para almacenar información sobre redes que se encuentran conectadas y permite el trafico de información de forma sencilla")
        registro.put("descripcion_ingles","Allows the development of functionalities such as routing, which is used to store information about the networks that are connected and allows information traffic in a simple way")
        registro.put("ejemplo","var express = require('express');\n" +
                "var app = express();\n")
        registro.put("categoria","Node.js")
        bd.insert("termino", null, registro)

        registro.put("nombre","Body-Parser")
        registro.put("descripcion","Permite a la aplicación recibir datos que se pueden almacenar dentro de una variable.")
        registro.put("descripcion_ingles","Allows the application to receive data that can be stored inside a variable.")
        registro.put("ejemplo","const bodyParser = require('body-parser');\n" +
                "\n" +
                "app.use(bodyParser.json());")
        registro.put("categoria","Node.js")
        bd.insert("termino", null, registro)

        registro.put("nombre","Morgan")
        registro.put("descripcion","Permite capturar solicitudes HTTP para su posterior registro y seguimiento")
        registro.put("descripcion_ingles","Allows capturing HTTP requests for later logging and tracking.")
        registro.put("ejemplo","const morgan = require('morgan');\n")
        registro.put("categoria","Node.js")
        bd.insert("termino", null, registro)

        registro.put("nombre","Listen")
        registro.put("descripcion","Se utiliza para asignar el puerto al servidor del API")
        registro.put("descripcion_ingles","Used to assign the port to the server on which the API will run")
        registro.put("ejemplo","listen(8080);")
        registro.put("categoria","Node.js")
        bd.insert("termino", null, registro)

        registro.put("nombre","package.Json")
        registro.put("descripcion","Utilizado para almacenar las dependencias empleadas en una aplicación o un Api.")
        registro.put("descripcion_ingles","Used to store the dependencies used in an application or an API")
        registro.put("ejemplo","npm init -y")
        registro.put("categoria","Node.js")
        bd.insert("termino", null, registro)

        registro.put("nombre","Get")
        registro.put("descripcion","Método permite al cliente enviar información al servidor a través de la url")
        registro.put("descripcion_ingles","Method that allows the client to send information to the server through the url.")
        registro.put("ejemplo","{get prop() { . . . } }")
        registro.put("categoria","Node.js")
        bd.insert("termino", null, registro)

        registro.put("nombre","Post")
        registro.put("descripcion","(En una aplicación web) recibe parámetros que son enviados desde la web.\n" +
                "(En una API REST) Se utiliza para recibir cierta información o parámetros y realizar una tarea específica.")
        registro.put("descripcion_ingles","In a REST API it is used to receive certain information or parameters and perform a specific task.")
        registro.put("ejemplo","rutaMovimiento.post('/crearMovimiento',crearMovimiento),")
        registro.put("categoria","Node.js")
        bd.insert("termino", null, registro)

        registro.put("nombre","Try/catch")
        registro.put("descripcion","Función que se utiliza en caso de error inesperado en el código y lo captura para mandar un mensaje de error.")
        registro.put("descripcion_ingles","Function that is used in case of an unexpected error in the code and captures it to send an error message.")
        registro.put("ejemplo","try {\n" +
                "  adddlert(\"Welcome guest!\");\n" +
                "}\n" +
                "catch(err) {\n" +
                "  document.getElementById(\"demo\").innerHTML = err.message;\n" +
                "}")
        registro.put("categoria","Node.js")
        bd.insert("termino", null, registro)

        registro.put("nombre","Update")
        registro.put("descripcion","Permite la actualización de un dato")
        registro.put("descripcion_ingles","Allows you to update the data.")
        registro.put("ejemplo","sudo apt update")
        registro.put("categoria","Node.js")
        bd.insert("termino", null, registro)

        registro.put("nombre","router")
        registro.put("descripcion","Usada para crear manejadores de rutas montables y modulares.")
        registro.put("descripcion_ingles","It is used to create modular mountable route handlers.")
        registro.put("ejemplo","import { Router } from \"express\";")
        registro.put("categoria","Node.js")
        bd.insert("termino", null, registro)

        registro.put("nombre","Module.exports")
        registro.put("descripcion","Agrupan código que realiza cierta función y se puede importar en otro archivo donde se necesite.")
        registro.put("descripcion_ingles","They include code that performs a certain function and can be imported into another file where it is needed.")
        registro.put("ejemplo","export const program = mysql.define('Programas', {\n" +
                "    id_programa: {\n" +
                "        type: DataTypes.BIGINT,\n" +
                "        primaryKey:true\n" +
                "    }, \n" +
                "    nombre_programa : {\n" +
                "        type: DataTypes.STRING(50)\n" +
                "    },\n" +
                "},")
        registro.put("categoria","Node.js")
        bd.insert("termino", null, registro)

        registro.put("nombre","Urlencoded")
        registro.put("descripcion","El parámetro de opciones contiene varias propiedades como extender, inflar, limitar, verificar, etc")
        registro.put("descripcion_ingles","Options parameter that contains various properties like stretch, inflate, constrain, check, etc.")
        registro.put("ejemplo","app.use(express.urlencoded({extended:false}));")
        registro.put("categoria","Node.js")
        bd.insert("termino", null, registro)

        registro.put("nombre","require")
        registro.put("descripcion","lee un archivo JavaScript, ejecuta el archivo y luego procede a devolver el objeto de exportación")
        registro.put("descripcion_ingles","receives HTTP requests from a client, such as your browser, and provides an HTTP response, such as an HTML or JSON page from an API.")
        registro.put("ejemplo","const server = http.createServer(requestListener);")
        registro.put("categoria","Node.js")
        bd.insert("termino", null, registro)

        registro.put("nombre","require")
        registro.put("descripcion","Lee un archivo JavaScript, ejecuta el archivo y luego procede a devolver el objeto de exportación")
        registro.put("descripcion_ingles","Reads a JavaScript file, executes the file, and then proceeds to return the expor object.")
        registro.put("ejemplo","const http = require(\"http\");")
        registro.put("categoria","Node.js")
        bd.insert("termino",null,registro)
    }
}