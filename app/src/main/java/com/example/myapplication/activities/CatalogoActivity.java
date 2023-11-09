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
        int[] toViews = {R.id.tv_titulo_publicacion, R.id.tv_precio_publicacion};

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
            public void onItemClick(AdapterView<?> parent, View view, int posicion, long l) {
                Log.i(TAG, "itemClick grupo" + posicion);
                Intent intent = new Intent(CatalogoActivity.this, PublicacionActivity.class);
                intent.putExtra("idGrupo", posicion);
                startActivity(intent);
            }
        });


    }






}
