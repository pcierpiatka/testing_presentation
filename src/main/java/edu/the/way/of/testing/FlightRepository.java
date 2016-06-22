package edu.the.way.of.testing;

/**
 * Created by pawel on 6/22/16.
 */
public interface FlightRepository {

    void save(Flight flight);

    Flight load(String flightCode);

    int count();
}
