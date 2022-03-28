package cafeOrders;
import java.util.*;
import java.sql.*;
/*
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

*/
public class orders {
	
	private int order_id;
	private int cust_id;
	/*ArrayList<orders> outstandingOrders  = new ArrayList <>();
	
	ArrayList<orders> completedOrders  =  new ArrayList <>();
	private int orderNum;
	private ArrayList<Double> costItems  = new ArrayList <>();
	private ArrayList<String> nameItems  = new ArrayList <>();
	private ArrayList<Integer> waitItems  = new ArrayList <>();
	//private boolean approved;
	*/
	public static Connection ordersDatabase() {
		Connection OrdersDatabase;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			OrdersDatabase = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cafe" , "root", "");
			return OrdersDatabase;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args) {
	//System.out.println(getWait());
	ArrayList<Integer> items = new ArrayList<>();
	items.add(2);
	items.add(3);
	items.add(5);
	items.add(1);
	
	setItemsOrdered(items);
	/*	
		Connection connect = ordersDatabase();
	try {
		Statement statement = connect.createStatement();
		String mysql = "SELECT * FROM items_ordered;";
		PreparedStatement checkDatabase = connect.prepareStatement(mysql);
		ResultSet resultSet = checkDatabase.executeQuery();
		
		while(resultSet.next()) {
			System.out.print(resultSet.getString("menu_id") + " ");
			System.out.print(resultSet.getString("order_id") + " ");
			System.out.print(resultSet.getString("cust_id") + " ");
			System.out.println(resultSet.getString("quantity") + " ");
		}
		
		statement.close();
		connect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	
	}



	
	
	public void setNextOrderId() {
		//READ MAX ORDER ID AND RETURN THAT PLUS ONE 
		Connection connect = ordersDatabase();
		try {
			Statement statement = connect.createStatement();
			String mysql = "SELECT MAX(order_id) FROM items_ordered;";
			PreparedStatement checkDatabase = connect.prepareStatement(mysql);
			ResultSet resultSet = checkDatabase.executeQuery();
			this.order_id = (resultSet.getInt("order_id"));/*
			 + 1;*/
			statement.close();
			connect.close();
			} catch (Exception e) {
				e.printStackTrace();
			}	
	}
	
	/*
	public void setCustId() {
		//GET THE CURRENT CUSTOMERS CUST_ID FROM THE CUSTOMERS CLASS
		
	}
	
	public String getOrderType() {
		//HAVE A MENU TO SELECT WHICH TYPE THE ORDER IS 
		
	}
	
	public String getAddress() {
		//GET A USER INPUTTED ADDRESS FROM THE APP IF THEY SELECT DELIVERY
	}
	
	public int getAvailableDriver() {
		//FIND A DRIVER IN THE STAFF DATABASE THAT IS AVAILABLE AND MARK HIM AS UNAVAILABLE
		
	}*/
	
	public static void setItemsOrdered(ArrayList<Integer> items){
		//ALLOW USERS TO ADD THEIR ITEMS AND SAVE THE ITEM IDS IN A LIST
		Connection connect = ordersDatabase();
		//int cust_id = 1;
		try {
			Statement statement = connect.createStatement();
			String mysql;
			for(int i = 0; i < items.size(); i++) {
				mysql = "INSERT INTO items_ordered (menu_id,order_id,cust_id,quantity)" + "VALUES (" + items.get(i) + ", 2" + ", 2" + ", 1)";
				PreparedStatement checkDatabase = connect.prepareStatement(mysql);
				checkDatabase.executeUpdate(mysql);
			}
				statement.close();
				connect.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public static int getWait() {
		//USE THE MAX ORDER WAIT VIEW TO GET THE WAIT TIME
		Connection connect = ordersDatabase();
		int wait = 0;
		int order_id = 4;
		try {
			Statement statement = connect.createStatement();
				String waitstatement = "SELECT * from max_wait_order where order_id = " + order_id;
				
				PreparedStatement checkDatabase = connect.prepareStatement(waitstatement);
				ResultSet resultSet = checkDatabase.executeQuery();
				wait = (resultSet.getInt("item_wait"));
				statement.close();
				connect.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return wait;
	}
		
	
}

	
	
	
	
	
	/*
	
	
	public void setOrdernum(int newOrderNum) {
		this.orderNum = newOrderNum;
	}
	
	public double getOrderCost() {
		double costOrder;
		for(int i = 0; i < costItems.size();i++) {
			costOrder = costItems.get(i) + costOrder;
		}
		return costOrder;
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	public double orderWait() {
		for every item in order
		if item wait > max wait
			max wait = item wait
		return 50.0;
	}

}

class delivery extends orders{
	private int driverNum;
	String address;
	public void assignDriver(driverNum) {
		this.driverNum = driverNum;
	}
	public int deliveryWait(anOrder) {
		return waitTime;
	}
	public boolean approveOrder(anOrder) {
		//if order approvable
			// get a waiter to approve then return true
		return true;
	}
	public void addOrderDeliver(orders) {
		outstandingOrders.add(orders);
	}
}

class takeaway extends orders{
	public void addOrderTakeaway(Hashtable<String, Integer> anOrder) {
		outstandingOrders.add(anOrder);
	}
}

class eatin extends orders{
	
	int tableNum;
	
	public void addOrderEatin(Hashtable<String, Integer> anOrder) {
		outstandingOrders.add(anOrder);
	}
}*/