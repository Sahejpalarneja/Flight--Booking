package Project.ticketbooking;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class BookingTest {

	@Test
	public void loadtest() throws ClassNotFoundException, IOException {
		ArrayList<Booking> books = Booking.load();
		assertTrue(books.size()>0);
		
	}
	
	@Test
	public void searchTest() throws ClassNotFoundException, IOException
	{
		Booking b = Booking.search(907477);
		assertTrue(b.bookingID == 907477);
		
	}

}
