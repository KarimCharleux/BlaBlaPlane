package com.example.blablaplane.object.trip;

import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import static android.app.PendingIntent.getActivity;

public class CityFinder {

    private static final String GEONAMES_API_URL = "http://api.geonames.org/searchJSON";
    private static final String GEONAMES_USERNAME = "randomrandom";

    public static String findNearestCity(LatLng latLng) {
        try {
            double latitude = latLng.latitude;
            double longitude = latLng.longitude;

            String searchQuery = "findNearbyJSON";
            String urlStr = GEONAMES_API_URL + "?q=" + searchQuery + "&lat=" + latitude + "&lng=" + longitude + "&username=" + GEONAMES_USERNAME;

            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            // Parse the response JSON using Gson
            JsonParser parser = new JsonParser();
            JsonObject jsonResponse = parser.parse(response.toString()).getAsJsonObject();
            JsonArray geonamesArray = jsonResponse.getAsJsonArray("geonames");

            if (geonamesArray != null && geonamesArray.size() > 0) {
                JsonObject geonamesObject = geonamesArray.get(0).getAsJsonObject();
                String city = geonamesObject.get("name").getAsString();
                return city;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
