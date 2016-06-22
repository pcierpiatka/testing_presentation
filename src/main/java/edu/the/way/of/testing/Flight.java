package edu.the.way.of.testing;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by pawel on 6/22/16.
 */
public class Flight {

    private String flightCode;
    private Map<String, Seat> seats = new HashMap<>();
    private String origin;
    private String destination;
    private Date flightDate;

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

    public void bookSeat(String seatNumber) {
        Seat seat = getSeat(seatNumber);
        if(!seat.isAvailable()) {
            throw new IllegalStateException("Seat is not avaiable");
        }
        seat.book();
    }

    public Seat getSeat(String seatNumber) {
        Seat seat = seats.get(seatNumber);
        if(seat == null) {
            throw new IllegalStateException("There is no such seat available");
        }
        return seat;
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

    public Date getFlightDate() {
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

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public int getAvailableSeatsLeft() {
        return (int)seats.values().stream().filter( s -> s.isAvailable()).count();
    }
}
