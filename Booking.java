package cscm12.cafe94;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for managing customer sit-in bookings.
 * @author Paul Norman
 * @version 1.1
 */
public class Booking {

    private int bookingID;
    private int custID;
    private int numberOfGuests;
    private int tableID;
    private LocalDateTime bookingDate;

    public Booking(int bookingID, int custID, int numberOfGuests, int tableID, LocalDateTime bookingDate) {
        this.bookingID = bookingID;
        this.custID = custID;
        this.numberOfGuests = numberOfGuests;
        this.tableID = tableID;
        this.bookingDate = bookingDate;
    }

    public int getBookingID() {
        return bookingID;
    }

    public int getCustID() {
        return this.custID;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public int getTableID() {
        return tableID;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    // Setters

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    /**
     * Adds values in Booking object to database.
     */
    public void uploadBooking() {
        DatabaseHandler handler = new DatabaseHandler();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String input = bookingDate.format(formatter);
        try {
            handler.newEntry("BookingTables (CustomerID,NumberGuests,TableID,BookingTime) ",
                         custID +
                            ", " + numberOfGuests +
                            ", " + tableID +
                            ", '" + input + "'",
                    "Database Error. Entries may be in incorrect format.");
        } catch (NullPointerException e) {
            System.out.println("A field is empty.");
        }
    }

    /** Makes a database check to see if booking time is free for a table using @checkTimeSlot
     * then if true uploads the booking to the database using @uploadBooking.
     */
    public void book(){
        boolean slotIsFree = checkTimeslot();
        if (slotIsFree){
            uploadBooking();
        } else{
            System.out.println("Table is booked for this timeslot.");
        }
        return;
    }

    public void approveBooking(){
        String booking = String.valueOf(bookingID);
        try {
            DatabaseHandler handler = new DatabaseHandler();
            handler.editEntry("BookingTables", "BookingID",
                     booking, "IsApproved=1",
                    "Database value error.");
        } catch (Exception e) {
            System.out.println("");
        }
    }

    /**
     * Edits the database booking entry with the same bookingID, using values in Booking object.
     */
    public void editBooking() {
        DatabaseHandler handler = new DatabaseHandler();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String id = String.valueOf(bookingID);
        String input = bookingDate.format(formatter);
        try {
            handler.editEntry("Bookings", "BookingID",
                     id,
                    ", CustomerID=" + custID +
                            ", NumberGuests=" + numberOfGuests +
                            ", TableID=" + tableID +
                            ", BookingTime='" + input + "'",
                    "Database Error. Entries may be in incorrect format.");
        } catch (NullPointerException e) {
            System.out.println("A field is empty.");
        }
    }

    /**
     * Deletes booking which matches the object.
     */
    public void deleteBooking() {
        DatabaseHandler handler = new DatabaseHandler();
        String id = String.valueOf(bookingID);
        try {
            handler.deleteEntry("BookingTables", "BookingID", id,
                    "May not exist.");
        } catch (Exception e) {
            System.out.println();
        }
    }

    /**
     * Checks bookings for a given tableID with a range of one hour before and one hour after. If there
     * are one or more bookings in that timeslot it will return false.
     * @return True if timeslot is free, false otherwise.
     */
    public boolean checkTimeslot() {
        LocalDateTime min = bookingDate.plusHours(-1);
        LocalDateTime max = bookingDate.plusHours(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String minString = min.format(formatter);
        String maxString = max.format(formatter);
        DatabaseHandler handler = new DatabaseHandler();
        Connection connect = handler.database();
        try {
            String query = "SELECT BookingTime from BookingTables WHERE TableID = " + tableID +
                    " AND BookingTime > '" + minString + "' AND BookingTime < '" + maxString
                    + "' AND IsApproved=1";
            System.out.println(query);
            PreparedStatement checkDatabase = connect.prepareStatement(query);
            ResultSet resultSet = checkDatabase.executeQuery();
            if (resultSet.next()) {
                return false;
            }
            return true;
        } catch (NullPointerException n) {
            System.out.println("Missing input.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
