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

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setSpecial(boolean special) {
        isSpecial = special;
    }

    public void setTimeToMake(int timeToMake) {
        this.timeToMake = timeToMake;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public int getFoodID() {
        return foodID;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public void uploadItem(){
        DatabaseHandler handler = new DatabaseHandler();
        try {
            handler.newEntry("MenuItems (ItemName, ItemType, Price, TimeToMake, " +
                            "IsVegetarian, IsSpecial)", "'"+ itemName +
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

    public void editItem(){
        DatabaseHandler handler = new DatabaseHandler();
        String id = String.valueOf(foodID);
        try {
            handler.editEntry("MenuItems (ItemName, Price, ItemType, IsVegetarian",
                    "ItemId", id,
                    "'" +itemName +
                            "', " + price +
                            ", '" + itemType +
                            "', " + isVegetarian,
                    "Database Error. Entries may be in incorrect format.");
        } catch (NullPointerException e){
            System.out.println("A field is empty.");
        }
    }

    public void deleteItem(){
        DatabaseHandler handler = new DatabaseHandler();
        String id = String.valueOf(foodID);
        try {
            handler.deleteEntry("MenuItems", "ItemID", id,
                    "May not exist.");
        } catch (Exception e) {
            System.out.println();
        }
    }
}
