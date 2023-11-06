package com.example.myapplication.data;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {
    String TAG = "DBHelper";
    private static final String DB_NAME = "carhubdb.sqlite";
    private static final int DB_VERSION = 1;
    private final Context context;
    private static final String TABLE_NAME = "usuario";
    private static final String COLUMN_EMAIL = "mail";
    private static final String COLUMN_PASSWORD = "password";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
    Log.d(TAG, "Entra tabla");

        db.execSQL("CREATE TABLE usuario (" +
                "                id_usuario INTEGER PRIMARY KEY ," +
                "                nombre VARCHAR NOT NULL," +
                "                mail VARCHAR NOT NULL," +
                "                telefono VARCHAR NOT NULL," +
                "                password VARCHAR NOT NULL" +
                ")"
        );
        db.execSQL("CREATE TABLE publicacion (" +
                "       id_publicacion INTEGER," +
                "       id_usuario INTEGER, " +
                "        imagen BLOB," +
                "        titulo VARCHAR," +
                "        descripcion VARCHAR," +
                "        estado VARCHAR," +
                "        precio VARCHAR," +
                "        fechaPublicacion DATE, " +
                "        CONSTRAINT Publicacion_pk PRIMARY KEY (id_publicacion,id_usuario)," +
                "       CONSTRAINT publicacion_FK FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)" +
                ")"
        );
        db.execSQL("CREATE TABLE comentario (" +
                "id_comentario INTEGER," +
                "id_publicacion INTEGER," +
                "id_usuario INTEGER," +
                "contComentario VARCHAR," +
                "fecha DATE," +
                "CONSTRAINT Comentario_pk PRIMARY KEY (id_comentario,id_publicacion,id_usuario)," +
                "CONSTRAINT comentario_FK FOREIGN KEY (id_publicacion,id_usuario) REFERENCES publicacion(id_publicacion,id_usuario)," +
                "CONSTRAINT comentario_FK_1 FOREIGN KEY (id_publicacion,id_usuario) REFERENCES publicacion(id_publicacion,id_usuario)" +
                ")"
        );
        db.execSQL("CREATE TABLE chat (" +
                "id_chat INTEGER," +
                "id_usuario INTEGER," +
                "usuarioChateado INTEGER," +
                "CONSTRAINT Chat_pk PRIMARY KEY (id_chat,id_usuario)," +
                "CONSTRAINT chat_FK FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)" +
                ")"
        );
        db.execSQL("CREATE TABLE mensaje (" +
                "id_mensaje INTEGER," +
                "id_chat INTEGER," +
                "usuarioChateado INTEGER," +
                "contMensaje VARCHAR," +
                "CONSTRAINT Mensaje_pk PRIMARY KEY (id_mensaje,id_chat,usuarioChateado)," +
                "CONSTRAINT mensaje_FK FOREIGN KEY (id_chat,id_mensaje,usuarioChateado) REFERENCES chat(id_chat,id_usuario,usuarioChateado)," +
                "CONSTRAINT mensaje_FK_1 FOREIGN KEY (id_chat,id_chat,usuarioChateado) REFERENCES chat(id_chat,id_usuario,usuarioChateado)" +
                ")"
        );
        db.execSQL("CREATE TABLE bookmarks (" +
                "id_bookmark SERIAL," +
                "id_usuario SERIAL," +
                "id_publicacion SERIAL," +
                "CONSTRAINT bookmarks_pk PRIMARY KEY (id_bookmark,id_usuario,id_publicacion)," +
                "CONSTRAINT bookmarks_FK FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)," +
                "CONSTRAINT bookmarks_FK_1 FOREIGN KEY (id_publicacion,id_usuario) REFERENCES publicacion(id_publicacion,id_usuario)" +
                ")"
        );


        Log.d(TAG, "Sale Tabla");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public synchronized void close() {
        super.close();
    }

    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    public SQLiteDatabase getReadableDatase(){
        return super.getReadableDatabase();
    }

    public boolean comprobarCredenciales(String email, String password) {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME  + "WHERE" + COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ? ";
        Cursor cursor = database.rawQuery(query, new String[]{email,password});

        if (cursor.getCount()> 0 ) {
            cursor.close();
            return true;

        } else {
            cursor.close();
            return false;

        }
    }


}




