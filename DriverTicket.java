package cscm12.cafe94;


/** Container class for driver tickets.
 * @author Paul Norman
 * @version 1.0
 */
public class DriverTicket extends Ticket {

    private int orderID;
    private String deliveryAddress;
    private String driverName;

    public DriverTicket(int orderID, String main, String side, String drink,
                        String deliveryAddress, String driverName) {
        super(main, side, drink);
        this.orderID = orderID;
        this.deliveryAddress = deliveryAddress;
        this.driverName = driverName;
    }



}
