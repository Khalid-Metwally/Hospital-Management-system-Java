package his;

/**
 * This class represent the Doctor object
 * 
 * 
 *
 */
public class Doctor {

	private int id;// ID of the doctor
	private String name;// Name of the doctor
	private String password;// Password of the doctor
	private String specialization;// Specialization of the doctor
	private int floor;// Floor of the doctor
	// Status of the doctor. 1 for active, 0 for inactive ( Lock the account after
	// the third wrong attempt to login)
	private int status;
	private int firstLogin;// 1 for first login. 0 for not. Show password on first login

	public Doctor(int id, String name, String password, String specialization, int floor, int status, int firstLogin) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.specialization = specialization;
		this.floor = floor;
		this.status = status;
		this.firstLogin = firstLogin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(int firstLogin) {
		this.firstLogin = firstLogin;
	}

}
