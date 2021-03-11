import java.io.*;
import java.util.*;

public class AddressBookMain {
    public static void main(String Args[]) throws IOException {
        AddressBook addressBook = new AddressBook();
        ArrayList<AddressBook> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(
                    "Enter your option \n 1.Add \n 2.Edit \n 3.Delete \n 4.Display \n 5.Add Address Boook \n 6. Search Contact by State or City\n "
                            + "7. display city records 8. Display state records\n 9.City Contacts count \n 10. state contacts count1 \n " +
                            "11. Sort address boook alpabetically \n 12. Sort using Zip or State or City");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    addressBook.add();
                    break;
                case 2:
                    addressBook.edit();
                    break;
                case 3:
                    addressBook.delete();
                    break;
                case 4:
                    addressBook.display();
                    break;
                case 5:
                    addressBook.add_Address_Book();
                    break;
                case 6:
                    addressBook.searchContactByStateOrCity();
                    break;
                case 7:
                    addressBook.displayCity();
                    break;
                case 8:
                    addressBook.displayState();
                    break;
                case 9:
                    addressBook.getCountCity();
                    break;
                case 10:
                    addressBook.getCountState();
                    break;
                case 11:
                    addressBook.sortAlphabetically();
                    break;
                case 12:
                    addressBook.sortUsingCityStateOrPin();
                    break;
                case 0:
                    break;
            }
        }
    }
}