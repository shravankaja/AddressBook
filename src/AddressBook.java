import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class AddressBook {
    public String FIRST_NAME;
    public String LAST_NAME;
    public String ADDRESS;
    public String CITY;
    public String STATE;
    public double ZIP_CODE;
    public double PHONE_NUMBER;
    public String EMAIL;
    public int NO_OF_CONTACTS;
    public String Address_Book_Name;
    public int duplicateNameCheck;
    public String firstName;
    public String cityName;
    public String stateName;
    public int cityContactsCount = 0;
    public int stateContactsCount = 0;
    Map<String, List<AddressBook>> mapper = new HashMap<>();
    Map<String, List<AddressBook>> cityContactMap = new HashMap<>();
    Map<String, List<AddressBook>> stateContactMap = new HashMap<>();
    ArrayList<AddressBook> address_Book_No = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    public int contacts_No;
    boolean found_Not_Found;
    public String firstNameCheck;
    int flag;
    int numberOfDupllicate = 0;


    public AddressBook() {
        // TODO Auto-generated constructor stub
    }

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

    public String getFirstName() {
        return FIRST_NAME;
    }

    public String getCityName() {
        return CITY;
    }

    public String getStateName() {
        return STATE;

    }

    public double getZIP_CODE() {
        return ZIP_CODE;
    }

    public String toString() {
        return "First Name :" + FIRST_NAME + " Last Name :" + LAST_NAME + " Address :" + ADDRESS + "  City :" + CITY +
                " State : " + STATE + " Zip_Code :" + ZIP_CODE + " Phone : " + PHONE_NUMBER + " Email :" + EMAIL;
    }

    public void searchContactByStateOrCity() {
        System.out.println("Enter city ");
        String cityName = sc.next();
        System.out.println("Enter person first name");
        String firstName = sc.next();
        List<Object> listObjects = new ArrayList<>();
        listObjects = cityContactMap.entrySet().stream().filter(e -> e.getKey().equals(cityName)).flatMap(e -> e.getValue().stream())
                .filter(e -> e.getFirstName().equals(firstName)).collect(Collectors.toList());
        System.out.println(listObjects);
        int num = listObjects.size();
        if (num == 0) {
            System.out.println("No records");
        }

    }

    public boolean duplicateEntriesCheck(String firstName) {
        List<Object> dpNameCheck = new ArrayList<>();
        dpNameCheck = mapper.entrySet().stream().map(e -> e.getValue().stream()).flatMap(e ->
                e.filter(s -> s.getFirstName().equals(firstName))).collect(Collectors.toList());
        int num = dpNameCheck.size();
        if (num > 0) {
            return true;
        }
        return false;
    }

    public void add_Address_Book() {
        System.out.println("Enter Address Book Name \n");
        String Address_Book_Name_test = sc.next();
        try {
            for (String s : mapper.keySet()) {
                System.out.println("Keys " + s);
                if (s.equals(Address_Book_Name_test)) {
                    System.out.println("Already exsists \n");
                } else {
                    Address_Book_Name = Address_Book_Name_test;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("No address books yet");
        }
    }

    public void add() throws IOException {

        System.out.println("Enter Address Book Name \n");
        Address_Book_Name = sc.next();
        System.out.println("Enter Number of Contacts \n");
        NO_OF_CONTACTS = sc.nextInt();
        for (int i = 0; i < NO_OF_CONTACTS; i++) {
            System.out.println("Enter first Name  \n");
            String first_Name = sc.next();
            if (duplicateEntriesCheck(first_Name)) {
                System.out.println("Name already taken");
                break;
            }
            System.out.println("Enter Address Lane \n");
            String address = sc.next();
            System.out.println("Enter City \n");
            String city = sc.next();
            cityName = city;
            System.out.println("Enter state \n");
            String state = sc.next();
            stateName = state;
            System.out.println("Enter Email \n");
            String email = sc.next();
            System.out.println("Enter Phone Number \n");
            Double phone_Number = sc.nextDouble();
            System.out.println("Enter Zip Name \n");
            Double zip_Code = sc.nextDouble();
            System.out.println("Enter Last Name \n");
            String last_Name = sc.next();
            mapper.computeIfAbsent(Address_Book_Name, Address_Book_Name -> new ArrayList<>())
                    .add(new AddressBook(last_Name, first_Name, address, cityName, stateName, phone_Number, zip_Code, email));

            AddressBook fileObject = new AddressBook(last_Name, first_Name, address, cityName, stateName, phone_Number, zip_Code,
                    email);
            writeToFile(Address_Book_Name, fileObject);

            cityContactMap.computeIfAbsent(cityName, cityName -> new ArrayList<>())
                    .add(new AddressBook(last_Name, first_Name, address, cityName, stateName, phone_Number, zip_Code, email));
            writeToFile(cityName, new AddressBook(last_Name, first_Name, address, cityName, stateName, phone_Number, zip_Code, email));
            stateContactMap.computeIfAbsent(stateName, stateName -> new ArrayList<>())
                    .add(new AddressBook(last_Name, first_Name, address, cityName, stateName, phone_Number, zip_Code, email));
            writeToFile(stateName, new AddressBook(last_Name, first_Name, address, cityName, stateName, phone_Number
                    , zip_Code, email));
        }
    }

    public void getCountCity() {
        System.out.println("Enter  City name  \n");
        String cityNameFilter = sc.next();
        List<Object> objectList = new ArrayList<>();
        objectList = cityContactMap.entrySet().stream().filter(e -> e.getKey().equals(cityNameFilter)).flatMap(e -> e.getValue().stream()).collect(Collectors.toList());
        int num = objectList.size();
        System.out.println("Size " + num);
    }

    public void getCountState() {
        System.out.println("Enter  City name  \n");
        String stateNameFilter = sc.next();
        List<Object> objectList = new ArrayList<>();
        objectList = stateContactMap.entrySet().stream().filter(e -> e.getKey().equals(stateNameFilter)).flatMap(e -> e.getValue().stream()).collect(Collectors.toList());
        int num = objectList.size();
        System.out.println("Size " + num);
    }

    public void display() throws FileNotFoundException {
        System.out.println("Enter address book name \n");
        String address_Book = sc.next();
        Path addressBookDirectory = Paths.get("E:\\Eclipse_Practise\\AddressBook\\DataFiles");
        File fileObject = addressBookDirectory.toFile();
        File[] listOfFiles = fileObject.listFiles();
        if (Files.isDirectory(addressBookDirectory)) {
            String newFile = addressBookDirectory + "/" + address_Book + ".txt";
            Path newFilePath = Paths.get(newFile);
            if (Files.exists(newFilePath)) {
                File myObj = new File(String.valueOf(newFilePath));
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                }
                myReader.close();
            }
        }
    }

    public void displayCity() {
        System.out.println("Enter  City name  \n");
        String cityNameFilter = sc.next();
        List<Object> objectList = new ArrayList<>();
        objectList = stateContactMap.entrySet().stream().filter(e -> e.getKey().equals(cityNameFilter)).flatMap(e -> e.getValue().stream()).collect(Collectors.toList());
        System.out.println("List of city objects :" + objectList.toString());
    }


    public void displayState() {
        System.out.println("Enter state name  \n");
        String stateNameFilter = sc.next();
        List<Object> objectList = stateContactMap.entrySet().stream().filter(e -> e.getKey().equals(stateNameFilter)).collect(Collectors.toList());
        System.out.println("List of city objects :" + objectList.toString());
    }

    public void display1() {
        System.out.println("First Name :" + FIRST_NAME);
        System.out.println("Last Name :" + LAST_NAME);
        System.out.println("ADDRESS :" + ADDRESS);
        System.out.println("CITY :" + CITY);
        System.out.println("STATE :" + STATE);
        System.out.println("ZIP :" + ZIP_CODE);
        System.out.println("PHONE :" + PHONE_NUMBER);
        System.out.println("EMAIL  :" + EMAIL);
    }

    public void edit() {
        System.out.println("Enter Address Book Name \n");
        String Address_Book_Name_test = sc.next();
        System.out.println("Enter Name of the contact you want to edit \n");
        String last_Name_Edit = sc.next();
        for (String s : mapper.keySet()) {
            if (s.equals(Address_Book_Name_test)) {
                System.out.println("Current key: " + s);
                for (AddressBook r : mapper.get(s)) {
                    found_Not_Found = r.LAST_NAME.equals(last_Name_Edit);
                    if (found_Not_Found == true) {
                        System.out.println(
                                "Enter which you want edit 1.first_name 2.last_name 3. address 4. city 5.state 6. email 7. phone number  8. zip ");
                        int option = sc.nextInt();
                        System.out.println("Enter new details \n");
                        switch (option) {
                            case 1:
                                System.out.println("Enter first Name \n");
                                String first_Name = sc.next();

                                r.FIRST_NAME = first_Name;
                                break;
                            case 3:
                                System.out.println("Enter Address Lane \n");
                                String address = sc.next();
                                r.ADDRESS = address;
                                break;
                            case 4:
                                System.out.println("Enter City \n");
                                String city = sc.next();
                                r.CITY = city;
                                break;
                            case 5:
                                System.out.println("Enter state \n");
                                String state = sc.next();
                                r.STATE = state;
                                break;
                            case 6:
                                System.out.println("Enter Email \n");
                                String email = sc.next();
                                r.EMAIL = email;
                                break;
                            case 7:
                                System.out.println("Enter Phone Number \n");
                                Double phone_Number = sc.nextDouble();
                                r.PHONE_NUMBER = phone_Number;
                                break;
                            case 8:
                                System.out.println("Enter Zip Name \n");
                                Double zip_Code = sc.nextDouble();
                                r.ZIP_CODE = zip_Code;
                                break;
                            case 2:
                                System.out.println("Enter Last Name \n");
                                String last_Name = sc.next();
                                r.LAST_NAME = last_Name;
                                break;
                            default:
                                System.out.println("Invalid option");
                                break;
                        }
                    }
                }
            } else {
                System.out.println("Address book not fond");
            }
        }
    }


    public void delete() {
        System.out.println("Enter Address Book Name \n");
        String Address_Book_Name_test = sc.next();
        System.out.println("Enter Name of the contact you want to edit \n");
        String last_Name_Edit = sc.next();
        for (Map.Entry<String, List<AddressBook>> entry : mapper.entrySet()) {
            String key = entry.getKey();
            if (key.equals(Address_Book_Name_test)) {
                List<AddressBook> list = entry.getValue();
                for (int i = 0; i < list.size(); i++) {
                    AddressBook we = list.get(i);
                    found_Not_Found = we.LAST_NAME.equals(last_Name_Edit);
                    System.out.println(found_Not_Found);
                    if (found_Not_Found == true) {
                        list.remove(we);
                    }
                }
            } else {
                System.out.println("AddressBookNotffoundd");
            }
        }
    }

    public void sortAlphabetically() {
        Comparator<AddressBook> NameCommparator = Comparator.comparing(AddressBook::getFirstName);
        System.out.println("Enter Address Book Name");
        String addressBookName = sc.next();
        List<AddressBook> listSorted = new ArrayList<AddressBook>();
        listSorted = mapper.entrySet().stream().filter(e -> e.getKey().equals(addressBookName)).flatMap(e -> e.getValue()
                .stream().sorted(NameCommparator)).collect(Collectors.toList());
        System.out.println(listSorted.toString());
    }

    public void sortUsingCityStateOrPin() {
        Comparator<AddressBook> cityCommparator = Comparator.comparing(AddressBook::getCityName);
        Comparator<AddressBook> stateCommparator = Comparator.comparing(AddressBook::getStateName);
        Comparator<AddressBook> zipCommparator = Comparator.comparing(AddressBook::getZIP_CODE);

        System.out.println("1. City 2. state 3.Zip");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Enter address book name");
                String addressBookName = sc.next();
                List<AddressBook> listSorted = new ArrayList<AddressBook>();
                listSorted = mapper.entrySet().stream().filter(e -> e.getKey().equals(addressBookName)).flatMap(e -> e.getValue()
                        .stream().sorted(cityCommparator)).collect(Collectors.toList());
                for (AddressBook r : listSorted) {
                    r.display1();
                }
                break;
            case 2:
                System.out.println("Enter address book name");
                String addressBookNameState = sc.next();
                List<AddressBook> listSortedState = new ArrayList<AddressBook>();
                listSortedState = mapper.entrySet().stream().filter(e -> e.getKey().equals(addressBookNameState)).
                        flatMap(e -> e.getValue().stream().sorted(stateCommparator)).collect(Collectors.toList());
                for (AddressBook r : listSortedState) {
                    r.display1();
                }
                break;
            case 3:
                System.out.println("Enter address book name");
                String addressBookNameZip = sc.next();
                List<AddressBook> listSortedZip = new ArrayList<AddressBook>();
                listSortedState = mapper.entrySet().stream().filter(e -> e.getKey().equals(addressBookNameZip)).
                        flatMap(e -> e.getValue().stream().sorted(zipCommparator)).collect(Collectors.toList());
                for (AddressBook r : listSortedState) {
                    r.display1();
                }
                break;
            default:
                System.out.println("Inavalid choice");
                break;
        }
    }

    public void writeToFile(String AddressBookName, AddressBook fileObjectToBeWritten) throws IOException {
        Path directoryPath = Paths.get("E:\\Eclipse_Practise\\AddressBook\\DataFiles");
        File directoryObject = directoryPath.toFile();
        if (Files.isDirectory(directoryPath)) {
            String fileName = directoryPath + "/" + AddressBookName + ".txt";
            Path filePath = Paths.get(fileName);
            File fileObject = filePath.toFile();
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                BufferedWriter out = new BufferedWriter(
                        new FileWriter(String.valueOf(filePath)));
                appendStrToFile(String.valueOf(filePath), "-" + System.lineSeparator());
                appendStrToFile(String.valueOf(filePath), " First Name : " + fileObjectToBeWritten.FIRST_NAME + System.lineSeparator());
                appendStrToFile(String.valueOf(filePath), " Last Name : " + fileObjectToBeWritten.LAST_NAME + System.lineSeparator());
                appendStrToFile(String.valueOf(filePath), " Phone  : " + fileObjectToBeWritten.PHONE_NUMBER + System.lineSeparator());
                appendStrToFile(String.valueOf(filePath), " Email  : " + fileObjectToBeWritten.EMAIL + System.lineSeparator());
                appendStrToFile(String.valueOf(filePath), " State  : " + fileObjectToBeWritten.STATE + System.lineSeparator());
                appendStrToFile(String.valueOf(filePath), " Address  : " + fileObjectToBeWritten.ADDRESS + System.lineSeparator());
                appendStrToFile(String.valueOf(filePath), " Zip  : " + fileObjectToBeWritten.ZIP_CODE + System.lineSeparator());
                appendStrToFile(String.valueOf(filePath), " City  : " + fileObjectToBeWritten.CITY + System.lineSeparator());
            } else if (Files.exists(filePath)) {
                appendStrToFile(String.valueOf(filePath), "-" + System.lineSeparator());
                appendStrToFile(String.valueOf(filePath), " First Name : " + fileObjectToBeWritten.FIRST_NAME + System.lineSeparator());
                appendStrToFile(String.valueOf(filePath), " Last Name : " + fileObjectToBeWritten.LAST_NAME + System.lineSeparator());
                appendStrToFile(String.valueOf(filePath), " Phone  : " + fileObjectToBeWritten.PHONE_NUMBER + System.lineSeparator());
                appendStrToFile(String.valueOf(filePath), " Email  : " + fileObjectToBeWritten.EMAIL + System.lineSeparator());
                appendStrToFile(String.valueOf(filePath), " State  : " + fileObjectToBeWritten.STATE + System.lineSeparator());
                appendStrToFile(String.valueOf(filePath), " Address  : " + fileObjectToBeWritten.ADDRESS + System.lineSeparator());
                appendStrToFile(String.valueOf(filePath), " Zip  : " + fileObjectToBeWritten.ZIP_CODE + System.lineSeparator());
                appendStrToFile(String.valueOf(filePath), " City  : " + fileObjectToBeWritten.CITY + System.lineSeparator());
            }
        }
    }

    public static void appendStrToFile(String fileName, String str) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
            out.write(str);
            out.close();
        } catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }
}
