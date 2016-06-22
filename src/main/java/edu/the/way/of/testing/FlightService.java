package edu.the.way.of.testing;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pawel on 6/22/16.
 */
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public void addFlight(Flight flight) {
        flightRepository.save(flight);
    }

    public int getAvailableSeatsCount(String flightCode) {
        return getFlight(flightCode).getSeatsCount();
    }

    public void book(String flightCode, String seatNumber) {
        Flight flight = getFlight(flightCode);
        flight.bookSeat(seatNumber);
    }

    public Flight getFlight(String flightCode) {
        Flight flight = flightRepository.load(flightCode);
        if(flight == null) {
            throw new IllegalStateException("Unknow fligh ");
        }
        return flight;
    }

    public int numberOfFlights() {
        return flightRepository.count();
    }
}
