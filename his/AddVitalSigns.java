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
import javax.swing.JTextField;
import javax.swing.text.Utilities;

/**
 * This class provides GUI for adding vital signs
 * 
 * 
 *
 */
public class AddVitalSigns extends JFrame implements ActionListener {

	private JFrame frame;// Add vital sign frame

	private JLabel lblPatientID;// Label for Patient ID
	private JComboBox<String> cmbPatients = new JComboBox<>(/* new String[] { "All Patients" } */);// Combobox/dropdown
																									// list for
	// patients

	private JLabel lblLowBP;// Label for Low BP Range
	private JTextField txtLowBP;// Text field Low BP Range

	private JLabel lblHighBP;// Label for High BP Range
	private JTextField txtHighBP;// Text field High BP Range

	private JLabel lblBreathing;// Label for Breathing
	private JTextField txtBreathing;// Text field for Breathing

	private JLabel lblPulseRate;// Label for PulseRate
	private JTextField txtPulseRate;// Text field for PulseRate

	private JLabel lblTemperature;// Label for Temperature
	private JTextField txtTemperature;// Text field for Temperature

	private JButton btnAddVitalSigns;// Button to perform add VitalSigns operation

	List<Specialization> specializations = Utility.readSpecializations();
	List<Patient> patients = Utility.readPatients();

	public AddVitalSigns() {
		frame = new JFrame("Hospital Information System - Add Vital Sign");
		frame.setSize(550, 300);// if you do not set size you will have to resize when it runs
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);

		// Create and add patient id label
		lblPatientID = new JLabel("Select Patient:");
		lblPatientID.setBounds(100, 20, 100, 25);
		frame.getContentPane().add(lblPatientID);

		// Add Combobox

		specializations.forEach(
				specialization -> cmbPatients.addItem("Floor " + specialization.getFloorNo() + " - All Patients"));

		patients.forEach(patient -> cmbPatients.addItem(patient.getName()));
		cmbPatients.setBounds(200, 20, 200, 25);
		frame.getContentPane().add(cmbPatients);

		// Create and add Low BP label
		lblLowBP = new JLabel("Low BP:");
		lblLowBP.setBounds(100, 50, 100, 25);
		frame.getContentPane().add(lblLowBP);

		// Create and add Low BP text field
		txtLowBP = new JTextField();
		txtLowBP.setBounds(200, 50, 200, 25);
		frame.getContentPane().add(txtLowBP);

		// Create and add High BP label
		lblHighBP = new JLabel("High BP:");
		lblHighBP.setBounds(100, 80, 100, 25);
		frame.getContentPane().add(lblHighBP);

		// Create and add High BP text field
		txtHighBP = new JTextField();
		txtHighBP.setBounds(200, 80, 200, 25);
		frame.getContentPane().add(txtHighBP);

		// Create and add Breathing label
		lblBreathing = new JLabel("Breathing:");
		lblBreathing.setBounds(100, 110, 100, 25);
		frame.getContentPane().add(lblBreathing);

		// Create and add Breathing text field
		txtBreathing = new JTextField();
		txtBreathing.setBounds(200, 110, 200, 25);
		frame.getContentPane().add(txtBreathing);

		// Create and add PulseRate label
		lblPulseRate = new JLabel("Pulse Rate:");
		lblPulseRate.setBounds(100, 140, 100, 25);
		frame.getContentPane().add(lblPulseRate);

		// Create and add PulseRate text field
		txtPulseRate = new JTextField();
		txtPulseRate.setBounds(200, 140, 200, 25);
		frame.getContentPane().add(txtPulseRate);

		// Create and add Temperature label
		lblTemperature = new JLabel("Temperature:");
		lblTemperature.setBounds(100, 170, 100, 25);
		frame.getContentPane().add(lblTemperature);

		// Create and add Temperature text field
		txtTemperature = new JTextField();
		txtTemperature.setBounds(200, 170, 200, 25);
		frame.getContentPane().add(txtTemperature);

		// Create and add add vital signs button
		btnAddVitalSigns = new JButton("Add Vital Signs");
		btnAddVitalSigns.setBounds(200, 200, 200, 25);
		btnAddVitalSigns.addActionListener(this);
		frame.getContentPane().add(btnAddVitalSigns);

		// Set login screen visible
		frame.setVisible(true);
	}

	/**
	 * This function performs action on button click
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		List<VitalSign> vitalSigns = Utility.readVitalSigns();

		String lowBP = txtLowBP.getText();
		String highBP = txtHighBP.getText();
		String breathing = txtBreathing.getText();
		String pulseRate = txtPulseRate.getText();
		String temperature = txtTemperature.getText();

		if (lowBP.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter Low BP!");
			txtLowBP.requestFocus();
			return;
		}

		if (highBP.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter High BP!");
			txtHighBP.requestFocus();
			return;
		}

		if (breathing.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter Breathing!");
			txtBreathing.requestFocus();
			return;
		}

		if (pulseRate.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter Pulse Rate!");
			txtPulseRate.requestFocus();
			return;
		}

		if (temperature.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter Temperature!");
			txtTemperature.requestFocus();
			return;
		}

		int lowBPInt = Integer.parseInt(lowBP);
		int highBPInt = Integer.parseInt(highBP);
		int breathingInt = Integer.parseInt(breathing);
		int pulseRateInt = Integer.parseInt(pulseRate);
		double temperatureDbl = Double.parseDouble(temperature);

		if (lowBPInt < 60 || lowBPInt > 80) {
			JOptionPane.showMessageDialog(null, "Low BP is outside normal range (60 to 80)!");
		}

		if (highBPInt < 90 || highBPInt > 120) {
			JOptionPane.showMessageDialog(null, "High BP is outside normal range (90 to 120)!");
		}

		if (breathingInt < 12 || breathingInt > 18) {
			JOptionPane.showMessageDialog(null, "Breathing is outside normal range (12 to 18)!");
		}

		if (pulseRateInt < 60 || pulseRateInt > 100) {
			JOptionPane.showMessageDialog(null, "Pulse Rate is outside normal range (60 to 100)!");
		}

		if (temperatureDbl < 97.8 || temperatureDbl > 99.1) {
			JOptionPane.showMessageDialog(null, "Temperatur is outside normal range (97.8 to 99.1)!");
		}

		String selectedPatient = "" + cmbPatients.getSelectedItem();

		if (selectedPatient.startsWith("Floor")) {// Add these vital signs to all patients
			int floorNo = Integer.parseInt(selectedPatient.substring(5, selectedPatient.indexOf("-")).trim());
			patients.forEach(pt -> {
				if (pt.getFloor() == floorNo) {
					VitalSign vitalSign = new VitalSign(pt.getId(), lowBPInt, highBPInt, breathingInt, pulseRateInt,
							temperatureDbl);
					vitalSigns.add(vitalSign);
				}
			});

		} else {// Add vital sign for the specific patient only
			Patient patient = patients.stream().filter(pt -> pt.getName().equals(selectedPatient)).findFirst()
					.orElse(null);
			VitalSign vitalSign = new VitalSign(patient.getId(), lowBPInt, highBPInt, breathingInt, pulseRateInt,
					temperatureDbl);
			vitalSigns.add(vitalSign);
		}
		
		Utility.writeVitalSigns(vitalSigns);
		JOptionPane.showMessageDialog(null, "Vital Signs successfully added!");
		frame.dispose();

	}

}
