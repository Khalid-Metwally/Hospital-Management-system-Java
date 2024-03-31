package his;
/**
 * This class holds the patient data
 * 
 *
 */
public class Patient {

	private int id;
	private String name;
	private String illness;
	private int floor;

	public Patient(int id, String name, String illness, int floor) {
		super();
		this.id = id;
		this.name = name;
		this.illness = illness;
		this.floor = floor;
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

	public String getIllness() {
		return illness;
	}

	public void setIllness(String illness) {
		this.illness = illness;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	@Override // tells the compile to expect that child class will override base class
	public String toString() {
		return "Name: " + this.name + ", ID: " + this.id + "Illness" + this.illness;

	}
}
