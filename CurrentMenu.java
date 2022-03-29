package src.main.manager;

/**
 * @Author Christian Piri
 * @Version v1.3
 *
 * Sets collected data to string variable to then be placed in table.
 */

public class CurrentMenu {

    private String columnOne;
    private String columnTwo;
    private String columnThree;
    private String columnFour;

    public CurrentMenu(String columnOne, String columnTwo, String columnThree,
                       String columnFour) {
        this.columnOne = columnOne;
        this.columnTwo = columnTwo;
        this.columnThree = columnThree;
        this.columnFour = columnFour;
    }

    public String getColumnOne() {
        return columnOne;
    }

    public void setColumnOne(String columnOne) {
        this.columnOne = columnOne;
    }

    public String getColumnTwo() {
        return columnTwo;
    }

    public void setColumnTwo(String columnTwo) {
        this.columnTwo = columnTwo;
    }

    public String getColumnThree() {
        return columnThree;
    }

    public void setColumnThree(String columnThree) {
        this.columnThree = columnThree;
    }

    public String getColumnFour() {
        return columnFour;
    }

    public void setColumnFour(String columnFour) {
        this.columnFour = columnFour;
    }
}
