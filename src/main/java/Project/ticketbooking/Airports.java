
package Project.ticketbooking;


import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;

import com.mashape.unirest.http.*;
import java.io.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class Airports {
	
	//The link to the API for getting the names of Airports
	private static String originIdHost= "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/autosuggest/v1.0/";
	
	//Character set
	protected static String charset = "UTF-8";

	//headers
	protected static String apiHostHeader =  "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com";
	protected static String apiKeyHeader = "f1439dedd0mshdd85817ff6b738cp1382bdjsn5c6a26817385";
	
	//function to convert charset to Unicode and takes parameter
	public static String getQuery(String s) throws UnsupportedEncodingException
	{
		String query = String.format("%s",URLEncoder.encode(s,charset));
		return query;
	}
	
	
	//gets the Json from the API
	public static JsonObject getAirportJson(String query) throws Exception
	{
		HttpResponse<JsonNode> response = Unirest.get(originIdHost+"HU/EUR/en-EN/?query="+query)
    			.header("x-rapidapi-host", apiHostHeader)
    			.header("x-rapidapi-key",apiKeyHeader)
    			.asJson();
		//create Parser
    	JsonParser jp =new JsonParser();
    	//Create Json OBJECT and convert JSON to string 
    	JsonObject jje =(JsonObject) jp.parse(response.getBody().toString());
    	return jje;
	}
	
	//Puts the airport names in a new list after removing the quotations
    public static ArrayList<String> airportList(JsonObject Json)
    {
    	//Get Array from the JSON
    	JsonArray Places = (JsonArray) Json.get("Places");
    	//Create iterator to go through array
    	Iterator<JsonElement> airport = Places.iterator();
    	//creating new list to add the names of airports without the quotations
    	ArrayList<String> airports = new ArrayList<String>(); 
    	
    	//Adding airport names to the list
    	while(airport.hasNext())
    	{
    		String placeId = airport.next().getAsJsonObject().get("PlaceId").toString();
    		airports.add(placeId.substring(1,placeId.length()-1));
    	}
    	return airports;
    	
    }

}
