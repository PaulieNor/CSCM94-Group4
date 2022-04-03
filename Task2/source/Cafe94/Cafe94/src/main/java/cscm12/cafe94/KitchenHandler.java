package cscm12.cafe94;

/** Handles food and menu related database calls.
 * @author Paul Norman
 * @version 1.0
 */
public class KitchenHandler extends DatabaseHandler {

    /**
     * Sets new special and reverts any current specials to non-special.
     * @param newSpecial The ItemName of new special.
     */
    public void changeSpecial(int newSpecial){
        try {
            String query = "UPDATE MenuItems SET isSpecial=0 WHERE isSpecial= 1";
            DatabaseHandler handler = new DatabaseHandler();
            tableUpdater(query, "Database key error.");
            query = "UPDATE MenuItems SET isSpecial=0 WHERE ItemName=" + newSpecial;
            tableUpdater(query, "Database key error.");
        } catch (Exception e){
            System.out.println("Something went wrong");
        }
    }
}
