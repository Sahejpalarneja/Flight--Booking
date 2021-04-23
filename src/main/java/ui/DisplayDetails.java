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

public class DisplayDetails implements ActionListener {
	
	JFrame frame = new JFrame();
	JMenuBar menuBar  = new JMenuBar();
	JMenu menu = new JMenu("Function");
	JMenuItem item1 = new JMenuItem("Back");
	JMenuItem item2 = new JMenuItem("Exit");
	JLabel label1 = new JLabel();
	JLabel label2  = new JLabel();
	
	
	//Used to dislay the Booking Details after the Search
	public DisplayDetails(Booking b1)
	{
		SpringLayout layout = new SpringLayout();
		Container contentPane  = new Container();
		contentPane = frame.getContentPane();
		contentPane.setLayout(layout);
		
		//Setting Size
		label1.setSize(40,20);
		label2.setSize(40,20);
		label1.setText(b1.flight.toString());
		label2.setText(b1.passengers.toString());
		
		//Adding Components
		menu.add(item1);
		menu.add(item2);
		menuBar.add(menu);
		frame.add(menuBar);
		contentPane.add(label1);
		contentPane.add(label2);
		
		//Assigning ActionListeners
		item1.addActionListener(this);
		item2.addActionListener(this);
		
		//Setting Layout
		layout.putConstraint(SpringLayout.WEST, label1, 40, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, label1, 20, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, label2, 30, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, label2, 20, SpringLayout.SOUTH, label1);
		

		frame.pack();
		frame.setSize(300,300);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);

		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == item1)
		{
			frame.setVisible(false);
			CheckBooking c = new CheckBooking();
		}
		else if(ae.getSource() == item2)
		{
			System.exit(0);
		}
	}
}
