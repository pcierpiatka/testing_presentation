package edu.the.way.of.testing.utils;

import edu.the.way.of.testing.Seat;
import edu.the.way.of.testing.SeatClass;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

/**
 * Created by pawel on 6/24/16.
 */
public class SeatAssert extends AbstractAssert<SeatAssert,Seat> {

    protected SeatAssert(Seat actual) {
        super(actual, SeatAssert.class);
    }

    public static SeatAssert assertThat(Seat actual){
        return new SeatAssert(actual);
    }

    public SeatAssert seatNumberIsEqualTo(String expected) {
        Assertions.assertThat(actual.getSeatNumber()).isEqualTo(expected);
        return this;
    }

    public SeatAssert seatClassIsEqualTo(SeatClass expected) {
        Assertions.assertThat(actual.getSeatClass()).isEqualTo(expected);
        return this;
    }

    public SeatAssert isEconomic(){
        return seatClassIsEqualTo(SeatClass.ECONOMIC);
    }

    public SeatAssert priceIsEqualTo(double expected) {
        Assertions.assertThat(actual.getPrice()).isEqualTo(expected);
        return this;
    }

    public SeatAssert isBooked() {
        Assertions.assertThat(actual.isBooked()).isTrue();
        return this;
    }

}
