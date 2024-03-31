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
public class ChangeDoctorData extends JFrame implements ActionListener {

	private JFrame frame;// Add ChangeDoctorData frame

	private JLabel lblSpecialization;// Label for Specialization
	private JTextField txtSpecialization;// Text field Specialization

	private JLabel lblName;// Label for Name
	private JTextField txtName;// Text field for Name

	private JButton btnChange;// Button to perform add change operation
	int doctorID;
	public ChangeDoctorData(int doctorID) {
		this.doctorID = doctorID;
		
		frame = new JFrame("Hospital Information System - Update Data");
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

		// Create and add lblName
		lblName = new JLabel("Floor No:");
		lblName.setBounds(100, 50, 100, 25);
		frame.getContentPane().add(lblName);

		// Create and add txtName
		txtName = new JTextField();
		txtName.setBounds(200, 50, 200, 25);
		frame.getContentPane().add(txtName);

		// Create and add add change button
		btnChange = new JButton("Update Data");
		btnChange.setBounds(200, 140, 200, 25);
		btnChange.addActionListener(this);
		frame.getContentPane().add(btnChange);
		loadDoctor(doctorID);
		// Set login screen visible
		frame.setVisible(true);
	}

	public Doctor loadDoctor(int doctorID) {
		List<Doctor> doctors = Utility.readDoctors();
		Doctor doctor = doctors.stream().filter(doc -> doc.getId()==doctorID).findFirst()
				.orElse(null);
		txtSpecialization.setText(doctor.getSpecialization());
		txtName.setText(doctor.getName());
		return doctor;
	}
	/**
	 * This function performs action on button click
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		List<Doctor> doctors = Utility.readDoctors();
		
		String specialization = txtSpecialization.getText();
		String name = txtName.getText();

		if (specialization.isEmpty()) {// If specialization is not given, display error and return
			JOptionPane.showMessageDialog(null, "Please enter specialization!");
			txtSpecialization.requestFocus();// Set focus on specialization
			return;
		}

		if (name.isEmpty()) {// If name is not given, display error and return
			JOptionPane.showMessageDialog(null, "Please enter floorNo!");
			txtName.requestFocus();// Set focus on name
			return;
		}
		
		Doctor doctor = doctors.stream().filter(doc -> doc.getId()==doctorID).findFirst()
				.orElse(null);
		doctor.setSpecialization(specialization);
		doctor.setName(name);
		
		Utility.writeDoctors(doctors);
		JOptionPane.showMessageDialog(null, "Doctor successfully added!");
		frame.dispose();
	}

	
}
