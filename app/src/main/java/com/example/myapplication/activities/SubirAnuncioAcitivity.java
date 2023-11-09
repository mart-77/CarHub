package com.example.myapplication.activities;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication.R;
import com.example.myapplication.data.DBHelper;

import java.io.ByteArrayOutputStream;

public class SubirAnuncioAcitivity extends AppCompatActivity {
    private static final String TAG = "";
    private static final int CODIGO_DE_PERMISO = 123;
    private EditText campoTitulo;
    private EditText campoDescripcion;
    private EditText campoEstado;
    private EditText campoPrecio;
    private Button campoImagen;
    private static final int seleccionarImagen = 1;
    private Uri imagenUri;
    private byte[] imagenBytes;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_anuncio);
        dbHelper = new DBHelper(this);

        campoTitulo = (EditText) findViewById(R.id.titulo_vehiculo_input);
        campoDescripcion = (EditText) findViewById(R.id.descripcion_anuncio_input);
        campoEstado = (EditText) findViewById(R.id.estado_anuncio_input);
        campoPrecio = (EditText) findViewById(R.id.precio_anuncio_input);
        campoImagen = (Button) findViewById(R.id.btn_subir_imagen);
        campoImagen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                seleccionarImagenGaleria();
            }
        });
    }

    public void seleccionarImagenGaleria() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CODIGO_DE_PERMISO);

            Log.d(TAG, "No tengo permiso");
//            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            startActivityForResult(intent, seleccionarImagen);
        } else {
            Log.d(TAG, "Pido");
        }
    }

// Solicitar permisos


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODIGO_DE_PERMISO) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso otorgado; puedes realizar la acción que requiere permisos.
                Log.d(TAG, "Entra permiso otorgaoo");

            } else {
                // Permiso denegado; informa al usuario o ajusta el flujo de tu aplicación según sea necesario.
                Log.d(TAG, "Entra permiso denegado");

            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "entra seleccion");

        if (requestCode == seleccionarImagen && resultCode == RESULT_OK) {
            Log.d(TAG, "entra seleccion1");

            if (data != null) {

                Log.d(TAG, "entra seleccion2");
                // La imagen ha sido seleccionada desde la galería
                imagenUri = data.getData();

                String[] projection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(imagenUri, projection, null, null, null);

                if (cursor != null && cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    String filePath = cursor.getString(columnIndex);

                    // Ahora 'filePath' contiene la ruta del archivo seleccionado desde la galería
                    cursor.close();

                    // Puedes usar 'filePath' para cargar la imagen, convertirla en bytes y guardarla en la base de datos
                    Bitmap bitmap = BitmapFactory.decodeFile((filePath)); // Reemplaza con la ruta de tu imagen
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    imagenBytes = stream.toByteArray();
                }
            }
        }

        Log.d(TAG, "Se guardo");

    }

    public void subirAnuncio(View view) {
        Long usuarioID = DBHelper.getUsuarioLogueado();
        String titulo = campoTitulo.getText().toString();
        String descripcion = campoDescripcion.getText().toString();
        String estado = campoEstado.getText().toString();
        String precio = campoPrecio.getText().toString();


        if (titulo.equals("") || descripcion.equals("") || estado.equals("") || precio.equals("")) {
            Toast.makeText(this, "Todos los campos deben estar completos!", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues values = new ContentValues();
            values.put("imagen", imagenBytes);
            try {
                dbHelper.subirAnuncio(usuarioID, titulo, descripcion, estado, precio, imagenBytes);
                Log.d(TAG, "Anincio publicado");
                Intent iniciarSesion = new Intent(this, MenuPrincipalActivity.class);
                startActivity(iniciarSesion);
                Toast.makeText(this, "Anuncio Publicado", Toast.LENGTH_SHORT).show();
            } catch (NumberFormatException e) {
                Log.d(TAG, "Error");
            }
        }
    }


}
