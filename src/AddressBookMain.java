import java.util.Scanner;

public class AddressBookMain {
	public static void main(String Args[]) {
		AddressBook m = new AddressBook();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println(
					"Enter your option \n 1.Add \n 2.Edit \n 3.Delete \n 4.Display \n 5.Add Address Boook \n 6. Search Contact by State or City\n "
							+ "7. display city records 8. Display state records\n 9.City Contacts count \n 10. state contacts count");
			int option = sc.nextInt();
			switch (option) {
			case 1:
				m.add();
				break;
			case 2:
				m.edit();
				break;
			case 3:
				m.delete();
				break;
			case 4:
				m.display();
				break;
			case 5:
				m.add_Address_Book();
				break;
			case 6:
				m.searchContactByStateOrCity();
			case 7:
				m.displayCity();
			case 8:
				m.displayState();
			case 9:
				m.getCountCity();
			case 10:
				m.getCountState();
			case 0:
				break;
			}
		}
	}
}