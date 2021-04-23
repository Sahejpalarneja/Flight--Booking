package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


import Project.ticketbooking.Booking;

public class CheckBooking implements ActionListener{

	JFrame frame = new JFrame("Enter Booking ID");
	JPanel panel = new JPanel();
	JMenuBar menuBar  = new JMenuBar();
	JMenu menu = new JMenu("Function");
	JMenuItem item1 = new JMenuItem("Back");
	JMenuItem item2 = new JMenuItem("Exit");
	JButton button = new JButton("OK");
	JTextField bookingID = new JTextField(20);
	
	
	//Creates the Gui when an Object has been created
	public CheckBooking()
	{
		//Setting Sizes
		button.setSize(20,20);
		
		//Adding components
		menu.add(item1);
		menu.add(item2);
		menuBar.add(menu);
		frame.add(menuBar);
		panel.add(bookingID,BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
		
		frame.add(panel);
		
		//addinf ActionListeners
		button.addActionListener(this);
		item1.addActionListener(this);
		item2.addActionListener(this);
		
		frame.setSize(200,200);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	
	//makes a pop up Message box
	 public static void infoBox(String infoMessage, String titleBar)
	    {
	        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	    }
	
	 
	 /*passes the Booking ID number to the Search function and Display the Booking details
	 	if booking is found otherwise opens a info box
	 */
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() ==button)
		{
			int ID = Integer.parseInt(bookingID.getText());
			try 
			{
				if(Booking.search(ID).bookingID == 0)
				{
					infoBox("Wrong Booking ID","Error");
				}
				else
				{
					frame.setVisible(false);
					DisplayDetails details = new DisplayDetails(Booking.search(ID));
				}
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else if(ae.getSource() == item1)
		{
			frame.setVisible(false);
			MainWindow m2 = new MainWindow();
		}
		else if(ae.getSource() == item2)
		{
			System.exit(0);
		}
	}

}
