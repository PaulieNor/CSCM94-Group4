import java.util.ArrayList;

public class MenuList{

    private ArrayList<MenuItem> ITEMS;

    public MenuList(){
    }

    public ArrayList<MenuItem> getList(){
        return this.ITEMS;
    }

    public void addItem(MenuItem MenuItem){
        ITEMS.add(MenuItem);
    }

    public MenuItem getItem(int i){
        return ITEMS.get(i);

    }

    public int getTotalPrice(){
        int total = 0;
        for (int item = 0; item < ITEMS.size(); item++){
            total += this.getItem(item).getPrice();
        }
        return total;
    }

    public int getMinWait(){
        int max = 0;
        for (int item = 0; item < ITEMS.size(); item++){
            if (this.getItem(item).getWaitTime() > max){
                max = this.getItem(item).getPrice();
            }
        }
        return max;
    }

    public void setSpecial(int i){
        for (int item = 0; item < ITEMS.size(); item++)
            if (this.getItem(item).getIsSpecial() == true){
                this.getItem(item).setIsSpecial(false);
            }
        this.getItem(i).setIsSpecial(true);
    }



}
