package edu.the.way.of.testing;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pawel on 6/22/16.
 */
public class Flight {

    private String flightCode;
    private Map<String, Seat> seats = new HashMap<>();
    private String origin;
    private String destination;
    private LocalDate flightDate;
    private double cheapestSeatPrice;

    public Flight(String flightCode) {
        this.flightCode = flightCode;
    }

    public void addSeat(SeatClass seatClass, String seatNumber, double price) {
        Seat seat = new Seat(seatClass, seatNumber, price);
        seats.put(seatNumber, seat);
    }

    public int getSeatsCount() {
        return seats.size();
    }

    public Seat bookSeat(String seatNumber) {
        Seat seat = getSeat(seatNumber);
        if(!seat.isAvailable()) {
            throw new SeatAlreadyBookedException();
        }
        seat.book();
        return seat;
    }

    public Seat getSeat(String seatNumber) {
        Seat seat = seats.get(seatNumber);
        if(seat == null) {
            throw new UnknownSeatException();
        }
        return seat;
    }

    public boolean isFrom(String origin) {
        return this.origin.equalsIgnoreCase(origin);
    }

    public boolean isTo(String destination) {
        return this.destination.equalsIgnoreCase(destination);
    }

    public String getFlightCode() {
        return flightCode;
    }

    public Map<String, Seat> getSeats() {
        return seats;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getFlightDate() {
        return flightDate;
    }

    public void setSeats(Map<String, Seat> seats) {
        this.seats = seats;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setFlightDate(LocalDate flightDate) {
        this.flightDate = flightDate;
    }

    public int getAvailableSeatsLeft() {
        return (int)seats.values().stream().filter( s -> s.isAvailable()).count();
    }

    public double getTheLowestPrice() {
        return 0.0;
    }

    public double getCheapestSeatPrice() {
        return cheapestSeatPrice;
    }

    public double getAveragePriceOfAvailableSeats() {
        double priceSum = 0;
        int availableCount = 0;
        for(Seat seat : seats.values()) {

            if(seat.isAvailable()) {
                priceSum += seat.getPrice();
                availableCount++;
            }
        }
        return priceSum / availableCount;
    }


    public double getAveragePriceOfSeatsInClass(SeatClass seatClass) {
        double priceSum = 0;
        int availableCount = 0;
        for(Seat seat : seats.values()) {
            if(seat.isInClass(seatClass)) {
                priceSum += seat.getPrice();
                availableCount++;
            }
        }
        return priceSum / availableCount;
    }
}
