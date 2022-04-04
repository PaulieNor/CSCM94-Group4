package cscm12.cafe94;
/**
 * [SitInOrders]
 * Sub class for handling sit in orders.
 * @author Patrick Rose
 * @version 1.0
 */
public class SitInOrders {
    private int tableNum;
    private int main;
    private int side;
    private int drink;
    private int sitInCustomerID;

    public SitInOrders(int main, int side, int drink, int tableNum, int sitInCustomerID) {
        this.tableNum = tableNum;
        this.main = main;
        this.side = side;
        this.drink = drink;
        this.sitInCustomerID = sitInCustomerID;
    }

    public static void main(String [] args){
    }
    /**
     * [submitSitInOrder]
     * Adds the values of the SitInOrders object to the database.
     */
    public void submitSitInOrder(){
        DatabaseHandler handler = new DatabaseHandler();
        try{
            handler.newEntry("SitDownOrders (SitDownCustomerID, TableNumber, SitDownCompletedOrder,\n" +
                            "SitDownMain, SitDownSide, SitDownDrink, IsServed)",
                    "'" + sitInCustomerID +
                            "', '" + tableNum +
                            "', '0'" +  //sitDownCompletedOrder always 0 to start with
                            ", '" + main +
                            "','" + side +
                            "','" + drink +
                            "','0'", //isServed always 0 to start with
                    "Database Error. Entries may be in incorrect format.");
        }catch (NullPointerException e){
            System.out.println("A field is empty.");
        }
    }
    /**
     * [markSitInOrderCompleted]
     * Method for the chef to mark an order that is complete.
     * @param orderID
     */
    public static void markSitInOrderCompleted(int orderID){
        DatabaseHandler handler = new DatabaseHandler();
        try{
            handler.tableUpdater("UPDATE SitDownOrders " +
                            "SET SitDownCompletedOrder = 'true' " +
                            "WHERE SitDownOrderID = " + orderID + ";",
                    "Query may be incorrectly formatted");
        } catch (NullPointerException e){
            System.out.println("A field is empty");
        }
    }
    /**
     * [markSitInOrderServed]
     * Method for the waitress to mark an order that is served.
     * @param orderID
     */
    public static void markSitInOrderServed(int orderID){
        DatabaseHandler handler = new DatabaseHandler();
        try{
            handler.tableUpdater("UPDATE SitDownOrders " +
                            "SET IsServed = 'true' " +
                            "WHERE SitDownOrderID = " + orderID + ";",
                    "Query may be incorrectly formatted");
        } catch (NullPointerException e){
            System.out.println("A field is empty");
        }
    }

    //Getters
    public int getMain(){
        return this.main;
    }
    public int getSide(){
        return this.side;
    }
    public int getDrink(){
        return this.drink;
    }
    public int getTableNum(){
        return this.tableNum;
    }
    public int getSitInCustomerID(){
        return this.sitInCustomerID;
    }
    //Setters
    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }
    public void setMain(int main){
        this.main = main;
    }
    public void setSide(int side){
        this.side = side;
    }
    public void setDrink(int drink){
        this.drink = drink;
    }
    public void setSitInCustomerID(int sitInCustomerID){
        this.sitInCustomerID = sitInCustomerID;
    }
}
