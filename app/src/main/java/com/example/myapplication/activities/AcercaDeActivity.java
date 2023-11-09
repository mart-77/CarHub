package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class AcercaDeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
    }

    public void lanzarFAQ(View view) {
        Intent lanzar = new Intent(this, FAQActivity.class);
        startActivity(lanzar);
    }

    public void lanzarChat (View view) {
        Intent lanzar = new Intent(this, ChatActivity.class);
        startActivity(lanzar);
    }

    public void lanzarMain (View view) {
        Intent lanzar = new Intent(this, MenuPrincipalActivity.class);
        startActivity(lanzar);
    }
}
