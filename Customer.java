package cscm12.cafe94;

/**
 * Contains customer information, uploads to database.
 */
public class Customer {

    private String custID;
    private String firstName;
    private String lastName;
    private String address;
    private String postcode;

    public Customer(String custID, String firstName, String lastName,
                   String address, String postcode) {
        this.custID = custID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postcode = postcode;
    }

    public String getCustID() {
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

    public void setCustID(String custID) {
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
    public void uploadCustomer(){
        DatabaseHandler handler = new DatabaseHandler();
        try {
            System.out.println(custID + "', '" + firstName + "', "+ lastName +  "', " +
                                "'" + address + "', '" + postcode + "'");
            handler.newEntry("Customers", "'"+ custID +
                            "', '" + firstName +
                            "', '"+ lastName +
                            "', '" + address +
                            "', '" + postcode + "'",
                    "Database Error. Entries may be in incorrect format.");
        } catch (NullPointerException e){
            System.out.println("A field is empty.");
        }
    }

    /**
     * Edits the database customer entry with the same CustomerUserID, using values in Customer object.
     */
    public void editCustomer(){
        DatabaseHandler handler = new DatabaseHandler();
        try {
            handler.editEntry("Customers", "CustomerUserID", custID,
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