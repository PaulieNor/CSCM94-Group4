package cscm12.cafe94;

// import javax.xml.crypto.Data; // java: package javax.xml.crypto is not visible
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    public Booking(int bookingID, int custID, int numberOfGuests, int tableID, String bookingDate) {
        this.bookingID = bookingID;
        this.custID = custID;
        this.numberOfGuests = numberOfGuests;
        this.tableID = tableID;
        this.bookingDate = LocalDateTime.parse(bookingDate);
    }

    public int getBookingID() {
        return bookingID;
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
    public void uploadBooking(){
        DatabaseHandler handler = new DatabaseHandler();
        try {
            handler.newEntry("Bookings", "CustomerUserID='" + custID +
                    "', numberOfGuests='" + numberOfGuests +
                    "', tableID='" + tableID +
                    "', bookingDate='" + bookingDate + "'",
                    "Database Error. Entries may be in incorrect format.");
        } catch (NullPointerException e){
            System.out.println("A field is empty.");
        }
    }

    /**
     * Edits the database booking entry with the same bookingID, using values in Booking object.
     */
    public void editBooking(){
        DatabaseHandler handler = new DatabaseHandler();
        try {
            handler.editEntry("Bookings", "bookingID",
                        "bookingID='" + bookingID,
                              "', CustomerUserID='" + custID +
                                    "', numberOfGuests='" + numberOfGuests +
                                    "', tableID='" + tableID +
                                    "', bookingDate='" + bookingDate + "'",
                    "Database Error. Entries may be in incorrect format.");
        } catch (NullPointerException e){
            System.out.println("A field is empty.");
        }
    }

    /** Checks datebase if there are other bookings less an hour before or after the requested slot.
     *
     * @return <code>boolean</code>, true for no other books and false otherwise.
     */
    public boolean checkTimeslot(){
        DatabaseHandler handler = new DatabaseHandler();
        // TODO
        return false;

    }


}
