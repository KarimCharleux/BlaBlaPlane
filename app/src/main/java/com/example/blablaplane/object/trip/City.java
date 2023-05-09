package com.example.blablaplane.object.trip;

import org.osmdroid.util.GeoPoint;

/**
 * Cities available for the trips
 */
public enum City {
    PARIS("Paris", "PAR", "France", new GeoPoint(48.856614, 2.3522219)),
    NICE("Nice", "NCE", "France", new GeoPoint(43.710173, 7.2619532)),
    LYON("Lyon", "LYS", "France", new GeoPoint(45.764043, 4.835659)),
    MARSEILLE("Marseille", "MRS", "France", new GeoPoint(43.296482, 5.36978)),
    TOULOUSE("Toulouse", "TLS", "France", new GeoPoint(43.604652, 1.444209)),
    BORDEAUX("Bordeaux", "BOD", "France", new GeoPoint(44.837789, -0.57918)),
    NANTES("Nantes", "NTE", "France", new GeoPoint(47.218371, -1.553621)),
    LILLE("Lille", "LIL", "France", new GeoPoint(50.62925, 3.057256)),
    STRASBOURG("Strasbourg", "SXB", "France", new GeoPoint(48.5734053, 7.7521113)),
    MONTPELLIER("Montpellier", "MPL", "France", new GeoPoint(43.610769, 3.876716)),
    RENNES("Rennes", "RNS", "France", new GeoPoint(48.117266, -1.6777926)),
    GRENOBLE("Grenoble", "GNB", "France", new GeoPoint(45.188529, 5.724523)),
    REIMS("Reims", "RHE", "France", new GeoPoint(49.258329, 4.031696)),
    PISE("Pise", "PSA", "Italie", new GeoPoint(43.722839, 10.401689)),
    BERLIN("Berlin", "BER", "Allemagne", new GeoPoint(52.520008, 13.404954)),
    AMSTERDAM("Amsterdam", "AMS", "Pays-Bas", new GeoPoint(52.370216, 4.895168));

    private final String cityName;
    private final String cityCode;
    private final String country;
    private final GeoPoint geoPoint;

    City(String cityName, String cityCode, String country, GeoPoint geoPoint) {
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.country = country;
        this.geoPoint = geoPoint;
    }

    public static City getCityByName(String cityName) {
        for (City city : City.values()) {
            if (city.getCityName().equals(cityName)) {
                return city;
            }
        }
        return null;
    }

    public String getCountry() {
        return country;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }
}
