package Project.ticketbooking;

import static org.junit.Assert.*;

import org.junit.Test;

public class PassengerTest {

	@Test
	public void ParrengerStringtest() {
		Passenger p1 = new Passenger("Sahejpal","Arneja","1234",21);
		assertTrue(Passenger.passengerList.get(0).toString().equals("Sahejpal Arneja 21 1234"));
		
	}

}
