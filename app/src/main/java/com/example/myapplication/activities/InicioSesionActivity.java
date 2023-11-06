package com.example.myapplication.activities;

import static com.example.myapplication.data.DBHelper.*;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.DBHelper;
import com.example.myapplication.data.Usuario;

public class InicioSesionActivity extends AppCompatActivity {
    private EditText campoEmail;
    private EditText campoPassword;
    private static final String TAG = "Inicio";

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        dbHelper = new DBHelper(this);


        campoEmail = (EditText) findViewById(R.id.et_email);
        campoPassword = (EditText) findViewById(R.id.et_password);
    }

    public void iniciarSesion(View view) {
        String email = campoEmail.getText().toString();
        String password = campoPassword.getText().toString();

        if (dbHelper.comprobarCredenciales(email, password)) {
            Log.d(TAG, "Correcto");
        } else {
            Log.d(TAG, "Incorrecto");

        }


    }


}
