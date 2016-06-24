package edu.the.way.of.testing;

import edu.the.way.of.testing.utils.FlightTestBuilder;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pawel on 6/18/16.
 */
public class S05_Builder {

    private FlightService flightService;

    @Before
    public void init() {
        flightService = new FlightService(new InMemoryFlightRepository());
    }

    @Test
    public void should_return_price_of_a_cheapest_seat_in_given_flight() {
        // given
        double cheapestSeatPrice = 10;
        Flight flight = new Flight("L101");
        Map<String, Seat> flightSeats = new HashMap<>();
        flightSeats.put("001", new Seat(SeatClass.FIRST, "001", 100 ));
        flightSeats.put("002", new Seat(SeatClass.BUSINESS, "001", 32 ));
        flightSeats.put("003", new Seat(SeatClass.ECONOMIC, "001", cheapestSeatPrice ));
        flight.setSeats(flightSeats);
        flightService.addFlight(flight);
        // when
        double cheapestFlightSeat = flightService.getPriceOfCheapestFlightSeat("L101");

        // then
        Assertions.assertThat(cheapestFlightSeat).isEqualTo(cheapestFlightSeat);
    }


    @Test
    public void should_return_price_of_a_cheapest_seat_in_given_flight_builder() {
        // given
        double cheapestSeatPrice = 10;

        Flight testFlight = FlightTestBuilder.flight("L101")
                .withSeatInPrice("001", 100)
                .withSeatInPrice("002", 32)
                .withSeatInPrice("003", cheapestSeatPrice).build();
        flightService.addFlight(testFlight);

        // when
        double cheapestFlightSeat = flightService.getPriceOfCheapestFlightSeat("L101");

        // then
        Assertions.assertThat(cheapestFlightSeat).isEqualTo(cheapestFlightSeat);
    }


    private Flight createFlightForTheCheapestSeatTest(double cheapestSeatPrice) {
        Flight flight = new Flight("L101");
        Map<String, Seat> flightSeats = new HashMap<>();
        flightSeats.put("001", new Seat(SeatClass.FIRST, "001", 100 ));
        flightSeats.put("002", new Seat(SeatClass.BUSINESS, "001", 32 ));
        flightSeats.put("003", new Seat(SeatClass.ECONOMIC, "001", cheapestSeatPrice ));
        flight.setSeats(flightSeats);
        return flight;
    }

}
