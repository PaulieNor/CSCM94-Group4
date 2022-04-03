package cscm12.cafe94;

/** Container class for food items. Uploads to databases and makes edits.
 * @author Paul Norman
 * @version 1.0
 */

public class FoodItem {


    private int foodID;
    private String itemName;
    private String itemType;
    private float price;
    private int timeToMake;
    private boolean isVegetarian;
    private boolean isSpecial;

    public FoodItem(int foodID, String itemName, float price, int timeToMake,
                    boolean isVegetarian, boolean isSpecial){
        if (itemName.toLowerCase().contains("side")){
            this.itemName = "Side";
        } else if (itemName.toLowerCase().contains("drink")){
            this.itemName = "Drink";
        } else {
            this.itemName = "Main";
        }
        this.foodID = foodID;
        this.itemName = itemName;
        this.price = price;
        this.timeToMake = timeToMake;
        this.isVegetarian = isVegetarian;
        this.isSpecial = isSpecial;
    }



    public void uploadItem(){
        DatabaseHandler handler = new DatabaseHandler();
        try {
            handler.newEntry("MenuItems (ItemName, ItemType, Price, TimeToMake, " +
                            "IsVegetarian, IsSpecial", "'"+ itemName +
                            "', '" + itemType +
                            "', '"+ price +
                            "', '" + timeToMake +
                            "', '" + isVegetarian +
                            "', '" + isSpecial + "'",
                    "Database Error. Entries may be in incorrect format.");
        } catch (NullPointerException e){
            System.out.println("A field is empty.");
        }
    }




}
