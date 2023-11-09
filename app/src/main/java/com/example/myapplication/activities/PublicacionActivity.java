package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.DBHelper;

public class PublicacionActivity extends AppCompatActivity {
    private DBHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacion);
        dbHelper = new DBHelper(this);

// Recupera los datos del Intent
        Intent intent = getIntent();
        if (intent != null) {
            String titulo = intent.getStringExtra("titulo");
            String descripcion = intent.getStringExtra("descripcion");
            String precio = intent.getStringExtra("precio");
            String estado = intent.getStringExtra("estado");

            // Ahora puedes utilizar los datos en tu actividad VerDatosPublicacion
            // Por ejemplo, mostrarlos en TextViews u otros elementos de la interfaz de usuario
            TextView textViewTitulo = findViewById(R.id.anuncio_titulo);
            TextView textViewDescripcion = findViewById(R.id.anuncio_descipcion);
            TextView textViewPrecio = findViewById(R.id.anuncio_precio);
            TextView textViewEstado = findViewById(R.id.anuncio_estado);

            textViewTitulo.setText(titulo);
            textViewDescripcion.setText(descripcion);
            textViewPrecio.setText(precio);
            textViewEstado.setText(estado);



            ImageView botonFavorito = findViewById(R.id.boton_agregar_favorito);
            botonFavorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Obtener la ID de la publicación actual (deberías tener una manera de obtenerla)
                    long usuarioID = DBHelper.getUsuarioLogueado();
                    long publicacionId = Long.parseLong(DBHelper.COLUMN_ID_PUBLICACION);

                    // Agregar a favoritos
                    dbHelper.agregarFavorito(publicacionId, usuarioID);

                }
            });




        }

    }
}
