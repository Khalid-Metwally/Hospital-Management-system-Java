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
 * This class creates the GUI for doctor to check the floor data
 * 
 * 
 *
 */
public class FloorData extends JFrame implements ActionListener {

	private JFrame frame;// FloorData frame
	int floorNo = -1;
	JTextField minLowBP;
	JTextField maxLowBP;
	JTextField avgLowBP;
	JTextField minHighBP;
	JTextField maxHighBP;
	JTextField avgHighBP;
	JTextField minBreathing;
	JTextField maxBreathing;
	JTextField avgBreathing;
	JTextField minPulseRate;
	JTextField maxPulseRate;
	JTextField avgPulseRate;
	JTextField minTemperature;
	JTextField maxTemperature;
	JTextField avgTemperature;

	JTextField txtFloorNo;
	JButton btnCheckFloorData;
	DefaultTableModel tableModel;
	JPanel panel;
	JScrollPane scrollPane;
	List<Patient> patients = Utility.readPatients();
	List<VitalSign> vitalSigns = Utility.readVitalSigns();
	JTable table;

	int totalPatients = 0;

	int minLowBPValue = Integer.MAX_VALUE;
	int maxLowBPValue = 0;
	int avgLowBPValue = 0;
	int sumLowBPValue = 0;

	int minHighBPValue = Integer.MAX_VALUE;
	int maxHighBPValue = 0;
	int avgHighBPValue = 0;
	int sumHighBPValue = 0;

	int minBreathingValue = Integer.MAX_VALUE;
	int maxBreathingValue = 0;
	int avgBreathingValue = 0;
	int sumBreathingValue = 0;

	int minPulseValue = Integer.MAX_VALUE;
	int maxPulseValue = 0;
	int avgPulseValue = 0;
	int sumPulseValue = 0;

	double minTempValue = Double.MAX_VALUE;
	double maxTempValue = 0;
	double avgTempValue = 0;
	double sumTempValue = 0;

	public FloorData() {
		frame = new JFrame("Hospital Information System - Floor Data");
		frame.setSize(550, 500);// if you do not set size you will have to resize when it runs
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);

		JLabel lblFloorNo = new JLabel("Enter Floor No:");
		lblFloorNo.setBounds(50, 20, 100, 25);
		frame.getContentPane().add(lblFloorNo);

		txtFloorNo = new JTextField();
		txtFloorNo.setBounds(150, 20, 200, 25);
		frame.getContentPane().add(txtFloorNo);

		btnCheckFloorData = new JButton("Check Data");
		btnCheckFloorData.setBounds(360, 20, 100, 25);
		btnCheckFloorData.addActionListener(this);

		loadFloorData();

		frame.getContentPane().add(btnCheckFloorData);
		frame.setVisible(true);
	}

	public void setFloorData() {
		totalPatients = 0;

		minLowBPValue = Integer.MAX_VALUE;
		maxLowBPValue = 0;
		avgLowBPValue = 0;
		sumLowBPValue = 0;

		minHighBPValue = Integer.MAX_VALUE;
		maxHighBPValue = 0;
		avgHighBPValue = 0;
		sumHighBPValue = 0;

		minBreathingValue = Integer.MAX_VALUE;
		maxBreathingValue = 0;
		avgBreathingValue = 0;
		sumBreathingValue = 0;

		minPulseValue = Integer.MAX_VALUE;
		maxPulseValue = 0;
		avgPulseValue = 0;
		sumPulseValue = 0;

		minTempValue = Double.MAX_VALUE;
		maxTempValue = 0;
		avgTempValue = 0;
		sumTempValue = 0;

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
			if (patient.getFloor() == floorNo) {
				tableModel.addRow(new Object[] { vitalsing.getPatientID(), patient.getName(),
						vitalsing.getLowBloodPressure(), vitalsing.getHighBloodPressure(), vitalsing.getBreathing(),
						vitalsing.getPulseRate(), vitalsing.getTemperature() });
				totalPatients++;
				sumLowBPValue += vitalsing.getLowBloodPressure();
				sumHighBPValue += vitalsing.getHighBloodPressure();
				sumBreathingValue += vitalsing.getBreathing();
				sumPulseValue += vitalsing.getPulseRate();
				sumTempValue += vitalsing.getTemperature();

				if (vitalsing.getLowBloodPressure() < minLowBPValue) {
					minLowBPValue = vitalsing.getLowBloodPressure();
				}

				if (vitalsing.getHighBloodPressure() < minHighBPValue) {
					minHighBPValue = vitalsing.getHighBloodPressure();
				}

				if (vitalsing.getBreathing() < minBreathingValue) {
					minBreathingValue = vitalsing.getBreathing();
				}

				if (vitalsing.getPulseRate() < minPulseValue) {
					minPulseValue = vitalsing.getPulseRate();
				}

				if (vitalsing.getTemperature() < minTempValue) {
					minTempValue = vitalsing.getTemperature();
				}

				if (vitalsing.getLowBloodPressure() > maxLowBPValue) {
					maxLowBPValue = vitalsing.getLowBloodPressure();
				}

				if (vitalsing.getHighBloodPressure() > maxHighBPValue) {
					maxHighBPValue = vitalsing.getHighBloodPressure();
				}

				if (vitalsing.getBreathing() > maxBreathingValue) {
					maxBreathingValue = vitalsing.getBreathing();
				}

				if (vitalsing.getPulseRate() > maxPulseValue) {
					maxPulseValue = vitalsing.getPulseRate();
				}

				if (vitalsing.getTemperature() > maxTempValue) {
					maxTempValue = vitalsing.getTemperature();
				}
			}
		});

		table.setModel(tableModel);

		if (totalPatients > 0) {
			avgLowBPValue = sumLowBPValue / totalPatients;
			avgHighBPValue = sumHighBPValue / totalPatients;
			avgBreathingValue = sumBreathingValue / totalPatients;
			avgPulseValue = sumPulseValue / totalPatients;
			avgTempValue = sumTempValue / totalPatients;

			minLowBP.setText("" + minLowBPValue);
			maxLowBP.setText("" + maxLowBPValue);
			avgLowBP.setText("" + avgLowBPValue);
			minHighBP.setText("" + minHighBPValue);
			maxHighBP.setText("" + maxHighBPValue);
			avgHighBP.setText("" + avgHighBPValue);
			minBreathing.setText("" + minBreathingValue);
			maxBreathing.setText("" + maxBreathingValue);
			avgBreathing.setText("" + avgBreathingValue);
			minPulseRate.setText("" + minPulseValue);
			maxPulseRate.setText("" + maxPulseValue);
			avgPulseRate.setText("" + avgPulseValue);
			minTemperature.setText("" + minTempValue);
			maxTemperature.setText("" + maxTempValue);
			avgTemperature.setText("" + avgTempValue);
		}else {
			minLowBP.setText("");
			maxLowBP.setText("");
			avgLowBP.setText("");
			minHighBP.setText("");
			maxHighBP.setText("");
			avgHighBP.setText("");
			minBreathing.setText("");
			maxBreathing.setText("");
			avgBreathing.setText("");
			minPulseRate.setText("");
			maxPulseRate.setText("");
			avgPulseRate.setText("");
			minTemperature.setText("");
			maxTemperature.setText("");
			avgTemperature.setText("");
		}

	}

	public void loadFloorData() {

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

		JLabel min = new JLabel("Min");
		min.setFont(new Font("Arial", Font.BOLD, 16));
		min.setHorizontalAlignment(JLabel.CENTER);
		min.setBounds(150, 220, 100, 25);
		frame.getContentPane().add(min);

		JLabel max = new JLabel("Max");
		max.setFont(new Font("Arial", Font.BOLD, 16));
		max.setHorizontalAlignment(JLabel.CENTER);
		max.setBounds(250, 220, 100, 25);
		frame.getContentPane().add(max);

		JLabel average = new JLabel("Average");
		average.setFont(new Font("Arial", Font.BOLD, 16));
		average.setHorizontalAlignment(JLabel.CENTER);
		average.setBounds(350, 220, 100, 25);
		frame.getContentPane().add(average);

		JLabel lowBP = new JLabel("Low BP:");
		lowBP.setFont(new Font("Arial", Font.BOLD, 14));
		lowBP.setBounds(20, 250, 100, 25);
		frame.getContentPane().add(lowBP);

		minLowBP = new JTextField("");
		minLowBP.setBounds(150, 250, 90, 25);
		minLowBP.setEditable(false);
		frame.getContentPane().add(minLowBP);

		maxLowBP = new JTextField("");
		maxLowBP.setBounds(250, 250, 90, 25);
		maxLowBP.setEditable(false);
		frame.getContentPane().add(maxLowBP);

		avgLowBP = new JTextField("");
		avgLowBP.setBounds(350, 250, 90, 25);
		avgLowBP.setEditable(false);
		frame.getContentPane().add(avgLowBP);

		JLabel highBP = new JLabel("High BP:");
		highBP.setFont(new Font("Arial", Font.BOLD, 14));
		highBP.setBounds(20, 280, 100, 25);
		frame.getContentPane().add(highBP);

		minHighBP = new JTextField("");
		minHighBP.setBounds(150, 280, 90, 25);
		minHighBP.setEditable(false);
		frame.getContentPane().add(minHighBP);

		maxHighBP = new JTextField("");
		maxHighBP.setBounds(250, 280, 90, 25);
		maxHighBP.setEditable(false);
		frame.getContentPane().add(maxHighBP);

		avgHighBP = new JTextField("");
		avgHighBP.setBounds(350, 280, 90, 25);
		avgHighBP.setEditable(false);
		frame.getContentPane().add(avgHighBP);

		JLabel breathing = new JLabel("Breathing:");
		breathing.setFont(new Font("Arial", Font.BOLD, 14));
		breathing.setBounds(20, 310, 100, 25);
		frame.getContentPane().add(breathing);

		minBreathing = new JTextField("");
		minBreathing.setBounds(150, 310, 90, 25);
		minBreathing.setEditable(false);
		frame.getContentPane().add(minBreathing);

		maxBreathing = new JTextField("");
		maxBreathing.setBounds(250, 310, 90, 25);
		maxBreathing.setEditable(false);
		frame.getContentPane().add(maxBreathing);

		avgBreathing = new JTextField("");
		avgBreathing.setBounds(350, 310, 90, 25);
		avgBreathing.setEditable(false);
		frame.getContentPane().add(avgBreathing);

		JLabel pulseRate = new JLabel("Pulse Rate:");
		pulseRate.setFont(new Font("Arial", Font.BOLD, 14));
		pulseRate.setBounds(20, 340, 100, 25);
		frame.getContentPane().add(pulseRate);

		minPulseRate = new JTextField("");
		minPulseRate.setBounds(150, 340, 90, 25);
		minPulseRate.setEditable(false);
		frame.getContentPane().add(minPulseRate);

		maxPulseRate = new JTextField("");
		maxPulseRate.setBounds(250, 340, 90, 25);
		maxPulseRate.setEditable(false);
		frame.getContentPane().add(maxPulseRate);

		avgPulseRate = new JTextField("");
		avgPulseRate.setBounds(350, 340, 90, 25);
		avgPulseRate.setEditable(false);
		frame.getContentPane().add(avgPulseRate);

		JLabel temperature = new JLabel("Temperature:");
		temperature.setFont(new Font("Arial", Font.BOLD, 14));
		temperature.setBounds(20, 370, 100, 25);
		frame.getContentPane().add(temperature);

		minTemperature = new JTextField("");
		minTemperature.setBounds(150, 370, 90, 25);
		minTemperature.setEditable(false);
		frame.getContentPane().add(minTemperature);

		maxTemperature = new JTextField("");
		maxTemperature.setBounds(250, 370, 90, 25);
		maxTemperature.setEditable(false);
		frame.getContentPane().add(maxTemperature);

		avgTemperature = new JTextField("");
		avgTemperature.setBounds(350, 370, 90, 25);
		avgTemperature.setEditable(false);
		frame.getContentPane().add(avgTemperature);

		frame.getContentPane().add(panel);
		frame.repaint();
		frame.validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCheckFloorData) {
			String strFloorNo = txtFloorNo.getText();
			if (strFloorNo.isEmpty()) {// If floorNo is not given, display error and return
				JOptionPane.showMessageDialog(null, "Please enter floorNo!");
				txtFloorNo.requestFocus();// Set focus on floorNo
				return;
			}
			floorNo = Integer.parseInt(strFloorNo);
			setFloorData();
		}

	}

}
