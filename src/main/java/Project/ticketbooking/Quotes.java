package Project.ticketbooking;

import java.io.UnsupportedEncodingException;
import java.net.*;



import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

public class Quotes extends Airports {
	
	
	//host for getting the flight quotes
	private static String quotesHost = "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsequotes/v1.0/";
	
	
	//converts the given String to Unicode for passing in the respnonse
	public static String getDateQuery(String s) throws UnsupportedEncodingException
	{
		String query = String.format("%s",URLEncoder.encode(s,charset));
		return query;
	}
	
	
	
	//Gets the JSon from the API
	public static JsonObject getQuotesJson(String origin,String destination,String date1,String date2) throws Exception
	{
		
		HttpResponse<JsonNode> response =  Unirest.get(quotesHost+"HU/EUR/en-EN/"+origin+"/"+destination+"/"+date1+"?inboundpartialdate="+date2)
				.header("x-rapidapi-key", apiKeyHeader)
				.header("x-rapidapi-host",apiHostHeader)
				.asJson();
		JsonParser jp = new JsonParser();
		JsonObject jje = (JsonObject)jp.parse(response.getBody().toString());
		return jje;
	}
	
	
	//Gets the Quotes array from the Json
	public static JsonArray getQuoteArray(JsonObject Json)
	{
		JsonArray quotes = (JsonArray) Json.get("Quotes");
		return quotes;
	}
	
	//gets the carriers from Json
	public static JsonArray getCarriersArray(JsonObject Json)
	{
		JsonArray carriers = (JsonArray) Json.get("Carriers");
		return carriers;
	}
	
	
	
	
	//gets the  price from each quote
	public static double getPrice(JsonObject quote)
	{
		String p;
		p = quote.get("MinPrice").toString();
		int price = Integer.parseInt(p);
		return price;
	}
	
	
	//gets whether the flight is direct or not
	public static String getFlightDetails(JsonObject quote)
	{
		String status;
		status = quote.get("Direct").toString();
		return status;
	}
	
	
	//gets a list of all the carriers/flights ids that are in a quote
	public static String getCarrierIDs(JsonObject quote)
	{
		
		JsonObject outboundLeg =  (JsonObject) quote.get("OutboundLeg");
		JsonArray carrierIDs =(JsonArray) outboundLeg.get("CarrierIds");
		return carrierIDs.get(0).toString();
	}
	
	//gets a list of the names of the flight carriers for a trip
	public static String getCarriers(String carrierIds,JsonObject Json)
	{
		JsonArray names = getCarriersArray(Json);
	
		for(JsonElement j: names)
		{
			
			if(j.getAsJsonObject().get("CarrierId").toString().equals(carrierIds))
				{
					return j.getAsJsonObject().get("Name").toString();
					
				}	
		}
		return "Unknown airline";
		
	}
	
	
}
