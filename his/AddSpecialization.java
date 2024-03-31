package his;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * This class provides GUI for adding specialization
 * 
 * 
 *
 */
public class AddSpecialization extends JFrame implements ActionListener {

	private JFrame frame;// Add specialization frame

	private JLabel lblSpecialization;// Label for Specialization
	private JTextField txtSpecialization;// Text field Specialization

	private JLabel lblFloorNo;// Label for Floor No
	private JTextField txtFloorNo;// Text field for Floor No

	private JLabel lblCapacity;// Label for Capacity
	private JTextField txtCapacity;// Text field for Capacity

	private JButton btnAddSpecialization;// Button to perform add specialization operation

	public AddSpecialization() {
		frame = new JFrame("Hospital Information System - Add Specialization");
		frame.setSize(550, 300);// if you do not set size you will have to resize when it runs
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);

		// Create and add Specialization label
		lblSpecialization = new JLabel("Specialization:");
		lblSpecialization.setBounds(100, 20, 100, 25);
		frame.getContentPane().add(lblSpecialization);

		// Create and add Specialization text field
		txtSpecialization = new JTextField();
		txtSpecialization.setBounds(200, 20, 200, 25);
		frame.getContentPane().add(txtSpecialization);

		// Create and add Floor No label
		lblFloorNo = new JLabel("Floor No:");
		lblFloorNo.setBounds(100, 50, 100, 25);
		frame.getContentPane().add(lblFloorNo);

		// Create and add Floor no text field
		txtFloorNo = new JTextField();
		txtFloorNo.setBounds(200, 50, 200, 25);
		frame.getContentPane().add(txtFloorNo);

		// Create and add capacity label
		lblCapacity = new JLabel("Floor Capacity:");
		lblCapacity.setBounds(100, 80, 100, 25);
		frame.getContentPane().add(lblCapacity);

		// Create and add capacity text field
		txtCapacity = new JTextField();
		txtCapacity.setBounds(200, 80, 200, 25);
		frame.getContentPane().add(txtCapacity);

		// Create and add add patient button
		btnAddSpecialization = new JButton("Add Specialization");
		btnAddSpecialization.setBounds(200, 140, 200, 25);
		btnAddSpecialization.addActionListener(this);
		frame.getContentPane().add(btnAddSpecialization);

		// Set login screen visible
		frame.setVisible(true);
	}

	/**
	 * This function performs action on button click
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		List<Specialization> specializations = Utility.readSpecializations();
		
		String specialization = txtSpecialization.getText();
		String floorNo = txtFloorNo.getText();
		String capacity = txtCapacity.getText();

		if (specialization.isEmpty()) {// If specialization is not given, display error and return
			JOptionPane.showMessageDialog(null, "Please enter specialization!");
			txtSpecialization.requestFocus();// Set focus on specialization
			return;
		}

		if (floorNo.isEmpty()) {// If floorNo is not given, display error and return
			JOptionPane.showMessageDialog(null, "Please enter floorNo!");
			txtFloorNo.requestFocus();// Set focus on floorNo
			return;
		}
		
		if (capacity.isEmpty()) {// If capacity is not given, display error and return
			JOptionPane.showMessageDialog(null, "Please enter capacity!");
			txtCapacity.requestFocus();// Set focus on capacity
			return;
		}
		
		Specialization  sp= new Specialization(specialization, // specialization
				Integer.parseInt(floorNo), // floor
				Integer.parseInt(capacity)); // capacity
		specializations.add(sp);
		
		Utility.writeSpecializations(specializations);
		JOptionPane.showMessageDialog(null, "Specialization successfully added!");
		frame.dispose();
	}

	
}
