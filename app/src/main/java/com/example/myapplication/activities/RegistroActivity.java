package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.DBHelper;

public class RegistroActivity extends AppCompatActivity {
    private static final String TAG = "";
    private EditText campoMail;
    private EditText campoNombre;
    private EditText campoTelefono;
    private EditText campoPassword;
    private EditText campoPasswordConfirm;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        dbHelper = new DBHelper(this);

        campoMail = (EditText) findViewById(R.id.et_mail_register);
        campoNombre = (EditText) findViewById(R.id.et_nombre_register);
        campoTelefono = (EditText) findViewById(R.id.et_telefono_register);
        campoPassword = (EditText) findViewById(R.id.et_password_register);
        campoPasswordConfirm = (EditText) findViewById(R.id.et_p_r_confirm);
    }

    public void registrarCuenta (View view) {

        String mail = campoMail.getText().toString();
        String nombre = campoNombre.getText().toString();
        String telefono = campoTelefono.getText().toString();
        String password = campoPassword.getText().toString();
        String passwordConfirm = campoPasswordConfirm.getText().toString();

        if (mail.equals("") || nombre.equals("") || telefono.equals("") || password.equals("") || passwordConfirm.equals("")) {

        } else {

        if (password.equals(passwordConfirm)) {
            dbHelper.crearCuenta(nombre, mail, telefono, password);
            Log.d(TAG, "Cuanta Creada");

            Intent iniciarSesion = new Intent(this, MenuPrincipalActivity.class);
            startActivity(iniciarSesion);
        }
        }
    }



}
