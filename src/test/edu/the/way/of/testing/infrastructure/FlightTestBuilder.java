package edu.the.way.of.testing.infrastructure;

import edu.the.way.of.testing.Flight;
import edu.the.way.of.testing.SeatClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * User: pcierpiatka
 */
public class FlightTestBuilder {

    private Flight flight;
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    private FlightTestBuilder(String flightCode) {
        flight = new Flight(flightCode);
    }

    public static FlightTestBuilder flight(String flightCode) {
        return new FlightTestBuilder(flightCode);
    }

    public Flight build() {
        return flight;
    }

    public FlightTestBuilder withSeats(int seatCount) {
        return withSeatsInPrice(seatCount, 0);
    }

    public FlightTestBuilder withSeatsInPrice(int seatsCount, double price) {
        return withSeats(SeatClass.ECONOMIC, seatsCount, price);
    }

    public FlightTestBuilder withBookedSeatsInPrice(int seatsCount, double price) {
        int paddedSeatsCount = flight.getSeatsCount() + seatsCount;
        String seatNumber;

        for (int seatIndex = flight.getSeatsCount(); seatIndex < paddedSeatsCount; ++seatIndex) {
            seatNumber = "seat" + seatIndex;
            flight.addSeat(SeatClass.ECONOMIC, seatNumber, price);
            flight.bookSeat(seatNumber);
        }
        return this;
    }

    public FlightTestBuilder withSeatInPrice(String seatNumber, double price) {
        flight.addSeat(SeatClass.ECONOMIC, seatNumber, price);
        return this;
    }

    public FlightTestBuilder withSeat(String seatNumber) {
        flight.addSeat(SeatClass.ECONOMIC, seatNumber, 0);
        return this;
    }

    public FlightTestBuilder withBookedSeat(String seatNumber) {
        flight.addSeat(SeatClass.ECONOMIC, seatNumber, 0);
        flight.bookSeat(seatNumber);
        return this;
    }

    public FlightTestBuilder withSeats(SeatClass seatClass, int seatsCount, double price) {
        int paddedSeatsCount = flight.getSeatsCount() + seatsCount;

        for (int seatIndex = flight.getSeatsCount(); seatIndex < paddedSeatsCount; ++seatIndex) {
            flight.addSeat(seatClass, "seat" + seatIndex, price);
        }
        return this;
    }

    public FlightTestBuilder from(String from) {
        flight.setOrigin(from);
        return this;
    }

    public FlightTestBuilder to(String destination) {
        flight.setDestination(destination);
        return this;
    }

    public FlightTestBuilder on(String dateOfFlight) {
        flight.setFlightDate(LocalDate.parse(dateOfFlight, dateFormat));
        return this;
    }
}
