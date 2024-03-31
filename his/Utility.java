package his;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class performs all the read and write operations
 * 
 * 
 *
 */
public class Utility {

	/**
	 * This method reads and loads staffs from the file
	 * 
	 * @return
	 */
	public static List<Staff> readStaffs() {
		List<Staff> staffs = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("staffs.txt"))) {
			String line = br.readLine();// we want to skip reading first line
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				Staff staff = new Staff(Integer.parseInt(parts[0]), // id
						parts[1], // name
						parts[2], // password
						Integer.parseInt(parts[3]));// status
				staffs.add(staff);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return staffs;
	}

	/**
	 * This method writes staffs data to file
	 * 
	 * @param staffs
	 */
	public static void writeStaffs(List<Staff> staffs) {
		try {
			PrintWriter outStream = new PrintWriter(new FileOutputStream("staffs.txt"));
			outStream.print("Staff ID,UserName,Password,Status\n");
			for (Staff staff : staffs) {
				outStream.print(staff.getId() + ",");
				outStream.print(staff.getUserName() + ",");
				outStream.print(staff.getPassword() + ",");
				outStream.print(staff.getStatus() + "\n");
			}
			outStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * This method reads and loads doctors from the file
	 * 
	 * @return
	 */
	public static List<Doctor> readDoctors() {
		List<Doctor> doctors = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("doctors.txt"))) {
			String line = br.readLine();// we want to skip reading first line
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				Doctor doctor = new Doctor(Integer.parseInt(parts[0]), // id
						parts[1], // name
						parts[2], // password
						parts[3], // specialization
						Integer.parseInt(parts[4]), // floor
						Integer.parseInt(parts[5]), // status
						Integer.parseInt(parts[6]));// firstlogin
				doctors.add(doctor);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return doctors;
	}

	/**
	 * This method write doctors to files
	 * 
	 * @param doctors
	 */
	public static void writeDoctors(List<Doctor> doctors) {
		try {
			PrintWriter outStream = new PrintWriter(new FileOutputStream("doctors.txt"));
			outStream.print("Doctor ID, Name, Password,Specialization,Floor,Status,FirstLogin\n");
			for (Doctor doctor : doctors) {
				outStream.print(doctor.getId() + ",");
				outStream.print(doctor.getName() + ",");
				outStream.print(doctor.getPassword() + ",");
				outStream.print(doctor.getSpecialization() + ",");
				outStream.print(doctor.getFloor() + ",");
				outStream.print(doctor.getStatus() + ",");
				outStream.print(doctor.getFirstLogin() + "\n");
			}
			outStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * This method reads and loads Specializations from the file
	 * 
	 * @return
	 */
	public static List<Specialization> readSpecializations() {
		List<Specialization> specializations = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("specializations.txt"))) {
			String line = br.readLine();// we want to skip reading first line
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				Specialization specialization = new Specialization(parts[0], // specialization
						Integer.parseInt(parts[1]), // floor
						Integer.parseInt(parts[2])); // capacity
				specializations.add(specialization);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return specializations;
	}

	/**
	 * This method writes Specializations data to file
	 * @param staffs
	 */
	public static void writeSpecializations(List<Specialization> specializations) {
		try {
			PrintWriter outStream = new PrintWriter(new FileOutputStream("specializations.txt"));
			outStream.print("Specialization,FloorNo,Capacity\n");
			for (Specialization specialization : specializations) {
				outStream.print(specialization.getSpecialization() + ",");
				outStream.print(specialization.getFloorNo() + ",");
				outStream.print(specialization.getCapacity() + "\n");
			}
			outStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * This method reads and loads patients from the file
	 * 
	 * @return
	 */
	public static List<Patient> readPatients() {
		List<Patient> patients = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("patients.txt"))) {
			String line = br.readLine();// we want to skip reading first line
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				Patient patient = new Patient(
						Integer.parseInt(parts[0]), // patient id
						parts[1], // name
						parts[2],//illness
						Integer.parseInt(parts[3])); // floor
				patients.add(patient);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return patients;
	}
	
	/**
	 * This method writes patients data to file
	 * @param staffs
	 */
	public static void writePatients(List<Patient> patients) {
		try {
			PrintWriter outStream = new PrintWriter(new FileOutputStream("patients.txt"));
			outStream.print("Patient ID,Patient Name, Illness,Floor\n");
			for (Patient patient : patients) {
				outStream.print(patient.getId() + ",");
				outStream.print(patient.getName() + ",");
				outStream.print(patient.getIllness() + ",");
				outStream.print(patient.getFloor() + "\n");
			}
			outStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * This method reads and loads vital signs from the file
	 * 
	 * @return
	 */
	public static List<VitalSign> readVitalSigns() {
		List<VitalSign> vitalSigns = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("vitalSigns.txt"))) {
			String line = br.readLine();// we want to skip reading first line
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				VitalSign vitalSign = new VitalSign(
						Integer.parseInt(parts[0]), // patient id
						Integer.parseInt(parts[1]), // low bp
						Integer.parseInt(parts[2]), // high bp
						Integer.parseInt(parts[3]), // breathing
						Integer.parseInt(parts[4]), // pulse rate
						Double.parseDouble(parts[5])); // temperature
				vitalSigns.add(vitalSign);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return vitalSigns;
	}
	
	/**
	 * This method writes vital signs data to file
	 * @param staffs
	 */
	public static void writeVitalSigns(List<VitalSign> vitalSigns) {
		try {
			PrintWriter outStream = new PrintWriter(new FileOutputStream("vitalSigns.txt"));
			outStream.print("PatientID, Low Blood Pressure, High Blood Pressure, Breathing, Pulse Rate, Temperature\n");
			for (VitalSign vitalSign : vitalSigns) {
				outStream.print(vitalSign.getPatientID() + ",");
				outStream.print(vitalSign.getLowBloodPressure() + ",");
				outStream.print(vitalSign.getHighBloodPressure() + ",");
				outStream.print(vitalSign.getBreathing() + ",");
				outStream.print(vitalSign.getPulseRate() + ",");
				outStream.print(vitalSign.getTemperature() + "\n");
			}
			outStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * This method generates a 10 digit password
	 * @return
	 */
	public static String generatePassword() {
		String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    int PASSWORD_LENGTH = 10;
        Random random = new Random();
        StringBuilder sb = new StringBuilder(PASSWORD_LENGTH);
        
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        
        return sb.toString();
    }
}
