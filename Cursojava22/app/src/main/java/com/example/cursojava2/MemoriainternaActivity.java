package com.example.cursojava2;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MemoriainternaActivity extends AppCompatActivity {
    private EditText txt_bitacora;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoriainterna);


        txt_bitacora = (EditText) findViewById(R.id.txt_bitacora);
        String archivos [] = fileList();

        if (ArchivosExiste(archivos, "bitacora.txt")){
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("bitacora.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String bitacoraCompleta = "";

                while (linea != null){
                    bitacoraCompleta = bitacoraCompleta + linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                txt_bitacora.setText(bitacoraCompleta);
            }catch (IOException e){

            }
        }
    }

    private  boolean ArchivosExiste(String archivos [], String NombreArchivo){
        for(int i = 0; i < archivos.length; i++)
            if (NombreArchivo.equals(archivos[i]))
                return true;
        return false;
    }

    public void Guardar(View view){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("bitacora.txt", Activity.MODE_PRIVATE));
            archivo.write(txt_bitacora.getText().toString());
            archivo.flush();
        }catch (IOException e){

        }
        Toast.makeText(this, R.string.bitguardada, Toast.LENGTH_SHORT).show();
        finish();
    }
}

