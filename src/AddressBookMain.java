import java.util.Scanner;

public class AddressBookMain {

	public static void main(String Args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Weclome to Address Book \n");
		System.out.println("Enter Number of Contacts \n");
		int no_of_Contacts = sc.nextInt();
		AddressBook contact[] = new AddressBook[no_of_Contacts];
		for (int i = 0; i < no_of_Contacts; i++) {
			System.out.println("Enter first Name \n");
			String first_Name = sc.next();
			System.out.println("Enter Address Lane \n");
			String address = sc.next();
			System.out.println("Enter City \n");
			String city = sc.next();
			System.out.println("Enter state \n");
			String state = sc.next();
			System.out.println("Enter Email \n");
			String email = sc.next();
			System.out.println("Enter Phone Number \n");
			Double phone_Number = sc.nextDouble();
			System.out.println("Enter Zip Name \n");
			Double zip_Code = sc.nextDouble();
			System.out.println("Enter Last Name \n");
			String last_Name = sc.next();

			contact[i] = new AddressBook(last_Name, first_Name, address, city, state, phone_Number, zip_Code, email);

		}
		System.out.println("Enter Name of the contact you want to edit \n");
		String last_Name_Edit = sc.next();
		for (int j = 0; j < no_of_Contacts; j++) {
			boolean found_Not_Found = contact[j].LAST_NAME.equals(last_Name_Edit);
			if (found_Not_Found == true) {
				System.out.println("Enter new details \n");
				System.out.println("Enter first Name \n");
				String first_Name = sc.next();
				System.out.println("Enter Address Lane \n");
				String address = sc.next();
				System.out.println("Enter City \n");
				String city = sc.next();
				System.out.println("Enter state \n");
				String state = sc.next();
				System.out.println("Enter Email \n");
				String email = sc.next();
				System.out.println("Enter Phone Number \n");
				Double phone_Number = sc.nextDouble();
				System.out.println("Enter Zip Name \n");
				Double zip_Code = sc.nextDouble();
				System.out.println("Enter Last Name \n");
				String last_Name = sc.next();

				contact[j] = new AddressBook(last_Name, first_Name, address, city, state, phone_Number, zip_Code,
						email);

				break;
			}
			System.out.println("Name not found \n");
		}

	}

}
