package com.example.myapplication.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.data.DBHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InicioActivity extends AppCompatActivity  {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        dbHelper = new DBHelper(this);

        // Obtener una instancia de la base de datos para escritura
        database = dbHelper.getWritableDatabase();

        // Realizar operaciones en la base de datos
        // Por ejemplo, puedes insertar, actualizar o consultar datos aquí

        // Cierra la base de datos cuando hayas terminado de usarla
        dbHelper.close();
    }

    public void cargarDatos(View view) throws ParseException {
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.openDatabase();

// Realiza la inserción de datos en la tabla

        String nombre = "Ejemplo";
        String mail = "Ejemplo";
        String telefono = "Ejemplo";
        String password = "Ejemplo";

        Byte imagen [] = null;
        String titulo = "Ejemplo";
        String descripcion = "ejemplo";
        String estado = "ejemplo";
        String precio = "ejemplo";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fecha= "2020-01-01";
        Date fechaPublicacion = sdf.parse(fecha);
        int id_usuario = 1;


        String insertQuery = "INSERT INTO usuario ( nombre,  mail, telefono, password) VALUES ( ?, ?, ?, ?)";
        db.execSQL(insertQuery, new Object[]{nombre,  mail, telefono, password});

        String insertQuery2 = "INSERT INTO publicacion (id_usuario, imagen, titulo, descripcion, estado, precio, fechaPublicacion) VALUES (?, ?, ?, ?,?,?,?)";
        db.execSQL(insertQuery2, new Object[]{id_usuario, imagen, titulo, descripcion, estado, precio, fechaPublicacion});
// Cierra la base de datos cuando hayas terminado de usarla

    }

}
