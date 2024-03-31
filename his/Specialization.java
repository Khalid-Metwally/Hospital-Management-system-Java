package his;
/**
 * This class holds the specialization data
 * 
 *
 */
public class Specialization {

	private String specialization;
	private int floorNo;
	private int capacity;

	public Specialization(String specialization, int floorNo, int capacity) {
		super();
		this.specialization = specialization;
		this.floorNo = floorNo;
		this.capacity = capacity;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public int getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(int floorNo) {
		this.floorNo = floorNo;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

}
