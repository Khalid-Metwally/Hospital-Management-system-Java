package his;

/**
 * This class represent the Staff object
 * 
 * 
 *
 */
public class Staff {

	private int id;// ID of the staff
	private String userName;// Username of the staff
	private String password;// Password of the staff
	// Status of the staff. 1 for active, 0 for inactive ( Lock the account after
	// the third wrong attempt to login)
	private int status;

	public Staff(int id, String userName, String password, int status) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
