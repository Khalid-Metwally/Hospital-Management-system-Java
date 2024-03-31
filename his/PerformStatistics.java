package his;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * This class creates the GUI for performing statistics
 * 
 * 
 *
 */
public class PerformStatistics extends JFrame implements ActionListener {

	private JFrame frame;// PerformStatistics frame
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

	public PerformStatistics() {
		frame = new JFrame("Hospital Information System - Statistics");
		frame.setSize(550, 500);// if you do not set size you will have to resize when it runs
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);

		DefaultTableModel tableModel;
		JPanel panel;
		JScrollPane scrollPane;
		List<Patient> patients = Utility.readPatients();
		List<VitalSign> vitalSigns = Utility.readVitalSigns();

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

		JTable table = new JTable(tableModel);
		table.getTableHeader().setBackground(Color.LIGHT_GRAY);
		table.setBackground(Color.LIGHT_GRAY);
		scrollPane = new JScrollPane(table);

		scrollPane.setPreferredSize(new Dimension(450, 200));

		panel.add(scrollPane, BorderLayout.CENTER);
		panel.add(table.getTableHeader(), BorderLayout.NORTH);// if u don't add this, column names wont show

		vitalSigns.forEach(vitalsing -> {
			Patient patient = patients.stream().filter(pt -> pt.getId() == vitalsing.getPatientID()).findFirst()
					.orElse(null);
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
		});

		avgLowBPValue = sumLowBPValue / totalPatients;
		avgHighBPValue = sumHighBPValue / totalPatients;
		avgBreathingValue = sumBreathingValue / totalPatients;
		avgPulseValue = sumPulseValue / totalPatients;
		avgTempValue = sumTempValue / totalPatients;
		panel.add(table);
		panel.setBounds(10, 10, 520, 200);

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

		JTextField minLowBP = new JTextField("" + minLowBPValue);
		minLowBP.setBounds(150, 250, 90, 25);
		minLowBP.setEditable(false);
		frame.getContentPane().add(minLowBP);

		JTextField maxLowBP = new JTextField("" + maxLowBPValue);
		maxLowBP.setBounds(250, 250, 90, 25);
		maxLowBP.setEditable(false);
		frame.getContentPane().add(maxLowBP);

		JTextField avgLowBP = new JTextField("" + avgLowBPValue);
		avgLowBP.setBounds(350, 250, 90, 25);
		avgLowBP.setEditable(false);
		frame.getContentPane().add(avgLowBP);

		JLabel highBP = new JLabel("High BP:");
		highBP.setFont(new Font("Arial", Font.BOLD, 14));
		highBP.setBounds(20, 280, 100, 25);
		frame.getContentPane().add(highBP);

		JTextField minHighBP = new JTextField("" + minHighBPValue);
		minHighBP.setBounds(150, 280, 90, 25);
		minHighBP.setEditable(false);
		frame.getContentPane().add(minHighBP);

		JTextField maxHighBP = new JTextField("" + maxHighBPValue);
		maxHighBP.setBounds(250, 280, 90, 25);
		maxHighBP.setEditable(false);
		frame.getContentPane().add(maxHighBP);

		JTextField avgHighBP = new JTextField("" + avgHighBPValue);
		avgHighBP.setBounds(350, 280, 90, 25);
		avgHighBP.setEditable(false);
		frame.getContentPane().add(avgHighBP);

		JLabel breathing = new JLabel("Breathing:");
		breathing.setFont(new Font("Arial", Font.BOLD, 14));
		breathing.setBounds(20, 310, 100, 25);
		frame.getContentPane().add(breathing);

		JTextField minBreathing = new JTextField("" + minBreathingValue);
		minBreathing.setBounds(150, 310, 90, 25);
		minBreathing.setEditable(false);
		frame.getContentPane().add(minBreathing);

		JTextField maxBreathing = new JTextField("" + maxBreathingValue);
		maxBreathing.setBounds(250, 310, 90, 25);
		maxBreathing.setEditable(false);
		frame.getContentPane().add(maxBreathing);

		JTextField avgBreathing = new JTextField("" + avgBreathingValue);
		avgBreathing.setBounds(350, 310, 90, 25);
		avgBreathing.setEditable(false);
		frame.getContentPane().add(avgBreathing);

		JLabel pulseRate = new JLabel("Pulse Rate:");
		pulseRate.setFont(new Font("Arial", Font.BOLD, 14));
		pulseRate.setBounds(20, 340, 100, 25);
		frame.getContentPane().add(pulseRate);

		JTextField minPulseRate = new JTextField("" + minPulseValue);
		minPulseRate.setBounds(150, 340, 90, 25);
		minPulseRate.setEditable(false);
		frame.getContentPane().add(minPulseRate);

		JTextField maxPulseRate = new JTextField("" + maxPulseValue);
		maxPulseRate.setBounds(250, 340, 90, 25);
		maxPulseRate.setEditable(false);
		frame.getContentPane().add(maxPulseRate);

		JTextField avgPulseRate = new JTextField("" + avgPulseValue);
		avgPulseRate.setBounds(350, 340, 90, 25);
		avgPulseRate.setEditable(false);
		frame.getContentPane().add(avgPulseRate);

		JLabel temperature = new JLabel("Temperature:");
		temperature.setFont(new Font("Arial", Font.BOLD, 14));
		temperature.setBounds(20, 370, 100, 25);
		frame.getContentPane().add(temperature);

		JTextField minTemperature = new JTextField("" + minTempValue);
		minTemperature.setBounds(150, 370, 90, 25);
		minTemperature.setEditable(false);
		frame.getContentPane().add(minTemperature);

		JTextField maxTemperature = new JTextField("" + maxTempValue);
		maxTemperature.setBounds(250, 370, 90, 25);
		maxTemperature.setEditable(false);
		frame.getContentPane().add(maxTemperature);

		JTextField avgTemperature = new JTextField("" + avgTempValue);
		avgTemperature.setBounds(350, 370, 90, 25);
		avgTemperature.setEditable(false);
		frame.getContentPane().add(avgTemperature);

		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
