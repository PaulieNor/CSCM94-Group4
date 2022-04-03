package cscm12.cafe94;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RestaurantTable {

        private int TABLE_ID;
        private int N_SEATS;

        public RestaurantTable(int tableID, int nSeats) {
            this.TABLE_ID = tableID;
            this.N_SEATS = nSeats;
        }

        public void setTableID(int tableID) {
            this.TABLE_ID = tableID;
        }

        public void setNSeats(int nSeats) {
            this.TABLE_ID = nSeats;
        }

        public int getTableID() {
            return this.TABLE_ID;
        }

        public int getNSeats() {
            return this.N_SEATS;
        }

    /**
     * [getBookings]
     * Retrieves all bookings associated with a table from the database for frontend display.
     * @return ObservableList of all bookings for that table.
     */
    public ObservableList<Booking> getBookings(){
            ObservableList<Booking> tableBookings = FXCollections.observableArrayList();
            DatabaseHandler handler = new DatabaseHandler();
            Connection connect = handler.database();
            try {
                String sql = "SELECT * FROM Bookings WHERE tableID ='" + TABLE_ID + "'";
                PreparedStatement checkDatabase = connect.prepareStatement(sql);
                ResultSet resultSet = checkDatabase.executeQuery();
                Booking booking;

                while (resultSet.next()) {
                    booking = new Booking(resultSet.getInt("BookingID"),
                            resultSet.getInt("CustomerUserID"),
                            resultSet.getInt("numberOfGuests"),
                            resultSet.getInt("tableID"),
                            resultSet.getString("bookingDate"));
                    tableBookings.add(booking);
                }
            } catch (NullPointerException n) {
                System.out.println(" ");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return tableBookings;
        }
}
