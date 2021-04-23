package Project.ticketbooking;

import java.io.Serializable;
import java.util.ArrayList;

public class Passenger implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String SSN;
	private int age;
	
	//List of all the Passengers
	public static ArrayList<Passenger> passengerList = new ArrayList<Passenger>();
	
	
	//Constructor
	public Passenger(String first,String last, String SSN,int age)
	{
		
		this.firstName = first;
		this.lastName =last;
		this.age =age;
		this.SSN =SSN;
		passengerList.add(this);
		
		
	}
	
	public String toString()
	{
		return (this.firstName +" "+ this.lastName +" "+this.age+" "+this.SSN);
	}

}
