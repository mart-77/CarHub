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

        // Cierra la base de datos cuando hayas terminado de usarla

    }


    public void lanzarInicioSesion(View view) {
        Intent intentCrearCuenta = new Intent( this, InicioSesionActivity.class );
        startActivity( intentCrearCuenta );
    }

    public void lanzarRegistro(View view) {
        Intent intentCrearCuenta = new Intent( this, RegistroActivity.class );
        startActivity( intentCrearCuenta );
    }


}
