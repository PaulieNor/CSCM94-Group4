package src.main.manager; 
import java.util.ArrayList; 

/**
* @Author James McMillan
* @Version 1.1
*
* This class allows the manager to create and modify events. 
*/


public class ManageEvent {

	//Instance variables for an Event object. 
	public String eventName;
	public String eventDate;
	public int eventCapacity;
	public String eventDescription; 


	//Constructor methods. 
	public manageEvent(String eventName, String eventDate, 
			int eventCapacity, String eventDescription) {
	this.eventName = eventName;
	this.eventDate = eventDate; 
	this.eventCapacity = eventCapacity;
	this.eventDescription = eventDescription; 
	}


	//Setters -> As the manager sets these, change to private? Or perhaps protected? 
	public void setEventName(String newEventName) {
		
		//Validation.
		if (eventName == null) {
			throw new NullPointerException("Please enter a name for the event: \n");
		} else {
			this.eventName = newEventName; 
		}	
	}
	
	//could add in error handling here, for dates within expected range. 
	public void setEventDate(String newEventDate) {
		this.eventDate = newEventDate; 
	}

	//Some error handling here, capacity MUST be >= 1! 
	//Please verify that exception choice is appropriate.
	public void setEventCapacity(int newEventCapacity) {
		
		if (eventCapacity < 1) {
			throw new IllegalArgumentException 
			("At least one person required, please enter a capacity: "); 
		} else {
			this.eventCapacity = newEventCapacity; 
		}	
	}

	public void setEventDescription(String newEventDescription) {
		this.eventDescription = newEventDescription; 
	}


	//Getter methods
	
	
	

	//toString method to summarise an event when queried.  
	
	
	//Getter method that allows query by eventName, returns .toString method with all 
	//event details. Same as getRequest() on diagram.
	
	
	
	//tablesRequired() method call. Int data type. 
	
	
	
	//addEvent() method call. Boolean -> Confused here. 
	
	
	//editEvent() method call. Boolean -> again confused, what happens when this is called?
	
	
	//removeEvent() method call. Boolean. 
	








}