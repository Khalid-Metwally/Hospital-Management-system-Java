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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

/**
 * This is the main class for Hospital Information System It will provide login
 * screen to staff/doctor
 * 
 * 
 *
 */

public class LoginScreen extends JFrame implements ActionListener {
	private JFrame loginScreen;// Main Login Scren Frame

	private JLabel lblUserName;// Label for user name
	private JTextField txtUserName;// Text field for user name

	private JLabel lblPassword;// Label for password
	private JPasswordField txtPassword;// TextField for password;

	private JLabel lblUserType;// Label for user type. i.e. Staff or Doctor
	private JComboBox cmbUserType;// Combobox/dropdown list for user type (Staff or Doctor)

	private JButton btnLogin;// Button to perform login operation
	private String userType[] = { "Staff", "Doctor" };// Combobox/dropdown list values

	private int invalidAttempts = 0;

	public LoginScreen() {

		loginScreen = new JFrame("Hospital Information System - Login");
		loginScreen.setSize(550, 300);// if you do not set size you will have to resize when it runs
		loginScreen.setLocationRelativeTo(null);
		loginScreen.getContentPane().setLayout(null);
		loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginScreen.setResizable(false);

		// Create and add user name label to login screen
		lblUserName = new JLabel("User Name:");
		lblUserName.setBounds(100, 50, 100, 25);
		loginScreen.getContentPane().add(lblUserName);

		// Create and add user name text field to login screen
		txtUserName = new JTextField();
		txtUserName.setBounds(200, 50, 200, 25);
		loginScreen.getContentPane().add(txtUserName);

		// Create and add password label to login screen
		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(100, 80, 100, 25);
		loginScreen.getContentPane().add(lblPassword);

		// Create and add password text field to login screen
		txtPassword = new JPasswordField();
		txtPassword.setBounds(200, 80, 200, 25);
		loginScreen.getContentPane().add(txtPassword);

		// Create and add user type label to login screen
		lblUserType = new JLabel("User Type:");
		lblUserType.setBounds(100, 110, 100, 25);
		loginScreen.getContentPane().add(lblUserType);

		// Create and add user type combobox/dropdown to login screen
		cmbUserType = new JComboBox(userType);
		cmbUserType.setBounds(200, 110, 200, 25);
		loginScreen.getContentPane().add(cmbUserType);

		// Create and add login button to login screen
		btnLogin = new JButton("Login");
		btnLogin.setBounds(200, 140, 200, 25);
		btnLogin.addActionListener(this);
		loginScreen.getContentPane().add(btnLogin);

		// Set login screen visible
		loginScreen.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnLogin) {// Login Button is pressed
			String userName = txtUserName.getText();
			String password = txtPassword.getText();

			if (userName.isEmpty()) {// If user name is not given, display error and return
				JOptionPane.showMessageDialog(null, "Please enter user name!");
				txtUserName.requestFocus();// Set focus on user name
				return;
			}

			if (password.isEmpty()) {// If user name is not given, display error and return
				JOptionPane.showMessageDialog(null, "Please enter password!");
				txtPassword.requestFocus();// Set focus on password
				return;
			}

			if (cmbUserType.getSelectedItem().equals("Staff")) {// Check Staff Login
				List<Staff> staffs = Utility.readStaffs();
				Staff staff = staffs.stream().filter(stf -> stf.getUserName().equals(userName)).findFirst()
						.orElse(null);
				if (staff == null) {
					JOptionPane.showMessageDialog(null, "Staff '" + userName + "' not found!");
					return;
				} else {
					if (staff.getStatus() == 0) {// user is locked
						JOptionPane.showMessageDialog(null, "Staff '" + userName + "' is locked!");
						return;
					} else if (staff.getPassword().equals(password) && staff.getStatus() == 1) {// Successful Login
						invalidAttempts = 0;
						txtUserName.setText("");
						txtPassword.setText("");
						SwingWorker sw1 = new SwingWorker() {
							@Override
				            protected String doInBackground()
				                throws Exception
				            {
								new StaffScreen();
								String res = "Finished Execution";
				                return res;
				            }
						};
						sw1.execute();
						
						this.setVisible(false);
					} else {// Failed attempt
						invalidAttempts++;
						JOptionPane.showMessageDialog(null,
								"Invalid password provided for '" + userName + "'. Try Again!",
								"Invalid Attempt:" + invalidAttempts, JOptionPane.ERROR_MESSAGE);
						txtPassword.requestFocus();// Set focus on password
						// Lock the account after the third wrong attempt to login
						if (invalidAttempts == 3) {
							invalidAttempts = 0;
							staff.setStatus(0);// lock staff account
							Utility.writeStaffs(staffs);
						}
						return;
					}
				}
			} else {// Check Doctor Login
				List<Doctor> doctors = Utility.readDoctors();
				Doctor doctor = doctors.stream().filter(doc -> doc.getName().equals(userName)).findFirst()
						.orElse(null);
				if (doctor == null) {
					JOptionPane.showMessageDialog(null, "Doctor '" + userName + "' not found!");
					return;
				} else {
					if (doctor.getStatus() == 0) {// user is locked
						JOptionPane.showMessageDialog(null, "Doctor '" + userName + "' is locked!");
						return;
					} else if (doctor.getPassword().equals(password) && doctor.getStatus() == 1) {// Successful Login
						invalidAttempts = 0;
						txtUserName.setText("");
						txtPassword.setText("");
						SwingWorker sw1 = new SwingWorker() {
							@Override
				            protected String doInBackground()
				                throws Exception
				            {
								new DoctorScreen(doctor.getId());
								String res = "Finished Execution";
				                return res;
				            }
						};
						sw1.execute();
						
					} else {// Failed attempt
						invalidAttempts++;
						JOptionPane.showMessageDialog(null,
								"Invalid password provided for '" + userName + "'. Try Again!",
								"Invalid Attempt:" + invalidAttempts, JOptionPane.ERROR_MESSAGE);
						txtPassword.requestFocus();// Set focus on password
						// Lock the account after the third wrong attempt to login
						if (invalidAttempts == 3) {
							invalidAttempts = 0;
							doctor.setStatus(0);// lock Doctor account
							Utility.writeDoctors(doctors);
						}
						return;
					}
				}
			}
		}

	}

	
	
	public static void main(String[] args) {
		new LoginScreen();
	}
}
