public class MenuItem{
    //Constructor
    //Logistics
    private String NAME; //The name of the item
    private int PRICE; //The price in pence

    //Dietary preference
    private boolean CONTAINS_MEAT; //Whether the product contains meat- for vegetarians
    private boolean ANIMAL_PRODUCT; //Whether the product is any kind of animal product- for vegans
    private boolean IS_HALAL; //Whether the product can be consumed by practicing Muslims

    //Daily special
    private boolean IS_SPECIAL;

    public MenuItem(String NAME, int PRICE, boolean CONTAINS_MEAT,
                    boolean ANIMAL_PRODUCT, boolean IS_HALAL, boolean IS_SPECIAL){
        this.NAME = NAME;
        this.PRICE = PRICE;
        this.CONTAINS_MEAT = CONTAINS_MEAT;
        this.ANIMAL_PRODUCT = ANIMAL_PRODUCT;
        this.IS_HALAL = IS_HALAL;
        this.IS_SPECIAL = IS_SPECIAL;
    }

    //Setters
    public void setName(String NEW_NAME) {
        this.NAME = NEW_NAME;
    }

    public void setPrice(int NEW_PRICE){
        this.PRICE = NEW_PRICE;
    }

    public void setContaintsMeat(boolean NEW_CONTAINS_MEAT){
        this.CONTAINS_MEAT = NEW_CONTAINS_MEAT;
    }

    public void setAnimalProduct(boolean NEW_ANIMAL_PRODUCT){
        this.ANIMAL_PRODUCT = NEW_ANIMAL_PRODUCT;
    }

    public void setIsHalal(boolean NEW_IS_HALAL){
        this.IS_HALAL = NEW_IS_HALAL;
    }

    public void setIsSpecial(boolean NEW_IS_SPECIAL){
        this.IS_SPECIAL = NEW_IS_SPECIAL;
    }

    //Getters
    public String getName(){
        return this.NAME;
    }

    public int getPrice(){
        return this.PRICE;
    }

    public boolean getContainsMeat(){
        return this.CONTAINS_MEAT;
    }

    public boolean getAnimalProduct(){
        return this.ANIMAL_PRODUCT;
    }

    public boolean getIsHalal(){
        return this.IS_HALAL;
    }

    public boolean getIsSpecial(){
        return this.IS_SPECIAL;
    }

}
