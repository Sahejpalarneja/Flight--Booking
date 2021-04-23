package ui;
import java.awt.BorderLayout;


import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;






import Project.ticketbooking.*;


public class Flights implements ActionListener {
	
	JFrame frame = new JFrame("Flights");
	JPanel panel = new JPanel();
	
	DefaultTableModel model = new DefaultTableModel();
	JTable table = new JTable(model);
	JScrollPane scroll = new JScrollPane(table);
	JButton button = new JButton("Book!");
	static Object chosen = new Object();
	
	//Makes a JTable from the flight details in the Flight lis
	public Flights(ArrayList<Flight> flightList)
	{
	
		
		
		frame.setSize(200, 300);
		
		Container contentPane =frame.getContentPane();
		
		//Makes a column called flights
		model.addColumn("Flights");
		
		//Adds a row with each row having the details of a flight
		for(int i= 0;i<flightList.size();i++)
		{
			
			model.addRow(new Object[] {flightList.get(i).toString()});
		}
		
		
		table.setFillsViewportHeight(true);
		table.setDefaultEditor(Object.class, null);
		
		//Sets Selection Mode
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Sets the width of the only model
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		table.setRowHeight(30);
		
		//Adding action listener
		button.addActionListener(this);
		
		contentPane.add(scroll);
		panel.add(button);
		contentPane.add(panel,BorderLayout.SOUTH);
	
			
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		//If a row has been selected then gets the source value and set it to the Chosen obJect attribute 
		if(table.getSelectedRow() >= 0)
		{
			chosen = table.getValueAt(table.getSelectedRow(), 0);
			selectedFlight();//Function to return the Chosen FLight
			frame.setVisible(false);
			
			//Creates the PassengerData Object to Open the next Window
			PassengerData p1 = new PassengerData();
		}
	}
	
	//Returns the value in the row selected by the user
	public static Object selectedFlight()
	{
		return chosen;
	}
	
	
}
