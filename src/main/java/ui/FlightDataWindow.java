package ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

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



import Project.ticketbooking.Airports;
import Project.ticketbooking.Flight;
import Project.ticketbooking.Quotes;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class FlightDataWindow implements ActionListener{
	JFrame bookingFrame = new JFrame("Add Flight Details");
	 JPanel panel =  new JPanel();
	 JMenuBar menuBar  = new JMenuBar();
	 JMenu menu = new JMenu("Function");
	 JMenuItem item1 = new JMenuItem("Back");
	 JMenuItem item2 = new JMenuItem("Exit");
	 JLabel label1 =new JLabel("Enter origin");
	 JLabel label2 = new JLabel("Enter Destination");
	 JLabel label3  = new JLabel("Enter Date of departure");
	 JLabel label4  = new JLabel("Enter Date of return");
	 JTextField origin = new JTextField(20);
	 JTextField destination = new JTextField(20);
	 JButton button = new JButton("OK!");
	 
	 
	 
	 //Objects to set the 2 DatePickers
	
	 //Set the Date to the Utiliies dtae model
	 UtilDateModel model = new UtilDateModel();
	 UtilDateModel model2 = new UtilDateModel();
	 
	 //Sets todays Date  
	 Properties p = new Properties();
	 
	 //Creates a panel in the Util Date model 
	 JDatePanelImpl datePanel = new JDatePanelImpl(model);
	 JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
	 
	 //Adds the calendr panel to the drop down and the sets the output date format to what is needed
	 JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());	
	 JDatePickerImpl datepicker2 = new JDatePickerImpl(datePanel2,new DateLabelFormatter());
	 
	 public FlightDataWindow()
	 {
		 	Container contentPane = bookingFrame.getContentPane();
			SpringLayout layout = new SpringLayout();
			contentPane.setLayout(layout);
			
			
			//Sets the properties for the date model
			p.put("text.today", "Today");
			p.put("text.month", "Month");
			p.put("text.year", "Year");
			
			
			//Adding the components
			menu.add(item1);
			menu.add(item2);
			menuBar.add(menu);
			bookingFrame.add(menuBar);
			contentPane.add(label1);
			contentPane.add(label2);
			contentPane.add(label3);
			contentPane.add(label4);
			contentPane.add(origin);
			contentPane.add(destination);
			contentPane.add(datePicker);
			contentPane.add(datepicker2);
			contentPane.add(button);
			
			//ActionListener
			button.addActionListener(this);
			item1.addActionListener(this);
			item2.addActionListener(this);
			
			//Setting the layout
			layout.putConstraint(SpringLayout.WEST, label1, 10, SpringLayout.WEST, contentPane);
			layout.putConstraint(SpringLayout.NORTH, label1, 30, SpringLayout.NORTH, contentPane);
			layout.putConstraint(SpringLayout.WEST, label2, 10, SpringLayout.WEST, contentPane);
			layout.putConstraint(SpringLayout.NORTH, label2, 10, SpringLayout.SOUTH, label1);
			layout.putConstraint(SpringLayout.WEST, label3, 10, SpringLayout.WEST, contentPane);
			layout.putConstraint(SpringLayout.NORTH, label3, 10, SpringLayout.SOUTH, label2);
			layout.putConstraint(SpringLayout.WEST, label4, 10, SpringLayout.WEST, contentPane);
			layout.putConstraint(SpringLayout.NORTH, label4, 20, SpringLayout.SOUTH, label3);
			layout.putConstraint(SpringLayout.WEST, origin, 140, SpringLayout.WEST, label1);
			layout.putConstraint(SpringLayout.NORTH, origin, 30, SpringLayout.NORTH, contentPane);
			layout.putConstraint(SpringLayout.WEST, destination, 140, SpringLayout.WEST, label2);
			layout.putConstraint(SpringLayout.NORTH, destination, 10, SpringLayout.SOUTH,origin );
			layout.putConstraint(SpringLayout.WEST, datePicker, 140, SpringLayout.WEST, label3);
			layout.putConstraint(SpringLayout.NORTH, datePicker, 10, SpringLayout.SOUTH,destination);
			layout.putConstraint(SpringLayout.WEST, datepicker2, 140, SpringLayout.WEST, label4);
			layout.putConstraint(SpringLayout.NORTH, datepicker2, 30, SpringLayout.SOUTH,datePicker);
			layout.putConstraint(SpringLayout.EAST,button,-5,SpringLayout.EAST,contentPane);
			layout.putConstraint(SpringLayout.SOUTH, button, 30,SpringLayout.SOUTH, datepicker2);
			
			
			bookingFrame.pack();
			bookingFrame.setSize(440,250);
			bookingFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			bookingFrame.setVisible(true);
	 }
	 
	 //Makin a popup dialogue box
	 public static void infoBox(String infoMessage, String titleBar)
	 {
	        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	    }
	 
	 
	 public void actionPerformed(ActionEvent e)
	 {
		if(e.getSource() ==button)
		{	
			//OPens a popup dailogue box if the origin or destination details have not been entered
			if(origin.getText().equals("") | destination.getText().equals("")) 
			{
				infoBox("Please enter all the details","Error");
				return;
			}
			else
			{
				try 
				{
				 
					//Gets the UTF-8 queries for the API GET
					String date1 = Quotes.getDateQuery(datePicker.getJFormattedTextField().getText());
					String date2 = Quotes.getDateQuery(datepicker2.getJFormattedTextField().getText());
					String ovalue = Airports.getQuery((origin.getText()));
					String dvalue = Airports.getQuery((destination.getText()));

					try
					{
						Flight.getDetails(ovalue,dvalue,date1,date2);
					}
					catch(Exception ex)
					{
						
						//opens the popup Dailogue box if the user has entered an invalid date
						infoBox("Invalid Date Or no Flights for Entered dates","Error");
						
					}
					finally
					{
						bookingFrame.setVisible(false);
					}
				 
				} 
				catch (UnsupportedEncodingException e1)
				{
					e1.printStackTrace();
				}
			  }
			//Creates the Flight Detail window Object which calls the next Window
			Flights window = new Flights(Flight.flightList);
		 
		}
		else if(e.getSource() == item1)
		{
			bookingFrame.setVisible(false);
			MainWindow m2 = new MainWindow();
		}
		else if(e.getSource() == item2)
		{
			System.exit(0);
		}
	 }
	 
	
}
