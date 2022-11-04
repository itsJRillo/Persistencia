package com.example.persistencia;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView mensaje = findViewById(R.id.message);
        TextView contenido = findViewById(R.id.contenido);

        File dir = this.getFilesDir();
        String filePath = dir + "/dades.txt";

        // Extraer la info del archivo y escribir en el archivo
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while (line) {
                System.out.println(line);
                contenido.setText(line);
            }

            br.close();
            fr.close();

            OutputStream os = openFileOutput("dades.txt", MODE_PRIVATE);
            EditText editText = findViewById(R.id.editFile);

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    mensaje.setText(charSequence);
                    try {
                        os.write(charSequence.toString().getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}