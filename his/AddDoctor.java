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
 * This class provides GUI for adding doctor
 * 
 *
 */
public class AddDoctor extends JFrame implements ActionListener {

	private JFrame frame;// Add doctor frame

	private JLabel lblDoctorID;// Label for Doctor ID
	private JTextField txtDoctorID;// Text field for Doctor ID

	private JLabel lblDoctorName;// Label for Doctor Name
	private JTextField txtDoctorName;// Text field for Doctor Name
	
	private JLabel lblSpecialization;// Label for specialization
	private JTextField txtSpecialization;// Text field for specialization

	private JLabel lblFloor;// Label for floor
	private JTextField txtFloor;// Text field for floor

	private JButton btnAddDoctor;// Button to perform add Doctor operation

	public AddDoctor() {
		frame = new JFrame("Hospital Information System - Add Patient");
		frame.setSize(550, 300);// if you do not set size you will have to resize when it runs
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);

		// Create and add Doctor id label
		lblDoctorID = new JLabel("Doctor ID:");
		lblDoctorID.setBounds(100, 20, 100, 25);
		frame.getContentPane().add(lblDoctorID);

		// Create and add Doctor id text field
		txtDoctorID = new JTextField();
		txtDoctorID.setBounds(200, 20, 200, 25);
		frame.getContentPane().add(txtDoctorID);

		// Create and add Doctor name label
		lblDoctorName = new JLabel("Doctor Name:");
		lblDoctorName.setBounds(100, 50, 100, 25);
		frame.getContentPane().add(lblDoctorName);

		// Create and add Doctor name text field
		txtDoctorName = new JTextField();
		txtDoctorName.setBounds(200, 50, 200, 25);
		frame.getContentPane().add(txtDoctorName);

		// Create and add Specialization label
		lblSpecialization = new JLabel("Specialization:");
		lblSpecialization.setBounds(100, 80, 100, 25);
		frame.getContentPane().add(lblSpecialization);

		// Create and add Specialization text field
		txtSpecialization = new JTextField();
		txtSpecialization.setBounds(200, 80, 200, 25);
		frame.getContentPane().add(txtSpecialization);

		// Create and add floor label
		lblFloor = new JLabel("Floor:");
		lblFloor.setBounds(100, 110, 100, 25);
		frame.getContentPane().add(lblFloor);

		// Create and add floor text field
		txtFloor = new JTextField();
		txtFloor.setBounds(200, 110, 200, 25);
		frame.getContentPane().add(txtFloor);

		// Create and add add Doctor button
		btnAddDoctor = new JButton("Add Doctor");
		btnAddDoctor.setBounds(200, 140, 200, 25);
		btnAddDoctor.addActionListener(this);
		frame.getContentPane().add(btnAddDoctor);

		// Set login screen visible
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		List<Doctor> doctors = Utility.readDoctors();
		
		String doctorID = txtDoctorID.getText();
		String name = txtDoctorName.getText();
		String specialization = txtSpecialization.getText();
		String floor = txtFloor.getText();

		if (doctorID.isEmpty()) {// If Doctor ID is not given, display error and return
			JOptionPane.showMessageDialog(null, "Please enter Doctor ID!");
			txtDoctorID.requestFocus();// Set focus on Doctor ID
			return;
		}

		if (name.isEmpty()) {// If name is not given, display error and return
			JOptionPane.showMessageDialog(null, "Please enter name!");
			txtDoctorName.requestFocus();// Set focus on name
			return;
		}
		
		if (specialization.isEmpty()) {// If specialization is not given, display error and return
			JOptionPane.showMessageDialog(null, "Please enter specialization!");
			txtSpecialization.requestFocus();// Set focus on specialization
			return;
		}
		if (floor.isEmpty()) {// If floor is not given, display error and return
			JOptionPane.showMessageDialog(null, "Please enter floor!");
			txtFloor.requestFocus();// Set focus on illness
			return;
		}
		int floorNo = Integer.parseInt(floor);
		
		String password = Utility.generatePassword();
//		Doctor(int id, String name, String password, String specialization, int floor, int status, int firstLogin) 
		Doctor doctor = new Doctor(
				Integer.parseInt(doctorID), 
				name, 
				password,
				specialization, 
				floorNo,
				1,
				1);
		doctors.add(doctor);
		Utility.writeDoctors(doctors);
		JOptionPane.showMessageDialog(null, "Doctor successfully added!");
		frame.dispose();
	}

}
