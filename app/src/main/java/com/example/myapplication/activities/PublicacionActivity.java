package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class PublicacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacion);

// Recupera los datos del Intent
        Intent intent = getIntent();
        if (intent != null) {
            String titulo = intent.getStringExtra("titulo");
            String descripcion = intent.getStringExtra("descripcion");
            String precio = intent.getStringExtra("precio");
            String estado = intent.getStringExtra("estado");

            // Ahora puedes utilizar los datos en tu actividad VerDatosPublicacion
            // Por ejemplo, mostrarlos en TextViews u otros elementos de la interfaz de usuario
            TextView textViewTitulo = findViewById(R.id.tv_titulo_publicacion);
            TextView textViewDescripcion = findViewById(R.id.tv_precio_publicacion);
            TextView textViewPrecio = findViewById(R.id.tv_descripcion_publicacion_campo);
            TextView textViewEstado = findViewById(R.id.tv_estado_publicacion_campo);

            textViewTitulo.setText(titulo);
            textViewDescripcion.setText(descripcion);
            textViewPrecio.setText(precio);
            textViewEstado.setText(estado);


        }

    }
}
