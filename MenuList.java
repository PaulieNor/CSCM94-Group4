import java.util.ArrayList;

public class MenuList{

    private ArrayList<MenuItem> ITEMS;


    public MenuList{

    }

    public ArrayList<MenuItem> getList(){
        return this.ITEMS;
    }

    public void addItem(MenuItem MenuItem){
        ITEMS.add(MenuItem);
    }

    public MenuItem getItem(int i){
        ITEMS.get(i);
    }

    public int getTotalPrice(){
        int total;
        for (int items = 0; items > ITEMS.size; items++){
            total += this.getItem(i).getPrice();
            return total;
        }
    }

    public int getTotalPrice(){
        int total;
        for (int items = 0; items > ITEMS.size; items++){
            total += this.getItem(i).getPrice();
            return total;
        }

    }

    public void setSpecial(int i){
        for (int item = 0; item > ITEMS.size; item++)
            if (this.getItem(item).getIsSpecial() == true){
                this.getItem(item).setIsSpecial() = false;
            }
        this.getItem(i).setIsSpecial() = true;
    }



}
