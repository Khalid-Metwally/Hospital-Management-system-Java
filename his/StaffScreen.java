package his;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
/**
 * This class create GUI for Staff to perform different operations like add patient, add doctor, assign specialization.
 * 
 *
 */
public class StaffScreen extends JFrame implements ActionListener {

	private JFrame staffScreen;// Main Staff Screen Frame
	private JMenuBar menubar;

	private JMenu fileMenu;// File menu
	private JMenuItem exitMenuItem;// Exit menu item

	private JMenu specializationMenu;// Specialization menu
	private JMenuItem specializationMenuItem;// Specialization menu item

	private JMenu patientMenu;// Patient menu
	private JMenuItem addPatientMenuItem;// Add Patient menu item

	private JMenu vitalSignMenu;// Vital Sign menu
	private JMenuItem addVitalSignMenuItem;// Add Vital Sign menu item

	private JMenu statisticsMenu;// Statistics menu
	private JMenuItem statisticsMenuItem;// Statistics menu item

	private JMenu doctorMenu;// Doctor menu
	private JMenuItem addDoctorMenuItem;// Add Doctor menu item

	private JLabel lblWelcome;// JLabel to display welcome message

	private JButton btnAddSpecialization;// JButton to add specialization
	private JButton btnAddPatient;// JButton to add patient
	private JButton btnAddVitalSign;// JButton to add vital sign
	private JButton btnPerformStatistics;// JButton to perform statistics
	private JButton btnAddDoctor;// JButton to add doctor

	public StaffScreen() {

		staffScreen = new JFrame("Hospital Information System - Staff");
		staffScreen.setSize(520, 500);// if you do not set size you will have to resize when it runs
		staffScreen.setLocationRelativeTo(null);
		staffScreen.getContentPane().setLayout(null);
		staffScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		staffScreen.setResizable(false);
		// Create and add menu bar
		menubar = new JMenuBar();
		menubar.setBounds(0, 0, 520, 20);

		// Create and add File menu
		fileMenu = new JMenu("File");
		menubar.add(fileMenu);

		// Create and add Exit menu item
		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(this);
		fileMenu.add(exitMenuItem);

		// Create and add Specialization menu
		specializationMenu = new JMenu("Specialization");
		menubar.add(specializationMenu);

		// Create and add Sepcialization menu item
		specializationMenuItem = new JMenuItem("Add Specialization");
		specializationMenuItem.addActionListener(this);
		specializationMenu.add(specializationMenuItem);

		// Create and add Patient menu
		patientMenu = new JMenu("Patient");
		menubar.add(patientMenu);

		// Create and add patient item
		addPatientMenuItem = new JMenuItem("Add Patient");
		addPatientMenuItem.addActionListener(this);
		patientMenu.add(addPatientMenuItem);

		// Create and add Vital Sign menu
		vitalSignMenu = new JMenu("Vital Sign");
		menubar.add(vitalSignMenu);

		// Create and add Vital Sign menu item
		addVitalSignMenuItem = new JMenuItem("Add Vital Sign");
		addVitalSignMenuItem.addActionListener(this);
		vitalSignMenu.add(addVitalSignMenuItem);

		// Create and add Statistics menu
		statisticsMenu = new JMenu("Statistics");
		menubar.add(statisticsMenu);

		// Create and add Statistics menu item
		statisticsMenuItem = new JMenuItem("Perform Statistics");
		statisticsMenuItem.addActionListener(this);
		statisticsMenu.add(statisticsMenuItem);

		// Create and add Doctor menu
		doctorMenu = new JMenu("Doctor");
		menubar.add(doctorMenu);

		// Create and add Doctor menu item
		addDoctorMenuItem = new JMenuItem("Add Doctor");
		addDoctorMenuItem.addActionListener(this);
		doctorMenu.add(addDoctorMenuItem);

		// Create and display Welcome Label
		lblWelcome = new JLabel("Welcome Staff!!");
		lblWelcome.setBounds(0, 20, 520, 35);
		lblWelcome.setHorizontalAlignment(JLabel.CENTER);
		lblWelcome.setFont(new Font("Arial", Font.BOLD, 18));
		staffScreen.getContentPane().add(lblWelcome);

		// Create and add specialization button
		btnAddSpecialization = new JButton("Add Specialization");
		btnAddSpecialization.setBounds(100, 70, 300, 35);
		btnAddSpecialization.addActionListener(this);
		staffScreen.getContentPane().add(btnAddSpecialization);

		// Create and add patient button
		btnAddPatient = new JButton("Add Patient");
		btnAddPatient.setBounds(100, 120, 300, 35);
		btnAddPatient.addActionListener(this);
		staffScreen.getContentPane().add(btnAddPatient);

		// Create and add vital sign button
		btnAddVitalSign = new JButton("Add Vital Sign");
		btnAddVitalSign.setBounds(100, 170, 300, 35);
		btnAddVitalSign.addActionListener(this);
		staffScreen.getContentPane().add(btnAddVitalSign);

		// Create and add perform statistics button
		btnPerformStatistics = new JButton("Perform Statistics");
		btnPerformStatistics.setBounds(100, 220, 300, 35);
		btnPerformStatistics.addActionListener(this);
		staffScreen.getContentPane().add(btnPerformStatistics);

		// Create and add doctor button
		btnAddDoctor = new JButton("Add Doctor");
		btnAddDoctor.setBounds(100, 270, 300, 35);
		btnAddDoctor.addActionListener(this);
		staffScreen.getContentPane().add(btnAddDoctor);

		staffScreen.getContentPane().add(menubar);
		staffScreen.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitMenuItem) {
			System.exit(0);
		}else if(e.getSource() == specializationMenuItem || e.getSource() == btnAddSpecialization) {
			new AddSpecialization();
		}else if(e.getSource() == addPatientMenuItem || e.getSource() == btnAddPatient) {
			new AddPatient();
		}else if(e.getSource() == addVitalSignMenuItem || e.getSource() == btnAddVitalSign) {
			new AddVitalSigns();
		}else if(e.getSource() == statisticsMenuItem || e.getSource() == btnPerformStatistics) {
			new PerformStatistics();
		}else if(e.getSource() == addDoctorMenuItem || e.getSource() == btnAddDoctor) {
			new AddDoctor();
		}

	}

//	public static void main(String args[]) {
//		new StaffScreen();
//	}

}
