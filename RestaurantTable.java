public class RestaurantTable{

    private int TABLE_ID;
    private int N_SEATS;



    public RestaurantTable(int TABLE_ID, int N_SEATS){
        this.TABLE_ID = TABLE_ID;
        this.N_SEATS = N_SEATS;
    }

    public void setTableID(int NEW_TABLE_ID){
        this.TABLE_ID = NEW_TABLE_ID;
    }

    public void setNSeats(int NEW_TABLE_ID){
        this.TABLE_ID = NEW_TABLE_ID;
    }


    public int getTableID(){
        return this.TABLE_ID;
    }

    public int getNSeats(){
        return this.N_SEATS;
    }

}
