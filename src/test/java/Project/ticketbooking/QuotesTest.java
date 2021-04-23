package Project.ticketbooking;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class QuotesTest {

	protected String origin="BUD-sky";
	protected String destination = "LOND-sky";
	String date1,date2;
	JsonElement jje;
	JsonArray quotes,carriers;
	JsonObject quote = new JsonObject();

	@Before
	public void setUp() throws Exception {
		date1 = Quotes.getDateQuery("2020-12-21");
		date2 = Quotes.getDateQuery("2020-01-13");
		jje = Quotes.getQuotesJson(origin, destination,date1 ,date2 );
		quotes = Quotes.getQuoteArray(jje.getAsJsonObject());
		
		carriers = Quotes.getCarriersArray(jje.getAsJsonObject());
		quote = quotes.get(0).getAsJsonObject();
	}

	@Test
	public void getQuotesJsontest() throws UnsupportedEncodingException, Exception
	{
		
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
	public void getCarrierTest()
	{
		String carrier = Quotes.getCarriers(Quotes.getCarrierIDs(quote),jje.getAsJsonObject());
		if(carrier.equals("Wizz Air"))
		{
			assertTrue(true);
		}
		else if(carrier.equals("Unknown airline"))
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void getPriceTest()
	{
		double price = Quotes.getPrice(quote);
		
		
		assertTrue(price == 16.0 );
		
	}
	@Test
	public void getStatusTest()
	{
		if(Quotes.getFlightDetails(quote).equals("true"))
		{
			assertTrue(true);
		}
		
	}
}
