package Project.ticketbooking;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;



public class Flight extends Quotes {
	
	public String origin;
	public String destination;
	public String originAirport;
	public String destinationAirport;
	public String carrier;
	public String status;
	public double price;
	public String date1;
	public String date2;
	public static ArrayList<Flight> flightList = new ArrayList<Flight>();
	
	//Sets the Attribute values
	public Flight(String origin,String destination,String date1,String date2) throws Exception
	{
		this.origin = origin;
		this.destination = destination;
		this.date1 =date1;
		this.date2 =date2;
	}
	public static void infoBox(String infoMessage, String titleBar)
	 {
	        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	    }
	 
	
	
	//Goes throught the API functions to get all the required details of the flight
	public static void getDetails(String origin, String destination,String date1, String date2) throws Exception
	{
		
		//Creating arrays with the names of airports in the given cities
		ArrayList<String> originA = airportList(getAirportJson(origin));
		ArrayList<String> destinationA =airportList(getAirportJson(destination));
		
		//Looping through each origin and destinaton airport pair to find outbound flights
		for(int i = 0;i<=originA.size()-1;i++)
		{
			for(int j = 0;j <= destinationA.size()-1;j++)
			{
				//creatses the Flight Object for the current Airport pair
				Flight f1 = new Flight(origin,destination,date1,date2);
				f1.originAirport = originA.get(i);
				f1.destinationAirport = destinationA.get(j);
				
				
				
				//Gets the Json Object for the current Airport pair
				JsonObject Quotes = getQuotesJson(originA.get(i),destinationA.get(j),date1,date2);
				
				//gets the airfare quote array
				JsonArray quote = getQuoteArray(Quotes);
				
				//Looping through the airfare array and getting the values of all the Details of th flight
				for(JsonElement k:quote)
				{
				 if(k.isJsonNull())
					{ 
						infoBox("There are no Flights","Error"); 
					}
					 else if(k.isJsonObject())
						
					 {
						f1.price = getPrice((JsonObject) k);
						if(getFlightDetails((JsonObject)k).equals("true"))
							f1.status = "Direct";
						else
							f1.status = "InDirect";
						f1.carrier = getCarriers(getCarrierIDs((JsonObject)k),Quotes);
						f1.carrier = f1.carrier.substring(1,f1.carrier.length()-1);
							
						
						//adds The flight to the list
						Flight.flightList.add(f1);
						
					}
					
				}
				
			}
		}	
		
	}
	
	
	@Override
	public String toString()
	{
		return (this.origin +" to " +this.destination+ "    \n" 
				+this.originAirport +" to "+this.destinationAirport+"   \n"
				+this.carrier+"   \t\t"+ this.price+"   \n"
				+this.date1+"  \n");
	}
	

}
