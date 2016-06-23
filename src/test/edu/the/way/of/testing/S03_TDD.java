package edu.the.way.of.testing;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

/**
 * Created by pawel on 6/18/16.
 */
public class S03_TDD {

    @Test
    public void should_fallow_red() {
        //arrange

        //act
        Reservation reservation = new Reservation() {
            @Override
            public boolean book(Flight flight) {
                return false;
            }
        };
        //assert
        Assert.assertTrue(reservation.book(null));
    }

    @Test
    public void should_fallow_green() {
        //given

        //when
        Reservation reservation = new Reservation() {
            @Override
            public boolean book(Flight flight) {

                boolean isThereAnySeatsLeft = false;
                boolean twoDaysBefore = flight.getFlightDate().plusDays(2).isBefore(LocalDate.now());
                boolean priceIsLessThan = false;

                double price = 100.00;
                int availableSeats = 0;
                for (Seat seat : flight.getSeats().values()) {
                    if (seat.getPrice() < price) {
                        priceIsLessThan = true;
                    }

                    if (seat.isAvailable()) {
                        availableSeats++;
                    }

                    if (availableSeats > 2) {
                        isThereAnySeatsLeft = true;
                    }

                    if (priceIsLessThan && isThereAnySeatsLeft) {
                        break;
                    }
                }

                return isThereAnySeatsLeft && priceIsLessThan && twoDaysBefore;
            }
        };
        //then
        Assert.assertTrue(reservation.book(null));
    }

    @Test
    public void should_fallow_refactor() {
        //given

        //when
        Reservation reservation = new Reservation() {
            @Override
            public boolean book(Flight flight) {
                boolean isThereAnySeatsLeft = flight.getAvailableSeatsLeft() > 0;
                boolean twoDaysBefore = flight.getFlightDate().plusDays(2).isBefore(LocalDate.now());
                boolean priceIsLessThan = flight.getTheLowestPrice() < 100;

                return isThereAnySeatsLeft && twoDaysBefore && priceIsLessThan;
            }
        };
        //then
        Assert.assertTrue(reservation.book(null));
    }


    interface Reservation {

        boolean book(Flight flight);
    }

}
