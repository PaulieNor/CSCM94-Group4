package cscm12.cafe94;


/**
 * Superclass for tickets.
 */
public class Ticket {


    private String main;
    private String side;
    private String drink;

    public Ticket(String main, String side, String drink) {
        this.main = main;
        this.side = side;
        this.drink = drink;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }
}
