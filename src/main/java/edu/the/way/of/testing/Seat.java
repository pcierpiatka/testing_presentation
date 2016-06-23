package edu.the.way.of.testing;

/**
 * Created by pawel on 6/22/16.
 */
public class Seat {

    private String seatNumber;
    private SeatClass seatClass;
    private double price;
    private boolean booked;

    public Seat(SeatClass seatClass, String seatNumber, double price) {
        this.seatClass = seatClass;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public boolean isInClass(SeatClass seatClass) {
        return this.seatClass.equals(seatClass);
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public SeatClass getSeatClass() {
        return seatClass;
    }

    public double getPrice() {
        return price;
    }

    public boolean isBooked() {
        return booked;
    }

    public boolean isAvailable() {
        return !isBooked();
    }

    public void book() {
        this.booked = true;
    }
}