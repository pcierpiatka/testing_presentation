package edu.the.way.of.testing.infrastructure;

import edu.the.way.of.testing.Seat;
import edu.the.way.of.testing.SeatClass;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

/**
 * Created by pawel on 6/24/16.
 */
public class SeatAssert extends AbstractAssert<SeatAssert,Seat> {

    private FlightAssert parent;

    protected SeatAssert(Seat actual, FlightAssert parent) {
        super(actual, SeatAssert.class);
    }

    public static SeatAssert assertThat(Seat actual){
        return new SeatAssert(actual, null);
    }

    public SeatAssert seatNumberIsEqualTo(String expected) {
        Assertions.assertThat(actual.getSeatNumber()).isEqualTo(expected);
        return myself;
    }

    public SeatAssert seatClassIsEqualTo(SeatClass expected) {
        Assertions.assertThat(actual.getSeatClass()).isEqualTo(expected);
        return myself;
    }

    public SeatAssert isEconomic(){
        return seatClassIsEqualTo(SeatClass.ECONOMIC);
    }

    public SeatAssert priceIsEqualTo(double expected) {
        Assertions.assertThat(actual.getPrice()).isEqualTo(expected);
        return myself;
    }

    public SeatAssert isBooked() {
        Assertions.assertThat(actual.isBooked()).isTrue();
        return myself;
    }

    public FlightAssert backToFlight() {
        validate();
        return parent;
    }

    private void validate() {
        Assertions.assertThat(parent).overridingErrorMessage("Can not back to FlightAssert because FlightAssert is null.\n");
    }

    public static SeatAssert assertThat(Seat seat, FlightAssert parent) {
        return new SeatAssert(seat, parent);
    }
}
