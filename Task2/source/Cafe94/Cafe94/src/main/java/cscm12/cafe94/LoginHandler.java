package cscm12.cafe94;

import java.sql.*;

/**
 * [LoginHandler]
 * Makes login calls to Database, returns whether login was successful and the staff role to the frontend.
 * @author Sumi Sunuwar, Paul Norman
 * @version 1.0
 */

public class LoginHandler extends DatabaseHandler {

    /**
     * [loginStatus]
     * Compares inputed variables to database usernames and passwords, returns whether login is successful.
     * @param username Inputted username string.
     * @param password Inputted password string.
     * @return If login was successful returns true, otherwise false.
     */
    public boolean loginStatus(String username, String password){
            boolean loginStatus = false;
            Connection connect = database();
            try {
                Statement statement = connect.createStatement();
                String sql = "SELECT * FROM ManageStaff WHERE StaffUsername='"
                        + username + "'" + " AND " +
                        "StaffPassword='" + password + "'";
                PreparedStatement checkDatabase = connect.prepareStatement(sql);
                ResultSet resultSet = checkDatabase.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.getString("StaffPassword").equals(password)) {
                        loginStatus = true;
                    }
                }
                statement.close();
                connect.close();
                return loginStatus;
            } catch (Exception e) {
                e.printStackTrace();
                return loginStatus;
            }
    }


    /**
     * [staffType]
     * Gets the staff type of the user for scene changing purposes.
     * @param username Inputted username string.
     * @return String containing the staff type, otherwise returns string containing "empty".
     * @throws SQLException Throws if bad SQL call.
     */
    public String staffType(String username) throws SQLException {
        String staffType = "empty";
        Connection connect = database();
        try {
            Statement statement = connect.createStatement();
            String sql = "SELECT * FROM ManageStaff WHERE StaffUsername='"
                    + username + "'";
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery();
            staffType = resultSet.getString("StaffType");
            statement.close();
            connect.close();
        } catch (SQLException e){
            System.out.println("Username not found.");
        } catch (Exception e){
            e.printStackTrace();
        }
        return staffType;
    }
}
