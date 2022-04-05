package cscm12.cafe94;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Menu {
    private SimpleStringProperty item;


    public Menu(String item) {
        this.item = new SimpleStringProperty(item);


    }

    public String getItem() {
        return item.get();
    }
    public void setItem(String anItem) {
        item.set(anItem);
    }

    public SimpleStringProperty itemProperty() {
        return item;
    }
}
