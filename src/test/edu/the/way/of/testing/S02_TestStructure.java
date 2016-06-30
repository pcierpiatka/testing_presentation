package edu.the.way.of.testing;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by pawel on 6/18/16.
 */
public class S02_TestStructure {

    @Test
    public void testJUnitWay(){
        //arrange
        /** creation of all objects that are NECESSARY for test execution
         *  - as well as preparing SUT for text execution */
        Flight flight = new Flight("ABC");
        flight.addSeat(SeatClass.ECONOMIC, "001", 120.00);
        //act
        /** execution of SUT */
        flight.bookSeat("001");
        //assert
        /** verification */
        Assert.assertEquals(flight.getSeat("001").isAvailable(), false);
    }

    @Test
    public void shouldDoTdd() {
        //given
        /** creating object which describe situation */
        Flight flight = createFlightWithOneAvailableSeat();
        //when
        /** behavior invocation */
        flight.bookSeat("001");
        //then
        Assert.assertEquals(flight.getAvailableSeatsLeft(), 0);
    }

    public void whatever_works() {
        //prepare test env
        //execute behavior
        //verify expected result
    }

    //my way
    public void should_description_of_required_behavior() {
        //given

        //when

        //them
    }


    private Flight createFlightWithOneAvailableSeat() {
        Flight flight = new Flight("ABC");
        flight.addSeat(SeatClass.ECONOMIC, "001", 120.00);
        return flight;
    }
}
