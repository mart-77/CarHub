package com.example.myapplication.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.DBHelper;

public class CatalogoActivity extends AppCompatActivity {
    private static final String TAG = "Perfil";
    private DBHelper dbHelper;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);
        dbHelper = new DBHelper(this);

        Cursor cursor = dbHelper.getCatalogo();



        String [] fromColumns = {DBHelper.COLUMN_TITULO, DBHelper.COLUMN_PRECIO};
        int[] toViews = {R.id.tv_nombre_publicacion, R.id.tv_precio_publicacion};

        adapter = new SimpleCursorAdapter(
                this,
                R.layout.elemento_catalogo,
                cursor,
                fromColumns,
                toViews,
                0
        );

        ListView listView = findViewById(R.id.lv_publicaciones);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Cursor cursor = (Cursor) adapter.getItem(position);

                Log.d(TAG, "cursor" + cursor);

                // Extrae los datos del Cursor


                    String titulo = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_TITULO));
                    String descripcion = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_DESCRIPCION));
                    String precio = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PRECIO));
                    String estado = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_ESTADO));

                    // Crea un Intent para abrir la actividad VerDatosPublicacion
                    Intent intent = new Intent(CatalogoActivity.this, PublicacionActivity.class);
                    // Pasa los datos como extras al Intent
                    intent.putExtra("titulo", titulo);
                    intent.putExtra("descripcion", descripcion);
                    intent.putExtra("precio", precio);
                    intent.putExtra("estado", estado);

                    // Inicia la nueva actividad
                    startActivity(intent);

            }
        });

    }






}
