package cscm12.cafe94;

public class KitchenTicket extends Ticket {

    private int referenceNumber;
    private String orderType;
    private boolean isCooked;

    public KitchenTicket(int referenceNumber, String main, String side,
                         String drink, String orderType, boolean isCooked) {
        super(main, side, drink);
        this.referenceNumber = referenceNumber;
        this.orderType = orderType;
        this.isCooked = isCooked;
    }

    public int getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public boolean isCooked() {
        return isCooked;
    }

    public void setCooked(boolean cooked) {
        isCooked = cooked;
    }
}
