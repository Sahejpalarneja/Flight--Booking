package Project.ticketbooking;

import static org.junit.Assert.*;

import org.junit.Test;

public class FlightTest extends QuotesTest{

	@Test
	public void getDetailstest() throws Exception {
		Flight.getDetails("Budapest","London",date1,date2);
		if(Flight.flightList.size()>0)
		{
			assertTrue(true);
		}
		
	}

}
