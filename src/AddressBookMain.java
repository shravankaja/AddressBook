import java.util.Scanner;
import java.util.*;

public class AddressBookMain {
	public static int NO_OF_CONTACTS = 0;

	public static void main(String Args[]) {
		Scanner sc = new Scanner(System.in);
		AddressBook contact[] = new AddressBook[10];

		System.out.println("Weclome to Address Book \n");
		while (true) {

			System.out.println("Please select an option \n 1.Add \n 2.Edit \n 3.Delete \n 4. Delete ");

			int option = sc.nextInt();
			switch (option) {
			case 1:
				System.out.println("Enter Number of Contacts \n");
				NO_OF_CONTACTS = sc.nextInt();

				for (int i = 0; i < NO_OF_CONTACTS; i++) {
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

					contact[i] = new AddressBook(last_Name, first_Name, address, city, state, phone_Number, zip_Code,
							email);
				}
				break;
			case 2:

				System.out.println("Enter Name of the contact you want to edit \n");
				String last_Name_Edit = sc.next();
				for (int j = 0; j < NO_OF_CONTACTS; j++) {
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

						contact[j] = new AddressBook(last_Name, first_Name, address, city, state, phone_Number,
								zip_Code, email);

					}
				}
				break;
			case 3:
				System.out.println("Enter Name of the contact you want to delete \n");
				String last_Name_Delete = sc.next();
				AddressBook contact_Final[] = new AddressBook[10];
				for (int k = 0, s = 0; k < NO_OF_CONTACTS; k++) {
					boolean found_Not_Found = contact[k].LAST_NAME.equals(last_Name_Delete);

					if (found_Not_Found == true) {
						continue;
					}
					contact_Final[s++] = contact[k];

				}
				try {
					for (int k = 0; k < NO_OF_CONTACTS; k++) {
						contact_Final[k].display();
					}
				} catch (NullPointerException e) {
					System.out.println("End of Contacts");
				}
				break;
			case 4:
				for (int k = 0; k < NO_OF_CONTACTS; k++) {
					contact[k].display();
				}
				break;

			}
		}
	}
}
