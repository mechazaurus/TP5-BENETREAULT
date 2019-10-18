package com.ybene.projects.httprequest.tools;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.ybene.projects.httprequest.model.GeoIP;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallWebAPI extends AsyncTask<String, String, String> {

    private TextView textView;

    public CallWebAPI(TextView textView) {
        this.textView = textView;
    }

    protected String doInBackground(String... params) {

        // ===== HTTP REQUEST =====
        String inputLine = "";
        StringBuilder stringBuilder = null;

        URL url;

        try {

            if (params.length >= 1) {
                url = new URL(params[0]);
            } else {
                url = new URL("http://google.fr");
            }

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            // ===== PARSING XML =====
            XmlPullParserFactory pullParserFactory;
            try {
                pullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = pullParserFactory.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(in, null);
                String result = parseXML(parser).toString();

                in.close();
                return result;
            } catch (XmlPullParserException e) {
                Log.e("XmlPullParserException", e.getMessage());
            } catch (IOException e) {
                Log.e("IOException", e.getMessage());
            }
            // =======================

            // ===== PREMIERE IMPLEMENTATION =====
/*          stringBuilder = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            in.close();
            return stringBuilder.toString();*/
            // ===================================

        } catch (IOException e) {

        }

        return "error";
        // ========================
    }

    protected void onPostExecute(String result) {
        textView.setText(result);
    }

    private GeoIP parseXML(XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.getEventType();

        GeoIP geoIP = new GeoIP();

        // Pour passer la première balise query
        boolean firstQuery = true;

        while(eventType != XmlPullParser.END_DOCUMENT) {
            String name = null;

            switch (eventType) {

                case XmlPullParser.START_TAG:

                    name = parser.getName();

                    // Pour passer la première balise query
                    if (firstQuery && name.equals("query")) {
                        firstQuery = false;
                        parser.next();
                    } else if (name.equals("Error")) {
                        Log.e("XmlPullParser Start Tag", "Web API error...");
                    } else if (name.equals("countryCode")) {
                        geoIP.setCountryCode(parser.nextText());
                    } else if (name.equals("query")) {
                        geoIP.setQuery(parser.nextText());
                    } else if (name.equals("country")) {
                        geoIP.setCountry(parser.nextText());
                    } else if (name.equals("status")) {
                        geoIP.setStatus(parser.nextText());
                    } else if (name.equals("region")) {
                        geoIP.setRegion(parser.nextText());
                    }

                    break;

                case XmlPullParser.END_DOCUMENT:
                    break;
            }

            eventType = parser.next();
        }

        return geoIP;
    }
}
