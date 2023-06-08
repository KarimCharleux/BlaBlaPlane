package com.example.blablaplane.object.trip;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Trip {
    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final int id;
    private final String pilotId;
    private final int aircraftId;
    private final Date departureDate;
    private final Date arrivalDate;
    private final City departure;
    private final City arrival;
    private final Float price;
    private int seatsLeft;

    public Trip() {
        this(null, null, null, null, null, null, 0, 0);
    }

    public Trip(Date departureDate, Date arrivalDate, City departure, City arrival, Float price, String pilot, int aircraftId, int seatsLeft) {
        this.id = createId();
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
        this.pilotId = pilot;
        this.seatsLeft = seatsLeft;
        this.aircraftId = aircraftId;
    }

    private int createId() {
        long currentTime = System.currentTimeMillis();
        int randomValue = (int) (Math.random() * 1000000);
        return (int) (currentTime + randomValue);
    }

    public int getAircraftId() {
        return this.aircraftId;
    }

    public int getSeatsLeft() {
        return this.seatsLeft;
    }

    public void bookSeats(int nbSeats) {
        if (this.seatsLeft >= nbSeats) {
            this.seatsLeft -= nbSeats;
        }
    }

    public int getId() {
        return this.id;
    }

    public Date getDepartureDate() {
        return this.departureDate;
    }

    public Date getArrivalDate() {
        return this.arrivalDate;
    }

    public City getDeparture() {
        return this.departure;
    }

    public City getArrival() {
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

    public String getPilotId() {
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
     * Get the departure date of the trip in the format "Samedi 01 Janvier 2021"
     *
     * @return String in the format "Samedi 01 Janvier 2021"
     */
    public String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE dd MMMM yyyy", new Locale("fr", "FR"));
        return simpleDateFormat.format(getDepartureDate()).substring(0, 1).toUpperCase() + simpleDateFormat.format(getDepartureDate()).substring(1);
    }
}