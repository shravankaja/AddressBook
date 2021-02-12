
public class AddressBook {
	public static String FIRST_NAME;
	public static String LAST_NAME;
	public static String ADDRESS;
	public static String CITY;
	public static String STATE;
	public static double ZIP_CODE;
	public static double PHONE_NUMBER;
	public static String EMAIL;

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

	void display() {
		System.out.println("Name you have added :" + FIRST_NAME);
	}
}
