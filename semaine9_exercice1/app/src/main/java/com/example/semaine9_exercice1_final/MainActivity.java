package com.example.semaine9_exercice1_final;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainActivity extends AppCompatActivity {
    EditText et_nom,et_courriel;
    Button btn_sauvegarder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_sauvegarder=findViewById(R.id.button);
        et_courriel=findViewById(R.id.editTextTextEmailAddress);
        et_nom=findViewById(R.id.editTextTextPersonName);
        btn_sauvegarder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                joueur j1=new joueur(et_nom.getText().toString(),et_courriel.getText().toString(),0);
                //Pour utiliser Gson, on doit ajouter la ligne suivante dans dependencies de build.gradle(:app)
                //    implementation 'com.google.code.gson:gson:2.10'
                try{
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        Gson gson=new Gson();
                       Writer writer= Files.newBufferedWriter(Paths.get("joueurs.json"));
                        // Writer writer= Files.newBufferedWriter(getApplication().getFileStreamPath(FILE_PATH).toPath());
                        gson.toJson(j1,writer);
                        writer.close();
                    }

                }catch (IOException e){
                    Log.d("Error","sauvegarde non fonctionnel");
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "Sauvegarde Réussi", Toast.LENGTH_SHORT).show();
                et_courriel.setText("");
                et_nom.setText("");
            }
        });

    }
}