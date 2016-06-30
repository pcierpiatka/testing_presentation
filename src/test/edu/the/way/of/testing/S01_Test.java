package edu.the.way.of.testing;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pawel on 6/18/16.
 */
public class S01_Test {

    // SRP = Single Responsibility Principal
    @Test
    public void testFallowSRPRule() {
        Flight flight = new Flight("SomeFlight");
        flight.setOrigin("Warsaw");
        flight.setDestination("Some other place");

        Map<String, Seat> seats = new HashMap<>();
        seats.put("0001", new Seat(SeatClass.ECONOMIC,"0001",102));

        flight.setSeats(seats);

        //test should exam one thing
        Assert.assertEquals(flight.getAvailableSeatsLeft(), 1);
    }

    @Test
    public void testShouldBePrecise() {
        //prepare SUT (System under test)
        Flight flight = new Flight("SomeFlight");
//        flight.setOrigin("Warszawa");
//        flight.bookSeat("023");
//        flight.setSeats(new HashMap<>());

        //assert things you actually want it
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

    @Test(expected = SeatAlreadyBookedException.class)
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
