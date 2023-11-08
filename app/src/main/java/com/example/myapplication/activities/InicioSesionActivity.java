package com.example.myapplication.activities;

import static com.example.myapplication.data.DBHelper.*;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

        if (email.equals("") || password.equals("")) {
            Log.d(TAG, "Campos obligatorios");
            Toast.makeText(this, "Porfavor, llenar todos los campos.", Toast.LENGTH_SHORT).show();

        } else {

            if (dbHelper.comprobarCredenciales(email, password)) {
                Log.d(TAG, "Correcto");
                setUsuarioLogueado();
                Intent iniciarSesion = new Intent(this, MenuPrincipalActivity.class);
                startActivity(iniciarSesion);
                Toast.makeText(this, "Correcto. Accediendo al sistema...", Toast.LENGTH_SHORT).show();

            } else {
                Log.d(TAG, "Incorrecto");
                Toast.makeText(this, "Credenciales incorrectas.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void setUsuarioLogueado (){
        String email = campoEmail.getText().toString();
        DBHelper.setUsuarioLogueado(dbHelper.getUsuarioID(email));
        long userId = dbHelper.getUsuarioID(email);
        Log.d(TAG, "Usuario: " + userId);

    }
}
