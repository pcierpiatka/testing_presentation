package edu.the.way.of.testing;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pawel on 6/22/16.
 */
public class InMemoryFlightRepository implements FlightRepository {

    private final Map<String, Flight> flightRepo = new HashMap<>();

    @Override
    public void save(Flight flight) {
        flightRepo.put(flight.getFlightCode(),flight);
    }

    @Override
    public Flight load(String flightCode) {
        return flightRepo.get(flightCode);
    }
}
