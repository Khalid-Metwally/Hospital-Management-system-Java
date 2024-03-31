package his;

/**
 * This class holds the vital signs data Vital Signs (Body Temperature, Pulse
 * Rate, Respiration Rate, Blood Pressure)
 * 
 * 
 *
 */
public class VitalSign {

	private int patientID;
	private int lowBloodPressure;// Normal range: 60 to 80
	private int highBloodPressure;// Normal range: 90 to 120
	private int breathing;// Normal range: 12 to 18 per minute
	private int pulseRate;// Normal range: 60 to 100 beats per minute
	private double temperature;// Normal range: 97.8°F to 99.1°F

	public VitalSign(int patientID, int lowBloodPressure, int highBloodPressure, int breathing, int pulseRate,
			double temperature) {
		super();
		this.patientID = patientID;
		this.lowBloodPressure = lowBloodPressure;
		this.highBloodPressure = highBloodPressure;
		this.breathing = breathing;
		this.pulseRate = pulseRate;
		this.temperature = temperature;
	}

	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public int getLowBloodPressure() {
		return lowBloodPressure;
	}

	public void setLowBloodPressure(int lowBloodPressure) {
		this.lowBloodPressure = lowBloodPressure;
	}

	public int getHighBloodPressure() {
		return highBloodPressure;
	}

	public void setHighBloodPressure(int highBloodPressure) {
		this.highBloodPressure = highBloodPressure;
	}

	public int getBreathing() {
		return breathing;
	}

	public void setBreathing(int breathing) {
		this.breathing = breathing;
	}

	public int getPulseRate() {
		return pulseRate;
	}

	public void setPulseRate(int pulseRate) {
		this.pulseRate = pulseRate;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

}
