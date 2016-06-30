package edu.the.way.of.testing.infrastructure;

import edu.the.way.of.testing.Flight;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.IterableAssert;

import java.util.List;

/**
 * User: pcierpiatka
 */
public class FlightListAssert extends IterableAssert<Flight> {

    protected FlightListAssert(List<Flight> actual) {
        super(actual);
    }

    public static FlightListAssert assertThat(List<Flight> actual) {
       return new FlightListAssert(actual);
    }

    public FlightListAssert containsFlightOnlyOnRoute(String origin, String destination) {
        for(Flight flight : actual) {
            Assertions.assertThat(flight.isFrom(origin)).isTrue();
            Assertions.assertThat(flight.isTo(destination)).isTrue();
        }

        return this;
    }

    public FlightListAssert containsFlightOnlyFrom(String origin) {
        for(Flight flight : actual) {
            Assertions.assertThat(flight.isFrom(origin)).isTrue();
        }

        return this;
    }

    public FlightListAssert containsFlightOnlyTo(String destination) {
        for(Flight flight : actual) {
            Assertions.assertThat(flight.isTo(destination)).isTrue();
        }

        return this;
    }

    public FlightListAssert hasSize(int size) {
        Assertions.assertThat(actual).hasSize(size);
        return this;
    }
}
