package src.main.Customers; 


import java.sql.*;
import java.collections.*;
import java.util.*; 

/**
* @Author James McMillan
* @Version v1.0
*/

public class CustomerDBConnector {

	//I based this one on Christian's for the Manager class. It is likely broken but needs
	//minor tweaks to fit CustomerDatabase context. My MariaDB linked database. 
	public static Connection getConnection() throws SQLException {
		String username = "localhost";
		String  password = "passporttwo"; 
		String url = "jdbc:mysql://localhost:3306/Cafe94"
		Connection customerDBConnection = DriverCustomers.getConnection(url, username, password);
		
		return customerDBConnection; 
	}
}