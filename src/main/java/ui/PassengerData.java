package ui;


import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Project.ticketbooking.*;
public class PassengerData implements ActionListener {
	JFrame frame = new JFrame("Add Details");
	JPanel panel = new JPanel();
	JMenuBar menuBar  = new JMenuBar();
	JMenu menu = new JMenu("Function");
	JMenuItem item1 = new JMenuItem("Back");
	JMenuItem item2 = new JMenuItem("Exit");
	JLabel label1 = new JLabel("Enter First Name");
	JLabel label2 = new JLabel("Enter Last Name");
	JLabel label3 = new JLabel("Enter Age");
	JLabel label4 = new JLabel("Enter SSN");
	JTextField fName = new JTextField(20);
	JTextField lName = new JTextField(20);
	JTextField age = new JTextField(20);
	JTextField SSN= new JTextField(20);
	JButton button2 = new JButton("Add Passenger");
	JButton button = new JButton("Ok");
	
	public PassengerData()
	{
		Container contentPane = frame.getContentPane();
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		
		//Adding the components
		menu.add(item1);
		menu.add(item2);
		menuBar.add(menu);
		frame.add(menuBar);
		contentPane.add(label1);
		contentPane.add(label2);
		contentPane.add(label3);
		contentPane.add(label4);
		contentPane.add(fName);
		contentPane.add(lName);
		contentPane.add(age);
		contentPane.add(SSN);
		
		//adding buttons to the Panel and Assigning the ActionListeners
		panel.add(button);
		button.addActionListener(this);
		panel.add(button2);
		button2.addActionListener(this);
		item1.addActionListener(this);
		item2.addActionListener(this);
		contentPane.add(panel);
		
		
		//Setting Layout
		layout.putConstraint(SpringLayout.WEST, label1, 10, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, label1, 30, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, label2, 10, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, label2, 10, SpringLayout.SOUTH, label1);
		layout.putConstraint(SpringLayout.WEST, label3, 10, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, label3, 10, SpringLayout.SOUTH, label2);
		layout.putConstraint(SpringLayout.WEST, label4, 10, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, label4, 20, SpringLayout.SOUTH, label3);
		layout.putConstraint(SpringLayout.WEST, fName, 140, SpringLayout.WEST, label1);
		layout.putConstraint(SpringLayout.NORTH, fName, 30, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, lName, 140, SpringLayout.WEST, label2);
		layout.putConstraint(SpringLayout.NORTH, lName, 10, SpringLayout.SOUTH,fName );
		layout.putConstraint(SpringLayout.WEST, age, 140, SpringLayout.WEST, label3);
		layout.putConstraint(SpringLayout.NORTH, age, 10, SpringLayout.SOUTH,lName);
		layout.putConstraint(SpringLayout.WEST, SSN, 140, SpringLayout.WEST, label4);
		layout.putConstraint(SpringLayout.NORTH, SSN, 30, SpringLayout.SOUTH,age);
		layout.putConstraint(SpringLayout.WEST,panel , 10, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.SOUTH, SSN);
		
		frame.pack();
		frame.setSize(440,260);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	
	//Creates a dailogue box 
	 public static void infoBox(String infoMessage, String titleBar)
	    {
	        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	    }
	 
	 
	 
	 public void actionPerformed(ActionEvent e)
	 {
		 //closes the Window After Button has been pressed
		 if(e.getSource() == button)
		 {
			 
			 //if Any of the fields has been left empty a warning Dailogue box will be displayed
			 if(fName.getText().equals("")|lName.getText().equals("")|age.getText().equals("")|SSN.getText().equals(""))
			 {
				 infoBox("Please enter all the details","Error");
			 }
			 else
			 {
				try
				{
					int AGE= Integer.parseInt(age.getText());
					 Passenger p1 = new Passenger(fName.getText(),lName.getText(),SSN.getText(),AGE);
					 
					 Booking b = null;
					 b = new Booking();
					 
					 //Opens the Confirmation WIndow
					 Confirmation c = new Confirmation(b);
				}
				catch(NumberFormatException | IOException ex)
				{
					//To Spot whether or not an integer has been inputed in the Text Field 
					infoBox("Wrong Age Input","Error");
				}
				finally
				{
					frame.setVisible(false);
				}	
					 
			 }
		}
		 
		 //Creates new window for  new Passenger Data to be Entered
		 else if(e.getSource() ==  button2)
		 {
			 if(fName.getText().equals("")|lName.getText().equals("")|age.getText().equals("")|SSN.getText().equals(""))
			 {
				 infoBox("Please enter all the details","Error");
			 }
			 else
			 {
				 try {
					 int AGE= Integer.parseInt(age.getText());
					 Passenger p1 = new Passenger(fName.getText(),lName.getText(),SSN.getText(),AGE);
					 frame.setVisible(false);
				 }
				 catch(NumberFormatException ex)
				 {
					 infoBox("Wrong age","error");
				 }
			 }
			 PassengerData p1 = new PassengerData();
			 
		 }
		 else if(e.getSource() == item1)
			{
				frame.setVisible(false);
				Flights window = new Flights(Flight.flightList);
			}
			else if(e.getSource() == item2)
			{
				System.exit(0);
			}
	 }
}
