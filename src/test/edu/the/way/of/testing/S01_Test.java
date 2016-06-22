package edu.the.way.of.testing;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pawel on 6/18/16.
 */
public class S01_Test {

    //this is some kind of test
    @Test
    public void testBehavior() {

    }

    @Test
    public void testNotLibrary() {

        FlightService flightService = new FlightService(new InMemoryFlightRepository());

        for(int i=0; i < 10 ; i++) {
            flightService.addFlight(new Flight("SomeCode"+i));
        }

        Assert.assertEquals(flightService.numberOfFlights(), 10);
    }

    @Test
    public void testFallowSRPRule() {

        Flight flight = new Flight("SomeFlight");
        flight.setOrigin("Warsaw");
        flight.setDestination("Some other place");

        Map<String, Seat> seats = new HashMap<>();
        seats.put("0001", new Seat(SeatClass.ECONOMIC,"0001",102));

        flight.setSeats(seats);

        Assert.assertEquals(flight.getAvailableSeatsLeft(), 1);


    }

    @Test
    public void testShouldBeClear() {
        Flight flight = new Flight("SomeFlight");

        Assert.assertEquals(flight.getFlightCode(),"SomeFlight");
    }

    @Test
    public void testVerifyHappyPath() {
        Flight flight = new Flight("SomeFlight");
        flight.setOrigin("Warsaw");
        flight.setDestination("Some other place");

        Map<String, Seat> seats = new HashMap<>();
        seats.put("0001", new Seat(SeatClass.ECONOMIC, "0001", 102));
        flight.setSeats(seats);

        flight.bookSeat("0001");

        Assert.assertFalse(flight.getSeat("0001").isAvailable());
    }

    @Test(expected = IllegalStateException.class)
    public void testVerifyFailingPath() {
        Flight flight = new Flight("SomeFlight");
        flight.setOrigin("Warsaw");
        flight.setDestination("Some other place");

        Map<String, Seat> seats = new HashMap<>();
        seats.put("0001", new Seat(SeatClass.ECONOMIC, "0001", 102));
        flight.setSeats(seats);

        flight.bookSeat("0001");
        flight.bookSeat("0001");
    }
}
