package cscm12.cafe94;

import java.time.LocalDate;
import java.time.LocalTime;

public class Customer {

    private int custID;
    private String firstName;
    private String lastName;
    private String address;
    private String postcode;

    public Customer(int custID, String firstName, String lastName,
                   String address, String postcode) {
        this.custID = custID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postcode = postcode;
    }

    public int getCustID() {
        return custID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * Adds values in Customer object to database.
     */
    public void uploadBooking(){
        DatabaseHandler handler = new DatabaseHandler();
        try {
            handler.newEntry("Customers", "CustomerFirstName='" + firstName +
                            "', CustomerLastName='" + lastName +
                            "', CustomerStreetAdd='" + address +
                            "', CustomerPostCode='" + postcode + "'",
                    "Database Error. Entries may be in incorrect format.");
        } catch (NullPointerException e){
            System.out.println("A field is empty.");
        }
    }

    /**
     * Edits the database booking entry with the same CustomerUserID, using values in Booking object.
     */
    public void editBooking(){
        DatabaseHandler handler = new DatabaseHandler();
        try {
            handler.editEntry("Customers", "CustomerUserID",
                    "CustomerUserID='" + custID,
                    "CustomerFirstName='" + firstName +
                            "', CustomerLastName='" + lastName +
                            "', CustomerStreetAdd='" + address +
                            "', CustomerPostCode='" + postcode + "'",
                    "Database Error. Entries may be in incorrect format.");
        } catch (NullPointerException e){
            System.out.println("A field is empty.");
        }
    }
}