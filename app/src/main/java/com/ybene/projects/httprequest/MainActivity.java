package com.ybene.projects.httprequest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ybene.projects.httprequest.tools.CallWebAPI;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView tvWebpage;
    private Button buttonIPAPIActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvWebpage = findViewById(R.id.activity_main_webpage);
        buttonIPAPIActivity = findViewById(R.id.activity_main_button_ipapi);

        URL url;
        HttpURLConnection urlConnection = null;

        // Retire les exceptions liées à la sécurité réseau
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            url = new URL("http://www.google.fr/");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            // ======================

            // ===== PREMIERE IMPLEMENTATION =====
/*            try {
                readStream(in);
            } catch (IOException e) {
                Log.e("IOException", e.getMessage());
            }*/
            // ===================================

            // ===== DEUXIEME CORRECTION =====
            // readStream(in);
            // ===============================

            // ===== URL EN PARAMETRE =====
/*            URL newUrl = new URL("http://www.google.fr");
            CallWebAPI callWebAPI = new CallWebAPI(tvWebpage);
            callWebAPI.execute(newUrl.toString());*/
            // ============================

            in.close();
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            urlConnection.disconnect();
        }

        buttonIPAPIActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IPAPIActivity.class);
                startActivity(intent);
            }
        });
    }

    private void readStream(InputStream in) throws IOException {

        // ===== PREMIERE IMPLEMENTATION =====
 /*       // Buffer pour lire l'InputStream
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

        // Création de la chaine de caractères finale
        StringBuilder stringBuilder = new StringBuilder();

        // Chaine de caractères pour contenir une ligne
        String line = null;

        // Construction de la chaine de carcatères finale à partir des lignes
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        // Affichage du code HTML sur l'activité
        tvWebpage.setText(stringBuilder.toString());*/
        // ===================================

        // ===== DEUXIEME CORRECTION =====
        CallWebAPI callWebAPI = new CallWebAPI(tvWebpage);
        callWebAPI.execute();
        // ===============================

        in.close();
    }
}
