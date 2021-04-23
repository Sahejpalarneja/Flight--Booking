package Project.ticketbooking;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class AirportsTest {

	Airports airport = new Airports();
	
	
	
	
	
	@Test
	public void JsonObjecttest() throws Exception 
	{

		JsonElement jje = Airports.getAirportJson(Airports.getQuery("Budapest"));
		if(jje.isJsonObject())
		{
			assertTrue(true);
		}
		else
		{
			fail();
		}
		
	}
	
	@Test
	public void airportListTest() throws UnsupportedEncodingException, Exception
	{

		JsonObject jje = Airports.getAirportJson(Airports.getQuery("Budapest"));
		ArrayList<String> result  = Airports.airportList(jje);
		if(result.size()>0)
		{
			assertTrue(true);
		}
		else
		{
			fail();
		}
	}
	

}
