package his;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * This class creates the GUI for doctor to check the patient data
 * 
 * 
 *
 */
public class CheckPatientData extends JFrame implements ActionListener {

	private JFrame frame;// FloorData frame
	int patientID = -1;

	JTextField txtPatientID;
	JButton btnCheckPatientData;
	DefaultTableModel tableModel;
	JPanel panel;
	JScrollPane scrollPane;
	List<Patient> patients = Utility.readPatients();
	List<VitalSign> vitalSigns = Utility.readVitalSigns();
	JTable table;
	public CheckPatientData() {
		frame = new JFrame("Hospital Information System - Floor Data");
		frame.setSize(550, 500);// if you do not set size you will have to resize when it runs
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);

		JLabel lblFloorNo = new JLabel("Enter Patient ID:");
		lblFloorNo.setBounds(50, 20, 100, 25);
		frame.getContentPane().add(lblFloorNo);

		txtPatientID = new JTextField();
		txtPatientID.setBounds(150, 20, 200, 25);
		frame.getContentPane().add(txtPatientID);

		btnCheckPatientData = new JButton("Check Data");
		btnCheckPatientData.setBounds(360, 20, 100, 25);
		btnCheckPatientData.addActionListener(this);

		loadPatientData();

		frame.getContentPane().add(btnCheckPatientData);
		frame.setVisible(true);
	}

	public void setPatientData() {

		tableModel = new DefaultTableModel();
		tableModel.addColumn("Patient ID");
		tableModel.addColumn("Patient Name");
		tableModel.addColumn("Low BP");
		tableModel.addColumn("High BP");
		tableModel.addColumn("Breathing");
		tableModel.addColumn("Pulse Rate");
		tableModel.addColumn("Temperature");

		vitalSigns.forEach(vitalsing -> {
			Patient patient = patients.stream().filter(pt -> pt.getId() == vitalsing.getPatientID()).findFirst()
					.orElse(null);
			if (patient.getId() == patientID) {
				tableModel.addRow(new Object[] { vitalsing.getPatientID(), patient.getName(),
						vitalsing.getLowBloodPressure(), vitalsing.getHighBloodPressure(), vitalsing.getBreathing(),
						vitalsing.getPulseRate(), vitalsing.getTemperature() });
			}
		});

		table.setModel(tableModel);

	}

	public void loadPatientData() {

		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		tableModel = new DefaultTableModel();

		tableModel.addColumn("Patient ID");
		tableModel.addColumn("Patient Name");
		tableModel.addColumn("Low BP");
		tableModel.addColumn("High BP");
		tableModel.addColumn("Breathing");
		tableModel.addColumn("Pulse Rate");
		tableModel.addColumn("Temperature");

		table = new JTable(tableModel);
		table.getTableHeader().setBackground(Color.LIGHT_GRAY);
		table.setBackground(Color.LIGHT_GRAY);
		scrollPane = new JScrollPane(table);

		scrollPane.setPreferredSize(new Dimension(450, 200));

		panel.add(scrollPane, BorderLayout.CENTER);
		panel.add(table.getTableHeader(), BorderLayout.NORTH);// if u don't add this, column names wont show

		panel.add(table);
		panel.setBounds(10, 50, 520, 100);

		frame.getContentPane().add(panel);
		frame.repaint();
		frame.validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCheckPatientData) {
			String strPatient = txtPatientID.getText();
			if (strPatient.isEmpty()) {// If floorNo is not given, display error and return
				JOptionPane.showMessageDialog(null, "Please enter Patient ID!");
				txtPatientID.requestFocus();// Set focus on floorNo
				return;
			}
			patientID = Integer.parseInt(strPatient);
			setPatientData();
		}

	}

}
