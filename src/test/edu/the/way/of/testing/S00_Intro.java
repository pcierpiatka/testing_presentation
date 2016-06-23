package edu.the.way.of.testing;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pawel on 6/22/16.
 */
public class S00_Intro {

    @Test
    public void unitTest() {
        Flight flight = new Flight("DCP0001");
        Map<String, Seat> seats = new HashMap<>();
        seats.put("001", new Seat(SeatClass.ECONOMIC, "001", 120.00));
        flight.setSeats(seats);

        flight.bookSeat("001");

        Assert.assertFalse(flight.getSeat("001").isAvailable());
    }

    @Test
    public void integrationTest() {
        FlightRepository flightRepository = new InMemoryFlightRepository();
        Flight flight = new Flight("DCP0001");
        Map<String, Seat> seats = new HashMap<>();
        seats.put("001", new Seat(SeatClass.ECONOMIC, "001", 120.00));
        flight.setSeats(seats);
        FlightService flightService = new FlightService(flightRepository);

        flightService.addFlight(flight);

        Flight searchedFlight = flightService.getFlight("DCP0001");
        Assert.assertTrue(searchedFlight.getSeat("001").isAvailable());

    }

    public void e2eTest() {
        //tests that cover business requirements
    }

}