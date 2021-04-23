package ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

public class MainWindow implements ActionListener  {
	JFrame mainWindow = new JFrame("Flight Booking");
	JButton bookButton  = new JButton("Book Flights");
	JButton checkButton = new JButton("View Bookings");
	
	public  MainWindow()
	{
		Container contentpane = mainWindow.getContentPane();
		SpringLayout layout = new SpringLayout();
		contentpane.setLayout(layout);
		
		//Adding compenent and Assigning action Listeners
		contentpane.add(bookButton);
		bookButton.addActionListener(this);
		contentpane.add(checkButton);
		checkButton.addActionListener(this);
		
		//Setting Layout
		layout.putConstraint(SpringLayout.WEST, bookButton, 65,SpringLayout.WEST, contentpane);
		layout.putConstraint(SpringLayout.NORTH, bookButton, 30,SpringLayout.NORTH, contentpane);
		layout.putConstraint(SpringLayout.WEST, checkButton, 65,SpringLayout.WEST, contentpane);
		layout.putConstraint(SpringLayout.NORTH, checkButton,20 ,SpringLayout.SOUTH, bookButton);
		
		mainWindow.pack();
		mainWindow.setSize(270,150);
		mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == bookButton)
		{
			mainWindow.setVisible(false);
			//Opens the Page to enter the Fligh details
			FlightDataWindow f = new FlightDataWindow();
		}
		else if(e.getSource() == checkButton)
		{
			mainWindow.setVisible(false);
			//Opens the page to enter the Bookin ID to search
			CheckBooking c = new CheckBooking();
			
		}
	}
	
	
}
