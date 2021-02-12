
public class AddressBook {
	public String FIRST_NAME;
	public String LAST_NAME;
	public String ADDRESS;
	public String CITY;
	public String STATE;
	public double ZIP_CODE;
	public double PHONE_NUMBER;
	public String EMAIL;

	public AddressBook(String last_Name, String first_Name, String address, String city, String state,
			double phone_Number, double zip_Code, String email) {
		this.FIRST_NAME = first_Name;
		this.LAST_NAME = last_Name;
		this.ADDRESS = address;
		this.CITY = city;
		this.STATE = state;
		this.ZIP_CODE = zip_Code;
		this.PHONE_NUMBER = phone_Number;
		this.EMAIL = email;

	}

	public void display() {
		System.out.println("First Name :" + FIRST_NAME);
		System.out.println(" Last Name :" + LAST_NAME);
		System.out.println("ADDRESS :" + ADDRESS);
		System.out.println("CITY :" + CITY);
		System.out.println("STATE :" + STATE);
		System.out.println("ZIP :" + ZIP_CODE);
		System.out.println("PHONE :" + PHONE_NUMBER);
		System.out.println("EMAIL  :" + EMAIL);
	}

}
