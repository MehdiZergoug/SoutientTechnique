package com.example.semaine9_exercice1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    EditText et_nom,et_courriel;
    Button btn_sauvegarder;
    joueur j1;
    private String nomFichier="joueurs.json";
    private String chemin="'Player_Base";
    File monFichierExterne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_sauvegarder=findViewById(R.id.button);
        et_courriel=findViewById(R.id.editTextTextEmailAddress);
        et_nom=findViewById(R.id.editTextTextPersonName);
        TextView tv=findViewById(R.id.textView);
        btn_sauvegarder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                j1=new joueur(et_nom.getText().toString(),et_courriel.getText().toString(),0);
                //Pour utiliser Gson, on doit ajouter la ligne suivante dans dependencies de build.gradle(:app)
                //    implementation 'com.google.code.gson:gson:2.10'
                Gson gson2=new Gson();
                String jsonString=gson2.toJson(j1);;
                tv.setText(jsonString);
                FileInputStream fichierIn=null;
                try{
                    if(!stockageExterneDisponible()||stockageExterneLectureSeule()){
                        Toast.makeText(getApplicationContext(), "Le chemin n'existe pas", Toast.LENGTH_SHORT).show();
                    }else {
                        monFichierExterne= new File(getExternalFilesDir(chemin),nomFichier);
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

                            Gson gson = new Gson();
                            Writer writer = Files.newBufferedWriter(Paths.get(monFichierExterne.getPath()));
                            gson.toJson(j1, writer);
                            writer.close();
                        }
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
    private static boolean stockageExterneLectureSeule(){
        String extStorageState= Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)){
            return true;
        }
        return false;
    }
    private static boolean stockageExterneDisponible(){
        String extStorageState= Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(extStorageState)){
            return true;
        }
        return false;
    }
}