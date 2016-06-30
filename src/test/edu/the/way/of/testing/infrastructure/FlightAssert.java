package edu.the.way.of.testing.infrastructure;

import edu.the.way.of.testing.Flight;
import edu.the.way.of.testing.Seat;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by pawel on 6/30/16.
 */
public class FlightAssert extends AbstractAssert<FlightAssert, Flight>{

    protected FlightAssert(Flight actual) {
        super(actual, FlightAssert.class);
    }

    public static FlightAssert assertThat(Flight actual) {
        return new FlightAssert(actual);
    }

    public SeatAssert selectSeatByNumber(String searchSeat) {
        Optional<Seat> seat = actual.getSeats().values().stream().findAny().filter(new Predicate<Seat>() {
            @Override
            public boolean test(Seat seat) {
                return seat.getSeatNumber().equals(searchSeat);
            }
        });
        Assertions.assertThat(seat.isPresent()).overridingErrorMessage("These Is Not the Seat (Droid:%s) You Are Looking For\n Flight contains %s such seats %s"
                , searchSeat
                , actual.getFlightCode()
                , actual.getSeats().keySet().toString()
        ).isTrue();
        return SeatAssert.assertThat(seat.get(), this);
    }
}
