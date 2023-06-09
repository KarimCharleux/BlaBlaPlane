package com.example.blablaplane.object.trip;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AirportFinder {
    private static final String GEONAMES_API_URL = "http://api.geonames.org/searchJSON";
    private static final String GEONAMES_USERNAME = "randomrandom";


    public static Airport findNearestAirport(LatLng latlng) {
        try {
            double latitude = latlng.latitude;
            double longitude = latlng.longitude;


            // Construire l'URL pour la requête API GeoNames en utilisant les coordonnées de l'utilisateur
            String searchQuery = "airports";
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
            JsonArray airportArray = jsonResponse.getAsJsonArray("geonames");

            if (airportArray != null && airportArray.size() > 0) {
                // Get the first airport from the array
                JsonObject airportObject = airportArray.get(0).getAsJsonObject();

                // Extract the necessary information
                String airportName = airportObject.get("name").getAsString();
                double airportLatitude = airportObject.get("lat").getAsDouble();
                double airportLongitude = airportObject.get("lng").getAsDouble();
                String airportCity = airportObject.get("toponymName").getAsString();
                String airportCountry = airportObject.get("countryName").getAsString();

                // Create an Airport object with the extracted information
                Airport nearestAirport = new Airport(airportName, airportLatitude, airportLongitude, airportCity, airportCountry);

                return nearestAirport;
            } else {
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
