 package edu.the.way.of.testing;

import edu.the.way.of.testing.utils.SeatAssert;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by pawel on 6/18/16.
 */
public class S04_Assertions {

    @Test
    public void should_assertion_be_used() {
        //given
        Flight flight = new Flight("ABC1023");
        flight.addSeat(SeatClass.ECONOMIC,"001", 102);
        flight.addSeat(SeatClass.BUSINESS,"001", 202);
        //when
        double averagePriceOfAvailableSeats = flight.getAveragePriceOfAvailableSeats();
        //then
        Assertions.assertThat(averagePriceOfAvailableSeats).isEqualTo(123);
    }

    @Test
    public void should_assertion_be_used_to_verify_object() {
        //given
        Flight flight = new Flight("ABC1023");
        flight.addSeat(SeatClass.ECONOMIC,"001", 102);
        //when
        Seat seat = flight.bookSeat("001");
        //then
        SeatAssert.assertThat(seat)
                .isEconomic()
                .isBooked()
                .priceIsEqualTo(102)
                .seatNumberIsEqualTo("001");
    }

}
