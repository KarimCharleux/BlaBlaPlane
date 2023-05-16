package com.example.blablaplane.object.trip;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class CitySuggestion {

    
    

    public static ArrayList<City> searchingClosestCity(String cityInput, int nbCities) {
        ArrayList<City> citiesSuggestion = new ArrayList<>();
        HashMap<Integer,ArrayList<String>> citiesDistance = new HashMap<>();
        for (City city : City.values()) {
            String cityFormatted = city.name().toLowerCase();
            if (cityFormatted.length()>=cityInput.length()) {
                int distance = distanceLevenshtein(cityFormatted, cityInput.toLowerCase());
                if (citiesDistance.containsKey(distance)) {
                    citiesDistance.get(distance).add(cityFormatted);
                } else {
                    ArrayList<String> cities = new ArrayList<>();
                    cities.add(cityFormatted);
                    citiesDistance.put(distance, cities);
                }
            }
        }
        ArrayList<Integer> allDistances = new ArrayList<>(citiesDistance.keySet());
        Collections.sort(allDistances);
        for (int i = 0; i < nbCities; i++) {
            for (String city : citiesDistance.get(allDistances.get(i))) {
                citiesSuggestion.add(City.valueOf(city.toUpperCase()));
            }
        }
        return citiesSuggestion;
    }

    private static int distanceLevenshtein(String cityName, String cityInput) {
        int sizeDiff = Math.abs(cityName.length() - cityInput.length());
        int distance = 0;
        ArrayList<Integer> allDistances = new ArrayList<>();
        for (int x = 0; x < cityName.length()-cityInput.length()-1; x++) {
            for(int i = 0; i < cityName.length(); i++) {
                for(int j = 0; j < cityInput.length(); j++) {
                    distance = distance + Math.abs(cityName.charAt(i) - cityInput.charAt(j));
                }
            }
            allDistances.add(distance);
        }
        return Collections.min(allDistances);
    }
}
