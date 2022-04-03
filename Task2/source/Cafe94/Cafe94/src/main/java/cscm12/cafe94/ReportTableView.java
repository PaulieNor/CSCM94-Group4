package cscm12.cafe94;

/**
 * @Author Christian Piri
 * @Version v1.5
 *
 * Sets collected data to string variable to then be placed in table for 'ManagerReport'.
 */

public class ReportTableView {

    private String columnOne;
    private String columnTwo;
    private String columnThree;
    private String columnFour;
    private String columnFive;
    private String columnSix;

    public ReportTableView(String columnOne, String columnTwo, String columnThree,
                           String columnFour, String columnFive, String columnSix) {
        this.columnOne = columnOne;
        this.columnTwo = columnTwo;
        this.columnThree = columnThree;
        this.columnFour = columnFour;
        this.columnFive = columnFive;
        this.columnSix = columnSix;
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

    public String getColumnFive() {
        return columnFive;
    }

    public void setColumnFive(String columnFive) {
        this.columnFive = columnFive;
    }

    public String getColumnSix() {
        return columnSix;
    }

    public void setColumnSix(String columnSix) {
        this.columnSix = columnSix;
    }
}
