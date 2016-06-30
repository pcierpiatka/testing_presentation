 package edu.the.way.of.testing;

 import edu.the.way.of.testing.infrastructure.FlightAssert;
 import edu.the.way.of.testing.infrastructure.SeatAssert;
 import org.assertj.core.api.Assertions;
 import org.junit.Test;

/**
 * Created by pawel on 6/18/16.
 */
public class S04_Assertions {

    @Test
    public void should_use_assertJ() {
        //given
        Flight flight = new Flight("ABC1023");
        flight.addSeat(SeatClass.ECONOMIC, "001", 102);
        flight.addSeat(SeatClass.BUSINESS, "001", 202);
        //when
        double averagePriceOfAvailableSeats = flight.getAveragePriceOfAvailableSeats();
        //then
        Assertions.assertThat(averagePriceOfAvailableSeats).isEqualTo(123);
    }

    @Test
    public void should_use_custom_assertion() {
        //given
        Flight flight = new Flight("ABC1023");
        flight.addSeat(SeatClass.ECONOMIC, "001", 102);
        //when
        Seat seat = flight.bookSeat("001");
        //then
        SeatAssert.assertThat(seat)
                .isEconomic()
                .isBooked()
                .priceIsEqualTo(102)
                .seatNumberIsEqualTo("001");
    }

    @Test
    public void should_show_off_with_error_message() {
        //give
        Flight flight = new Flight("ABC1023");
        flight.addSeat(SeatClass.ECONOMIC, "001", 101);
        flight.addSeat(SeatClass.BUSINESS, "002", 102);
        flight.addSeat(SeatClass.ECONOMIC, "003", 103);
        //when
        //do some business operation
        //then
        FlightAssert.assertThat(flight)
                .selectSeatByNumber("010")
                .isEconomic()
                .priceIsEqualTo(101);
    }

}
