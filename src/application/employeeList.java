package application;

public class employeeList {

	private int id;
	private String firstname;
	private String lastname;
	private String gender;
	private String yoe;
	
	
	public employeeList() {

	}

	public employeeList(int id, String firstname, String lastname, String gender, String yoe) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.yoe = yoe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getYoe() {
		return yoe;
	}

	public void setYoe(String yoe) {
		this.yoe = yoe;
	}	
	
}
