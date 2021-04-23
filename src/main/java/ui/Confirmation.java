package ui;


import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import Project.ticketbooking.Booking;

public class Confirmation implements ActionListener{
	
	JFrame frame = new JFrame();
	JMenuBar menuBar  = new JMenuBar();
	JMenu menu = new JMenu("Function");
	JMenuItem item1 = new JMenuItem("Exit");
	JLabel label1 = new JLabel();
	JLabel label4 =new JLabel("Your Booking ID is: ");
	JLabel label2 = new JLabel();
	JLabel label3  = new JLabel();
	
	
	//USed to display the Booking Details after the User has entered the Passenger Details
	public Confirmation (Booking b1)
	{
		SpringLayout layout = new SpringLayout();
		Container contentPane  = new Container();
		contentPane = frame.getContentPane();
		contentPane.setLayout(layout);
		
		//Setting Size
		label1.setSize(40,20);
		label2.setSize(40,20);
		label3.setSize(40,20);
		label4.setSize(40, 20);
		
		//Setting Values
		label1.setText(b1.flight.toString());
		label2.setText(Integer.toString(b1.bookingID));
		label3.setText(b1.passengers.toString());
		
		//Adding Components 
		
		menu.add(item1);
		menuBar.add(menu);
		frame.add(menuBar);
		contentPane.add(label4);
		contentPane.add(label1);
		contentPane.add(label2);
		contentPane.add(label3);
		
		//Adding ActionListeners
		item1.addActionListener(this);
		
		
		
		//Setting Layout
		layout.putConstraint(SpringLayout.WEST, label1, 30, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, label1, 20, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, label4, 30, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, label4, 20, SpringLayout.SOUTH, label1);
		layout.putConstraint(SpringLayout.WEST, label2, 5, SpringLayout.EAST, label4);
		layout.putConstraint(SpringLayout.NORTH, label2, 60, SpringLayout.NORTH,contentPane );
		layout.putConstraint(SpringLayout.WEST, label3, 30, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, label3, 20, SpringLayout.SOUTH,label2);
	
		
			
	
		frame.pack();
		frame.setSize(300,300);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		System.exit(0);
	}

}
