package edu.the.way.of.testing;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by pawel on 6/18/16.
 */
public class S06_WhatNotToTest {

    /**
     * Frameworks,
     * Libraries,
     * Get/Set
     * Unit:
     * Integration
     */
    @Test
    public void testNotLibrary() {
        Flight flight = new Flight("ABC001");
        flight.setSeats(new HashMap<>());

        for(int i=0; i < 10 ; i++) {
            flight.addSeat(SeatClass.ECONOMIC,"Seat"+ i, 120 + i);
        }

        //we dont need to iterate 10 times to know that addSeat works
        Assert.assertEquals(flight.getSeatsCount(), 10);
    }


    public void should_not_fallow_mock_madness() {
        //given
        MyMock.when(Repository.class).then("This");
        MyMock.when(Service.class).then("is");
        MyMock.when(OtherService.class).then("SDC!!!");
        //when
        Object result = new FlightServiceImpl().doMagic("Abc");
        //then
        Assert.assertEquals(result, "Shame on you!");

    }

    private class FlightServiceImpl {

        private Repository repository;
        private Service service;
        private OtherService otherService;

        public Object doMagic(Object object) {
            Object result = repository.doThing(object);
            if(checkResult(result)) {
                   doWhatYouNeedToDo(result);
            } else {
                doSomeOtherStuff(result);
            }
            return service.doThingService(otherService.doOtherThing(result));
        }

        private void doSomeOtherStuff(Object result) {
        }

        private void doWhatYouNeedToDo(Object result) {

        }

        private boolean checkResult(Object result) {
            return false;
        }

    }

    private static class MyMock {

        static MyMock when(Class clazz) {
            return new MyMock();
        }

        MyMock then(Object o) {
            return this;
        }

    }

    private interface Repository { Object doThing(Object object); }
    private interface Service {Object doThingService(Object object); }
    private interface OtherService{ Object doOtherThing(Object object); }

}
