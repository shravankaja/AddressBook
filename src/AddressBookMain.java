import java.util.Scanner;

public class AddressBookMain {
	public static void main(String Args[]) {
		AddressBook m = new AddressBook();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out
					.println("Enter your option \n 1.Add \n 2.Edit \n 3.Delete \n 4.Display \n 5.Add Address Boook \n");
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

			case 0:
				break;
			}
		}
	}
}