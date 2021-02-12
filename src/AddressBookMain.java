import java.util.Scanner;

public class AddressBookMain {

	public static void main(String Args[]) {
		System.out.println("Weclome to Address Book");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Last Name");
		String last_Name = sc.nextLine();
		System.out.println("Enter first Name");
		String first_Name = sc.nextLine();
		System.out.println("Enter Address Lane");
		String address = sc.nextLine();
		System.out.println("Enter City");
		String city = sc.nextLine();
		System.out.println("Enter state");
		String state = sc.nextLine();
		System.out.println("Enter Email");
		String email = sc.nextLine();
		System.out.println("Enter Phone Number");
		Double phone_Number = sc.nextDouble();
		System.out.println("Enter Zip Name");
		Double zip_Code = sc.nextDouble();
		AddressBook contact_One = new AddressBook(last_Name, first_Name, address, city, state, phone_Number, zip_Code,
				email);
		contact_One.display();

	}
}
