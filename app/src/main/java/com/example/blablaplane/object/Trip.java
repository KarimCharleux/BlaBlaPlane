package com.example.blablaplane.object;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Trip {

    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final int id;
    private final int pilotId;
    private String departureDate;
    private String arrivalDate;
    private String departure;
    private String arrival;
    private Float price;

    public Trip(int id, String departureDate, String arrivalDate, String departure, String arrival, Float price, int pilot) {
        this.id = id;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
        this.pilotId = pilot;
    }

    public int getId() {
        return this.id;
    }

    public Date getDepartureDate() {
        try {
            return dateFormatter.parse(this.departureDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Date getArrivalDate() {
        try {
            return dateFormatter.parse(this.arrivalDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDeparture() {
        return this.departure;
    }

    public String getArrival() {
        return this.arrival;
    }

    /**
     * Get the price of the trip in the format "0,00€"
     *
     * @return String in the format "0,00€"
     */
    public String getPrice() {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        format.setMaximumFractionDigits(2);

        String formattedPrice = format.format(this.price);
        formattedPrice = formattedPrice.replace("€", "");
        formattedPrice = formattedPrice.trim();

        return formattedPrice + "€";
    }

    public int getPilotId() {
        return this.pilotId;
    }

    /**
     * Get the duration of the trip in hours and minutes
     *
     * @return String in the format "0h00"
     */
    public String getDuration() {
        long duration = getArrivalDate().getTime() - getDepartureDate().getTime();
        long hours = duration / 3600000;
        long minutes = (duration % 3600000) / 60000;
        // Add a 0 before the minutes if it's less than 10
        String minutesString = (minutes < 10) ? "0" + minutes : "" + minutes;

        return hours + "h" + minutesString;
    }

    /**
     * Get the departure time of the trip in hours and minutes
     *
     * @return String in the format "00:00"
     */
    public String getDepartureTime() {
        return dateFormatter.format(getDepartureDate()).substring(11, 16).replace(":", "h");
    }

    /**
     * Get the arrival time of the trip in hours and minutes
     *
     * @return String in the format "00:00"
     */
    public String getArrivalTime() {
        return dateFormatter.format(getArrivalDate()).substring(11, 16).replace(":", "h");
    }

    /**
     * Get the departure date of the trip in the format "Thu, 01 Jan"
     *
     * @return String in the format "Thu, 01 Jan"
     */
    public String getDate() {
        return dateFormatter.format(getDepartureDate()).substring(0, 10);
    }
}
