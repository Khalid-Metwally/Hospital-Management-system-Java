package his;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/**
 * This class provides GUI for adding patient
 * 
 *
 */
public class AddPatient extends JFrame implements ActionListener {

	private JFrame frame;// Add patient frame

	private JLabel lblPatientID;// Label for Patient ID
	private JTextField txtPatientID;// Text field for Patient ID

	private JLabel lblPatientName;// Label for Patient Name
	private JTextField txtPatientName;// Text field for Patient Name

	private JLabel lblIllness;// Label for Illness
	private JTextField txtIllness;// Text field for Illness

	private JLabel lblFloor;// Label for floor
	private JTextField txtFloor;// Text field for floor

	private JButton btnAddPatient;// Button to perform add patient operation

	public AddPatient() {
		frame = new JFrame("Hospital Information System - Add Patient");
		frame.setSize(550, 300);// if you do not set size you will have to resize when it runs
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);

		// Create and add patient id label
		lblPatientID = new JLabel("Patient ID:");
		lblPatientID.setBounds(100, 20, 100, 25);
		frame.getContentPane().add(lblPatientID);

		// Create and add patient id text field
		txtPatientID = new JTextField();
		txtPatientID.setBounds(200, 20, 200, 25);
		frame.getContentPane().add(txtPatientID);

		// Create and add patient name label
		lblPatientName = new JLabel("Patient Name:");
		lblPatientName.setBounds(100, 50, 100, 25);
		frame.getContentPane().add(lblPatientName);

		// Create and add patient name text field
		txtPatientName = new JTextField();
		txtPatientName.setBounds(200, 50, 200, 25);
		frame.getContentPane().add(txtPatientName);

		// Create and add illness label
		lblIllness = new JLabel("Illness:");
		lblIllness.setBounds(100, 80, 100, 25);
		frame.getContentPane().add(lblIllness);

		// Create and add illness text field
		txtIllness = new JTextField();
		txtIllness.setBounds(200, 80, 200, 25);
		frame.getContentPane().add(txtIllness);

		// Create and add floor label
		lblFloor = new JLabel("Floor:");
		lblFloor.setBounds(100, 110, 100, 25);
		frame.getContentPane().add(lblFloor);

		// Create and add floor text field
		txtFloor = new JTextField();
		txtFloor.setBounds(200, 110, 200, 25);
		frame.getContentPane().add(txtFloor);

		// Create and add add patient button
		btnAddPatient = new JButton("Add Patient");
		btnAddPatient.setBounds(200, 140, 200, 25);
		btnAddPatient.addActionListener(this);
		frame.getContentPane().add(btnAddPatient);

		// Set login screen visible
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		List<Patient> patients = Utility.readPatients();
		List<Specialization> specializations = Utility.readSpecializations();
		
		String patientID = txtPatientID.getText();
		String name = txtPatientName.getText();
		String illness = txtIllness.getText();
		String floor = txtFloor.getText();

		if (patientID.isEmpty()) {// If Patient ID is not given, display error and return
			JOptionPane.showMessageDialog(null, "Please enter Patient ID!");
			txtPatientID.requestFocus();// Set focus on Patient ID
			return;
		}

		if (name.isEmpty()) {// If name is not given, display error and return
			JOptionPane.showMessageDialog(null, "Please enter name!");
			txtPatientName.requestFocus();// Set focus on name
			return;
		}
		
		if (illness.isEmpty()) {// If illness is not given, display error and return
			JOptionPane.showMessageDialog(null, "Please enter illness!");
			txtIllness.requestFocus();// Set focus on illness
			return;
		}
		if (floor.isEmpty()) {// If floor is not given, display error and return
			JOptionPane.showMessageDialog(null, "Please enter floor!");
			txtFloor.requestFocus();// Set focus on illness
			return;
		}
		int floorNo = Integer.parseInt(floor);
		//Check that floor has the capacity
		Specialization specialization = specializations.stream().filter(sp -> sp.getFloorNo()==floorNo).findFirst()
				.orElse(null);
		if(specialization == null) {
			JOptionPane.showMessageDialog(null, "Floor does not exist! Please add floor with specialization!");
			return;
		}
		//Get the floor capacity
		int capacity = specialization.getCapacity();
		//Count already admitted patient to that floor
		long alreadyAdmitted = patients.stream().filter(pt -> pt.getFloor() == floorNo).count();
		
		if(alreadyAdmitted>=capacity) {
			JOptionPane.showMessageDialog(null, "Floor capacity has reached. Please increase capacity first.");
			return;
		}
		
		Patient patient = new Patient(
				Integer.parseInt(patientID), 
				name, 
				illness, 
				floorNo);
		patients.add(patient);
		Utility.writePatients(patients);
		JOptionPane.showMessageDialog(null, "Patient successfully added!");
		frame.dispose();
	}

}
