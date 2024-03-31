package his;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class DoctorScreen extends JFrame implements ActionListener {

	private JFrame doctorScreen;// Main Doctor Screen Frame
	private JMenuBar menubar;

	private JMenu fileMenu;// File menu
	private JMenuItem exitMenuItem;// Exit menu item

	JMenu doctorMenu;// Doctor menu
	JMenuItem changeDoctorMenuItem;// Change Doctor Data menu item

	JMenu floorMenu;// Floor menu
	JMenuItem checkFloorMenuItem;// Check Floor menu item

	JMenu patientMenu;// Floor menu
	JMenuItem checkPatientMenuItem;
	JLabel lblWelcome;// JLabel to display welcome message

	JButton btnChangeData;// JButton to change Personal Data
	JButton btnCheckPatientData;// JButton to check Patient data
	JButton btnCheckFloorData;// JButton to check Floor data
	int doctorID;

	public DoctorScreen(int doctorID) {
		this.doctorID = doctorID;

		doctorScreen = new JFrame("Hospital Information System - Doctor");
		doctorScreen.setSize(520, 500);// if you do not set size you will have to resize when it runs
		doctorScreen.setLocationRelativeTo(null);
		doctorScreen.getContentPane().setLayout(null);
		doctorScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		doctorScreen.setResizable(false);

		// Create and add menu bar
		menubar = new JMenuBar();
		menubar.setBounds(0, 0, 520, 20);

		// Create and add File menu
		fileMenu = new JMenu("File");
		menubar.add(fileMenu);

		// Create and add doctor menu
		doctorMenu = new JMenu("Doctor");
		menubar.add(doctorMenu);

		// Create and add change data menu item
		changeDoctorMenuItem = new JMenuItem("Change Personal Data");
		changeDoctorMenuItem.addActionListener(this);
		doctorMenu.add(changeDoctorMenuItem);

		// Create and add Patient menu
		patientMenu = new JMenu("Patient");
		menubar.add(doctorMenu);

		// Create and add patient item
		checkPatientMenuItem = new JMenuItem("Check Patient' Data");
		checkPatientMenuItem.addActionListener(this);
		doctorMenu.add(checkPatientMenuItem);

		// Create and add Floor menu
		floorMenu = new JMenu("Floor");
		menubar.add(floorMenu);

		// Create and add patient item
		checkFloorMenuItem = new JMenuItem("Check Floor' Data");
		checkFloorMenuItem.addActionListener(this);
		floorMenu.add(checkFloorMenuItem);

		// Create and display Welcome Label
		lblWelcome = new JLabel("Welcome Doctor!!");
		lblWelcome.setBounds(0, 20, 520, 35);
		lblWelcome.setHorizontalAlignment(JLabel.CENTER);
		lblWelcome.setFont(new Font("Arial", Font.BOLD, 18));
		doctorScreen.getContentPane().add(lblWelcome);

		// Create and add change personal data button
		btnChangeData = new JButton("Change Personal Data");
		btnChangeData.setBounds(100, 70, 300, 35);
		btnChangeData.addActionListener(this);
		doctorScreen.getContentPane().add(btnChangeData);

		// Create and add patient button
		btnCheckPatientData = new JButton("Check Patient's Data");
		btnCheckPatientData.setBounds(100, 120, 300, 35);
		btnCheckPatientData.addActionListener(this);
		doctorScreen.getContentPane().add(btnCheckPatientData);

		// Create and add vital sign button
		btnCheckFloorData = new JButton("Check Floor's Data");
		btnCheckFloorData.setBounds(100, 170, 300, 35);
		btnCheckFloorData.addActionListener(this);
		doctorScreen.getContentPane().add(btnCheckFloorData);

		doctorScreen.getContentPane().add(menubar);
		doctorScreen.setVisible(true);
		loadDoctor(doctorID);
	}

	public Doctor loadDoctor(int doctorID) {
		List<Doctor> doctors = Utility.readDoctors();
		Doctor doctor = doctors.stream().filter(doc -> doc.getId() == doctorID).findFirst().orElse(null);
		if (doctor.getFirstLogin() == 1) {
			String password = JOptionPane.showInputDialog("Change Password?");
			if (!password.isEmpty())
				doctor.setPassword(password);
			doctor.setFirstLogin(0);
			Utility.writeDoctors(doctors);
		}
		return doctor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitMenuItem) {
			System.exit(0);
		} else if (e.getSource() == changeDoctorMenuItem || e.getSource() == btnChangeData) {
			new ChangeDoctorData(doctorID);
		} else if (e.getSource() == checkPatientMenuItem || e.getSource() == btnCheckPatientData) {
			new CheckPatientData();
		} else if (e.getSource() == checkFloorMenuItem || e.getSource() == btnCheckFloorData) {
			new FloorData();
		}

	}

//	public static void main(String args[]) {
//		new DoctorScreen(1234);
//	}
}
