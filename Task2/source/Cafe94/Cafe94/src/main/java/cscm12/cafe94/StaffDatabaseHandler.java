package cscm12.cafe94;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *"StaffDatabaseHandler" interfaces with the database for St
 *
 * @author Sumi Sunuwar
 * @version 1.0
 */
public class StaffDatabaseHandler extends DatabaseHandler{

    /**
     * <code>downloadStaffTable</code> fetches information of staff members.
     * @return <code>ObservableList</code> of Staff details.
     */
    public ObservableList<ManageStaff> downloadStaffTable() {
        ObservableList<ManageStaff> staffList = FXCollections.observableArrayList();
        Connection connect = database();
        try {
            String sql = "SELECT * FROM Staff";
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery();
            ManageStaff staff;

            while (resultSet.next()) {
                staff = new ManageStaff(resultSet.getString("First_Name"),
                        resultSet.getString("Last_Name"),
                        resultSet.getString("StaffType"),
                        resultSet.getInt("HoursToWork"),
                        resultSet.getString("StaffUsername"),
                        resultSet.getString("StaffPassword"));
                staffList.add(staff);
            }
        } catch (NullPointerException n) {
            System.out.println(" ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staffList;
    }


}
