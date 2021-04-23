package Project.ticketbooking;
import java.util.Random;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.Serializable;
import java.util.ArrayList;


import ui.Flights;

public class Booking implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public ArrayList<Passenger> passengers;
	public Object flight;
	public int bookingID;
	

	//Generates a Random 6 digit long integer which acts as the Booking confirmation ID
	public void generatebookingID()
	{
		Random rand = new Random();
		this.bookingID = rand.nextInt(1000000);
		
	}
	
	
	/*Creates a Booking Object with only the Booking ID attribute
	  Used to identify whether the Booking Id exists or not
	 	*/
	public Booking(int bookingID)
	{
		this.bookingID = bookingID;
	}
	
	
	//Sets the Values needed for the Booking
	public Booking() throws IOException
	{
		
		//Gets the flight Selected by the user
		this.flight = Flights.selectedFlight();
		//List of Passengers 
		this.passengers = Passenger.passengerList;
		this.generatebookingID();
		//Write the Booking details
		this.save();	

	}
	
	
	//Serializes all Booking Objects and writes/appends them to a txt file
	public void save() throws IOException
	{
		try
		{
			FileOutputStream f = new FileOutputStream("Booking.txt",true);
			ObjectOutputStream out = new ObjectOutputStream(f);
			out.writeObject(this);
			out.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	

	
	/*Reads all the Booking objects from the Txt filw and stores them In an ArrayList for searching
	Before returning the list*/
	public static ArrayList<Booking> load() throws IOException, ClassNotFoundException
	{
		ArrayList<Booking> results = new ArrayList<Booking>();

	    FileInputStream fis = null;
	    try {
	        fis = new FileInputStream("Booking.txt");
	        while (true) {
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            results.add((Booking)ois.readObject());
	        }
	    } catch (EOFException ignored) {
	    	
	        // as expected
	    } finally {
	        if (fis != null)
	            fis.close();
	      
	    }
	    return results;
		
	}
	
	
	/*Calls the load() function and searches teh list for the same Booking ID returns the Object if found
	Otherwise creates ad returns a Booking Object with BookingID == 0
	*/
	public static Booking search(int ID) throws ClassNotFoundException, IOException
	{
		for(Booking i: load())
		{
			if(ID == i.bookingID)
			{
				return i;
			}
		}
		Booking b2 = new Booking(0);
		return b2;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb =new StringBuilder();
		sb.append(this.flight.toString());
		for(Passenger i: this.passengers)
		{
			sb.append(i.toString());
			sb.append("\n");
		}
		sb.append("Your BookingID is\n");
		sb.append(this.bookingID);
		return sb.toString();
	}

	 
}
