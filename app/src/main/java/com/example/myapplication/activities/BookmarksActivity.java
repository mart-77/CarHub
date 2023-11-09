package com.example.myapplication.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.DBHelper;

public class BookmarksActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private ListView listView;
    private SimpleCursorAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        dbHelper = new DBHelper(this);
        listView = findViewById(R.id.lv_bookmark);

        // Obtener todas las publicaciones favoritas
        Cursor cursor = dbHelper.obtenerTodasLasPublicacionesFavoritas();

        // Definir las columnas que deseas mostrar en el ListView
        String[] columnas = new String[]{DBHelper.COLUMN_ID_PUBLICACION};

        // Definir los IDs de los elementos de la interfaz a los que deseas asignar los datos
        int[] ids = new int[]{android.R.id.text1};

        // Crear un adaptador para el ListView
        adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                cursor,
                columnas,
                ids,
                0
        );

        // Establecer el adaptador en el ListView
        listView.setAdapter(adapter);

        
    }


    }



