package com.example.myapplication.activities;

import static com.example.myapplication.data.DBHelper.COLUMN_EMAIL;
import static com.example.myapplication.data.DBHelper.COLUMN_NOMBRE;
import static com.example.myapplication.data.DBHelper.COLUMN_TEL;
import static com.example.myapplication.data.DBHelper.getUsuarioLogueado;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.DBHelper;

public class PerfilActivity extends AppCompatActivity {
    private static final String TAG = "Perfil";
    private DBHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        long userId = DBHelper.getUsuarioLogueado();
        Cursor cursor = dbHelper.getUsuarioData(userId);
        if (cursor != null && cursor.moveToFirst()){

            String nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE));
            String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
            String telefono = cursor.getString(cursor.getColumnIndex(COLUMN_TEL));

        }
    }

    TextView campoNombre = findViewById(R.id.tv_nombre);
    TextView campoCorreo = findViewById(R.id.tv_correo);
    TextView campoTelefono = findViewById(R.id.tv_telefono);

            campoNombre.setText(nombre);
            campoCorreo.setText(email);
            campoTelefono.setText(telefono);

    }

