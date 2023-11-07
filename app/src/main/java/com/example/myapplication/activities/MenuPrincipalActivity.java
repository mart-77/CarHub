package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }

    public void lanzarCatalogo (View view) {
        Intent lanzar = new Intent(this, CatalogoActivity.class);
        startActivity(lanzar);
    }

    public void lanzarSubirAnunio (View view) {
        Intent lanzar = new Intent(this, SubirAnuncioAcitivity.class);
        startActivity(lanzar);
    }

    public void lanzarBooksmarks (View view) {
        Intent lanzar = new Intent(this, BookmarksActivity.class);
        startActivity(lanzar);
    }

    public void lanzarPerfil (View view) {
        Intent lanzar = new Intent(this, PerfilActivity.class);
        startActivity(lanzar);
    }

    public void lanzarChat (View view) {
        Intent lanzar = new Intent(this, ChatActivity.class);
        startActivity(lanzar);
    }

    public void lanzarFAQ(View view) {
        Intent lanzar = new Intent(this, FAQActivity.class);
        startActivity(lanzar);
    }

    public void lanzarAcercaDe (View view) {
        Intent lanzar = new Intent(this, AcercaDeActivity.class);
        startActivity(lanzar);
    }

}
