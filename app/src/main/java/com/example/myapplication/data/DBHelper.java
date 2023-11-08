package com.example.myapplication.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    String TAG = "DBHelper";
    private static final String DB_NAME = "carhubdb.sqlite";
    private static final int DB_VERSION = 1;
    private final Context context;
    private static final String TABLE_NAME = "usuario";
    private static final String COLUMN_ID = "id_usuario";
    private static final String COLUMN_EMAIL = "mail";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_NOMBRE = "nombre";
    private static long usuarioLogueado;



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
                "       id_publicacion INTEGER PRIMARY KEY," +
                "       id_usuario INTEGER, " +
                "        imagen BLOB," +
                "        titulo VARCHAR," +
                "        descripcion VARCHAR," +
                "        estado VARCHAR," +
                "        precio VARCHAR," +
                "        fechaPublicacion DATE, " +
                "       CONSTRAINT publicacion_FK FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)" +
                ")"
        );
        db.execSQL("CREATE TABLE comentario (" +
                "id_comentario INTEGER PRIMARY KEY," +
                "id_publicacion INTEGER," +
                "id_usuario INTEGER," +
                "contComentario VARCHAR," +
                "fecha DATE," +
                "CONSTRAINT comentario_FK FOREIGN KEY (id_publicacion,id_usuario) REFERENCES publicacion(id_publicacion,id_usuario)," +
                "CONSTRAINT comentario_FK_1 FOREIGN KEY (id_publicacion,id_usuario) REFERENCES publicacion(id_publicacion,id_usuario)" +
                ")"
        );
        db.execSQL("CREATE TABLE chat (" +
                "id_chat INTEGER PRIMARY KEY," +
                "id_usuario INTEGER," +
                "usuarioChateado INTEGER," +
                "CONSTRAINT chat_FK FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)" +
                ")"
        );
        db.execSQL("CREATE TABLE mensaje (" +
                "id_mensaje INTEGER PRIMARY KEY," +
                "id_chat INTEGER," +
                "usuarioChateado INTEGER," +
                "contMensaje VARCHAR," +
                "CONSTRAINT mensaje_FK FOREIGN KEY (id_chat,id_mensaje,usuarioChateado) REFERENCES chat(id_chat,id_usuario,usuarioChateado)," +
                "CONSTRAINT mensaje_FK_1 FOREIGN KEY (id_chat,id_chat,usuarioChateado) REFERENCES chat(id_chat,id_usuario,usuarioChateado)" +
                ")"
        );
        db.execSQL("CREATE TABLE bookmarks (" +
                "id_bookmark INTEGER PRIMARY KEY," +
                "id_usuario INTEGER," +
                "id_publicacion INTEGER," +
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

    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    public boolean comprobarCredenciales(String email, String password) {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME  + " WHERE " + COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ? ";
        Cursor cursor = database.rawQuery(query, new String[]{email,password});

        if (cursor.getCount()> 0 ) {
            cursor.close();
            return true;

        } else {
            cursor.close();
            return false;

        }
    }

    public void crearCuenta (String nombre, String mail, String telefono, String password) {
        SQLiteDatabase database = this.getReadableDatabase();
        String insertQuery = "INSERT INTO usuario (nombre, mail, telefono, password) VALUES ( ?, ?, ?, ?)";
        database.execSQL(insertQuery, new Object[]{nombre, mail, telefono, password});
    }



//    Usuario


    public long getUsuarioID (String mail) {
        SQLiteDatabase database = this.getReadableDatabase();
        String[] columns = {COLUMN_ID};
        String selection = COLUMN_EMAIL + " = ?";
        String[] selectionArgs = {mail} ;

        Cursor cursor = database.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        long userID = -1;
        if (cursor.moveToFirst()) {
            userID = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));

        }
        cursor.close();
        return userID;
    }

    public static void setUsuarioLogueado(long userID) {
        usuarioLogueado = userID;
    }

    public static long getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void subirAnuncio (Long usuarioID, String titulo, String descripcion, String estado, String precio, byte[] imagenBytes) {
        SQLiteDatabase database = this.getReadableDatabase();
        String insertQuery = "INSERT INTO publicacion (id_usuario, titulo, descripcion, estado, precio, imagen) VALUES (?, ?, ? , ? , ?, ?)";
        database.execSQL(insertQuery, new Object[]{usuarioID, titulo, descripcion, estado, precio, imagenBytes});
    }
}




