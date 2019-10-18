package com.ybene.projects.httprequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView tvWebpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvWebpage = findViewById(R.id.activity_main_webpage);

        URL url;
        HttpURLConnection urlConnection = null;

        // Retire les exceptions liées à la sécurité réseau
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            url = new URL("http://www.google.fr/");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            try {
                readStream(in);
            } catch (IOException e) {
                Log.e("IOException", e.getMessage());
            }

            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            urlConnection.disconnect();
        }
    }

    private void readStream(InputStream in) throws IOException {

        // Buffer pour lire l'InputStream
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
        tvWebpage.setText(stringBuilder.toString());
    }
}
