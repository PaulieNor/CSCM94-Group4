package cscm12.cafe94;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * [Booking]
 * Class for managing customer sit-in bookings.
 * @author Paul Norman
 * @version 1.0
 */
public class Booking {

    private int custID;
    private int numberOfGuests;
    private int tableID;
    private LocalTime bookingTime;
    private LocalDate bookingDate;

    public Booking(int custID, int numberOfGuests, int tableID,
                   LocalTime bookingTime, LocalDate bookingDate) {
        this.custID = custID;
        this.numberOfGuests = numberOfGuests;
        this.tableID = tableID;
        this.bookingTime = bookingTime;
        this.bookingDate = bookingDate;
    }

    public int getCustID(){
        return this.custID;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public int getTableID() {
        return tableID;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public LocalTime getBookingTime() {
        return bookingTime;
    }

    // Setters

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public void setBookingTime(LocalTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    /**
     * [uploadBooking]
     * Adds values in Booking object to database.
     */
    public void uploadBooking(){
        DatabaseHandler handler = new DatabaseHandler();
        try {
            handler.newEntry("Bookings", "CustomerUserID=" + custID +
                    ", numberOfGuests=" + numberOfGuests +
                    ", tableID=" + tableID +
                    ", bookingTime=" + bookingTime +
                    ", bookingDate=" + bookingDate,
                    "Database Error. Entries may be in incorrect format.");
        } catch (NullPointerException e){
            System.out.println("A field is empty.");
        }
    }
}
