package sena.yamboro.devtionary
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//para crear una base de datos se deve de agregar un nuevo kotlin class file.
//a la clase se le dan los parametros que esta requiere.
class DevTionaryBd(context: Context, name: String, factory:
SQLiteDatabase.CursorFactory?, Version: Int):

//SQLiteOpenHelper() permite crear,editar y actualizar la base de datos
SQLiteOpenHelper(context,name,factory,Version){

    //funcion principal, la cual recibe SQLiteDatabase por parametro
    override fun onCreate(db: SQLiteDatabase) {
        with(db){

            //execSQL() permite ejecutar codigo sql que no retorne parametros.
            //en este caso se ejecuta el codigo para crear las tablas o entidades.
            execSQL("create table termino(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre text, descripcion text, descripcion_ingles text,ejemplo text, categoria text)")
            execSQL("create table categoria(id_categoria INTEGER PRIMARY KEY AUTOINCREMENT, nombre text, descripcion text, descripcion_ingles text)")
        }
    }

    //onUpgrade() permite actualizar la base de datos, agregando nuevas tablas o columnas...
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}