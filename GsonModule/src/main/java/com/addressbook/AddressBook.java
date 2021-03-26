package com.addressbook;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.*;
import com.fasterxml.jackson.databind.module.*;
import org.codehaus.jackson.*;
import org.json.*;

import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.*;
import java.util.stream.*;

@JsonSerialize(using = AddressJsonSerializer.class)
public class AddressBook {
    public String FIRST_NAME;
    public String LAST_NAME;
    public String ADDRESS;
    public String CITY;
    public String STATE;
    public String ZIP_CODE;
    public String PHONE_NUMBER;
    public String EMAIL;
    public static String cityName;
    public static String stateName;
    Map<String, List<AddressBook>> mapper = new HashMap<>();
    Map<String, List<AddressBook>> cityContactMap = new HashMap<>();
    Map<String, List<AddressBook>> stateContactMap = new HashMap<>();
    ArrayList<AddressBook> address_Book_No = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    public static int NO_OF_CONTACTS;
    public static String Address_Book_Name;
    boolean found_Not_Found;
    String addressBookName;
    String contactType;
    String country;
    String street;
    int PHONE;
    String dateAdded;
    ArrayList<HashMap<String, List<AddressBook>>> list = new ArrayList<>();
    ArrayList<AddressBook> listOfTableObjects = new ArrayList<>();
    HashMap<String, List<AddressBook>> contactFirstNameListTables = new HashMap<>();
    AddressBookDBService addressBookDBService = new AddressBookDBService();

    public AddressBook() {
        // TODO Auto-generated constructor stub
    }

    public AddressBook(String FIRST_NAME, String LAST_NAME, String ADDRESS, String CITY,
                       String STATE, String ZIP_CODE, String PHONE_NUMBER, String EMAIL) {
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.ADDRESS = ADDRESS;
        this.CITY = CITY;
        this.STATE = STATE;
        this.ZIP_CODE = ZIP_CODE;
        this.PHONE_NUMBER = PHONE_NUMBER;
        this.EMAIL = EMAIL;
    }

    public AddressBook(String FIRST_NAME, String LAST_NAME, int PHONE, String EMAIL, String addressBookName) {
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.PHONE = PHONE;
        this.EMAIL = EMAIL;
        this.addressBookName = addressBookName;
    }

    public AddressBook(String FIRST_NAME, String contactType) {
        this.FIRST_NAME = FIRST_NAME;
        this.contactType = contactType;
    }

    public AddressBook(String CITY, String STATE, String ZIP_CODE, String country, String street, String FIRST_NAME) {
        this.CITY = CITY;
        this.STATE = STATE;
        this.ZIP_CODE = ZIP_CODE;
        this.country = country;
        this.street = street;
        this.FIRST_NAME = FIRST_NAME;
    }

    public AddressBook(String STATE, String street, String CITY, String contactType, String country, String FIRST_NAME, String LAST_NAME, String addressBookName,
                       String dateAdded, String ZIP_CODE, String EMAIL, int PHONE) {
        this.CITY = CITY;
        this.STATE = STATE;
        this.ZIP_CODE = ZIP_CODE;
        this.country = country;
        this.street = street;
        this.FIRST_NAME = FIRST_NAME;
        this.contactType = contactType;
        this.LAST_NAME = LAST_NAME;
        this.PHONE = PHONE;
        this.EMAIL = EMAIL;
        this.dateAdded = dateAdded;
        this.addressBookName = addressBookName;
    }

    public AddressBook(String FIRST_NAME, String LAST_NAME, String CITY, String STATE, String ZIP_CODE, String EMAIL, int PHONE) {
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.CITY = CITY;
        this.STATE = STATE;
        this.ZIP_CODE = ZIP_CODE;
        this.EMAIL = EMAIL;
        this.PHONE = PHONE;
    }

    private List<AddressBook> arrayOfContact;

    public AddressBook(List<AddressBook> arrayOfContact) {
        this();
        this.arrayOfContact = new ArrayList<>(arrayOfContact);
    }


    public void writeData(String STATE, String street, String CITY, String contactType, String country, String FIRST_NAME, String LAST_NAME, String addressBookName,
                          String dateAdded, String ZIP_CODE, String EMAIL, int PHONE) {
        int zip = Integer.parseInt(ZIP_CODE);
        List<AddressBook> innerList = new ArrayList<>();
        innerList = addressBookDBService.write(STATE, street, CITY, contactType, country,
                FIRST_NAME, LAST_NAME, addressBookName, dateAdded, zip,

                EMAIL, PHONE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(FIRST_NAME, LAST_NAME, PHONE, ZIP_CODE);
    }


    public void writeMultipleContactsToAddressBook(ArrayList<AddressBook> listToAdd) {
        List<Boolean> check = new ArrayList<>();
        String name;
        Map<Integer, Boolean> AdditionStatus = new HashMap<>();
        listToAdd.stream().forEach(addressBook -> {
            Runnable task = () -> {
                AdditionStatus.put(addressBook.hashCode(), false);
                System.out.println("Employee Beign Added: " + Thread.currentThread().getName());
                this.writeData(addressBook.STATE, addressBook.street, addressBook.CITY, addressBook.contactType, addressBook.country,
                        addressBook.FIRST_NAME, addressBook.LAST_NAME, addressBook.addressBookName, addressBook.dateAdded, addressBook.ZIP_CODE,
                        addressBook.EMAIL, addressBook.PHONE);
                System.out.println(AdditionStatus);
                AdditionStatus.put(addressBook.hashCode(), true);
                System.out.println("After addition :" + Thread.currentThread().getName());
            };
            Thread thread = new Thread(task, addressBook.FIRST_NAME);
            thread.start();
        });
        while (AdditionStatus.containsValue(false)) {
            try {
                Thread.sleep(4500000, 55555555);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public String getCITY() {
        return CITY;
    }

    public String getSTATE() {
        return STATE;
    }

    public String getZIP_CODE() {
        return ZIP_CODE;
    }

    public String getPHONE_NUMBER() {
        return PHONE_NUMBER;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    @Override
    public String toString() {
        return FIRST_NAME + "," + LAST_NAME + "," + ADDRESS + "," + CITY + "," + STATE + "," + ZIP_CODE + ","
                + PHONE_NUMBER + "," + EMAIL;
    }

    public void searchContactByStateOrCity() {
        System.out.println("Enter city ");
        String cityName = sc.next();
        System.out.println("Enter person first name");
        String firstName = sc.next();
        List<Object> listObjects = new ArrayList<>();
        listObjects = cityContactMap.entrySet().stream().filter(e -> e.getKey().equals(cityName))
                .flatMap(e -> e.getValue().stream()).filter(e -> e.getFIRST_NAME().equals(firstName))
                .collect(Collectors.toList());
        System.out.println(listObjects);
        int num = listObjects.size();
        if (num == 0) {
            System.out.println("No records");
        }

    }

    public boolean duplicateEntriesCheck(String firstName) {
        List<Object> dpNameCheck = new ArrayList<>();
        dpNameCheck = mapper.entrySet().stream().map(e -> e.getValue().stream())
                .flatMap(e -> e.filter(s -> s.getFIRST_NAME().equals(firstName))).collect(Collectors.toList());
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

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public void setZIP_CODE(String ZIP_CODE) {
        this.ZIP_CODE = ZIP_CODE;
    }

    public void setPHONE_NUMBER(String PHONE_NUMBER) {
        this.PHONE_NUMBER = PHONE_NUMBER;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public void add() throws IOException {

        System.out.println("Enter Address Book Name \n");
        Address_Book_Name = sc.next();
        System.out.println("Enter Number of Contacts \n");
        NO_OF_CONTACTS = sc.nextInt();
        for (int i = 0; i < NO_OF_CONTACTS; i++) {
            System.out.println("Enter first Name  \n");
            FIRST_NAME = sc.next();
            if (duplicateEntriesCheck(FIRST_NAME)) {
                System.out.println("Name already taken");
                break;
            }
            System.out.println("Enter Address Lane \n");
            ADDRESS = sc.next();
            System.out.println("Enter City \n");
            CITY = sc.next();
            System.out.println("Enter state \n");
            STATE = sc.next();
            System.out.println("Enter Email \n");
            EMAIL = sc.next();
            System.out.println("Enter Phone Number \n");
            PHONE_NUMBER = sc.next();
            System.out.println("Enter Zip Name \n");
            ZIP_CODE = sc.next();
            System.out.println("Enter Last Name \n");
            LAST_NAME = sc.next();

            mapper.computeIfAbsent(Address_Book_Name, Address_Book_Name -> new ArrayList<>()).add(new AddressBook(
                    LAST_NAME, FIRST_NAME, ADDRESS, CITY, STATE, PHONE_NUMBER, ZIP_CODE, EMAIL));
            writeToFile(Address_Book_Name, new AddressBook(
                    LAST_NAME, FIRST_NAME, ADDRESS, CITY, STATE, PHONE_NUMBER, ZIP_CODE, EMAIL));
            cityContactMap.computeIfAbsent(CITY, cityName -> new ArrayList<>()).add(new AddressBook(
                    LAST_NAME, FIRST_NAME, ADDRESS, CITY, STATE, PHONE_NUMBER, ZIP_CODE, EMAIL));
            writeToFile(CITY, new AddressBook(
                    LAST_NAME, FIRST_NAME, ADDRESS, CITY, STATE, PHONE_NUMBER, ZIP_CODE, EMAIL));
            stateContactMap.computeIfAbsent(STATE, stateName -> new ArrayList<>()).add(new AddressBook(
                    LAST_NAME, FIRST_NAME, ADDRESS, CITY, STATE, PHONE_NUMBER, ZIP_CODE, EMAIL));
            writeToFile(STATE, new AddressBook(
                    LAST_NAME, FIRST_NAME, ADDRESS, CITY, STATE, PHONE_NUMBER, ZIP_CODE, EMAIL));
        }

    }

    public void getCountCity() {
        System.out.println("Enter  City name  \n");
        String cityNameFilter = sc.next();
        List<Object> objectList = new ArrayList<>();
        System.out.println(cityContactMap);
        objectList = cityContactMap.entrySet().stream().filter(e -> e.getKey().equals(cityNameFilter))
                .flatMap(e -> e.getValue().stream()).collect(Collectors.toList());
        int num = objectList.size();
        System.out.println("Size " + num);
    }

    public void getCountState() {
        System.out.println("Enter  City name  \n");
        String stateNameFilter = sc.next();
        List<Object> objectList = new ArrayList<>();
        objectList = stateContactMap.entrySet().stream().filter(e -> e.getKey().equals(stateNameFilter))
                .flatMap(e -> e.getValue().stream()).collect(Collectors.toList());
        int num = objectList.size();
        System.out.println("Size " + num);
    }

    public Connection connectToDatabase() {
        String jdbcURL = "jdbc:mysql://localhost:3306/addressbook?allowPublicKeyRetrieval=true&useSSL=false";
        String userName = "root";
        String password = "Addtexthere25";
        Connection connection = null;  //database connection
        String connectionString = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("No drivers loaded ", e);
        }
        try {
            System.out.println("Connectin to " + jdbcURL);
            connection = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println(connection);
            connectionString = connection.toString();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public int display() throws IOException, JSONException {
        ArrayList<HashMap<String, List<AddressBook>>> list = new ArrayList<>();
        int result = 0;
        String sql = " select cd.*,c_d.street,c_d.city,c_d.state,c_d.zip,c_d.country,ct.contact_type " +
                "from contact_details as cd inner join contact_addresses as c_d on cd.first_name=c_d.first_name inner\n" +
                " join contact_types as ct on cd.first_name=ct.first_name";
        list = addressBookDBService.readData(sql);
        result = list.size();
        return result;
    }

    public boolean update(String columnToBeEdited, String value, String firstName) throws SQLException, AddressBookException {
        List<AddressBook> list = new ArrayList<>();
        List<AddressBook> listCheck = new ArrayList<>();
        list = addressBookDBService.update(columnToBeEdited, value, firstName);
        listCheck = addressBookDBService.getContactObject(firstName);
        if (list.equals(listCheck)) {
            return true;
        }
        return false;
    }

    public int getContactsGivenDateStateOrCity(String value) throws AddressBookException, SQLException {
        int result = addressBookDBService.getContactsGivenDateStateOrCity(value);
        if (result == 0) try {
            throw new AddressBookException("No records");
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean writeToDatabase(String state, String street, String city, String contactType,
                                   String country, String firstName, String lastName,
                                   String addressBookName, String dateAdded, int zip, String email,
                                   int phoneNumber) {
        List<AddressBook> list = new ArrayList<>();
        List<AddressBook> listCheck = new ArrayList<>();
        list = addressBookDBService.write(state, street, city, contactType,
                country, firstName, lastName,
                addressBookName, dateAdded, zip, email, phoneNumber);
        listCheck = addressBookDBService.getContactObject(firstName);
        if (list.equals(listCheck)) {
            return true;
        }
        return false;
    }


    public void displayCity() {
        System.out.println("Enter  City name  \n");
        String cityNameFilter = sc.next();
        List<AddressBook> objectList = new ArrayList<>();
        objectList = cityContactMap.entrySet().stream().filter(e -> e.getKey().equals(cityNameFilter))
                .flatMap(e -> e.getValue().stream()).collect(Collectors.toList());
        // System.out.println("List of city objects :" + objectList.toString());
        for (AddressBook r : objectList) {
            r.display1();
        }
    }

    public void displayState() {
        System.out.println("Enter  State name  \n");
        String cityNameFilter = sc.next();
        List<AddressBook> objectList = new ArrayList<>();
        objectList = stateContactMap.entrySet().stream().filter(e -> e.getKey().equals(cityNameFilter))
                .flatMap(e -> e.getValue().stream()).collect(Collectors.toList());
        for (AddressBook r : objectList) {
            r.display1();
        }
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
                                FIRST_NAME = sc.next();
                                r.FIRST_NAME = FIRST_NAME;
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
                                String phone_Number = sc.next();
                                r.PHONE_NUMBER = phone_Number;
                                break;
                            case 8:
                                System.out.println("Enter Zip Name \n");
                                String zip_Code = sc.next();
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
        Comparator<AddressBook> NameCommparator = Comparator.comparing(AddressBook::getFIRST_NAME);
        System.out.println("Enter Address Book Name");
        String addressBookName = sc.next();
        List<AddressBook> listSorted = new ArrayList<AddressBook>();
        listSorted = mapper.entrySet().stream().filter(e -> e.getKey().equals(addressBookName))
                .flatMap(e -> e.getValue().stream().sorted(NameCommparator)).collect(Collectors.toList());
        System.out.println(listSorted.toString());
    }

    public void sortUsingCityStateOrPin() {
        Comparator<AddressBook> cityCommparator = Comparator.comparing(AddressBook::getCITY);
        Comparator<AddressBook> stateCommparator = Comparator.comparing(AddressBook::getSTATE);
        Comparator<AddressBook> zipCommparator = Comparator.comparing(AddressBook::getZIP_CODE);

        System.out.println("1. City 2. state 3.Zip");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Enter address book name");
                String addressBookName = sc.next();
                List<AddressBook> listSorted = new ArrayList<AddressBook>();
                listSorted = mapper.entrySet().stream().filter(e -> e.getKey().equals(addressBookName))
                        .flatMap(e -> e.getValue().stream().sorted(cityCommparator)).collect(Collectors.toList());
                for (AddressBook r : listSorted) {
                    r.display1();
                }
                break;
            case 2:
                System.out.println("Enter address book name");
                String addressBookNameState = sc.next();
                List<AddressBook> listSortedState = new ArrayList<AddressBook>();
                listSortedState = mapper.entrySet().stream().filter(e -> e.getKey().equals(addressBookNameState))
                        .flatMap(e -> e.getValue().stream().sorted(stateCommparator)).collect(Collectors.toList());
                for (AddressBook r : listSortedState) {
                    r.display1();
                }
                break;
            case 3:
                System.out.println("Enter address book name");
                String addressBookNameZip = sc.next();
                List<AddressBook> listSortedZip = new ArrayList<AddressBook>();
                listSortedState = mapper.entrySet().stream().filter(e -> e.getKey().equals(addressBookNameZip))
                        .flatMap(e -> e.getValue().stream().sorted(zipCommparator)).collect(Collectors.toList());
                for (AddressBook r : listSortedState) {
                    r.display1();
                }
                break;
            default:
                System.out.println("Inavalid choice");
                break;
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

    public void writeToFile(String AddressBookName, AddressBook obj) throws IOException {
        Object wr = null;
        Path directoryPath = Paths.get("E:\\Eclipse_Practise\\AddressBook\\GsonModule\\DataFiles");
        String fileName = directoryPath + "/" + AddressBookName + ".json";
        Path filePath = Paths.get(fileName);
        File fileObject = filePath.toFile();
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
            final ObjectMapper mapperJson = new ObjectMapper();
            final String json = mapperJson.writeValueAsString(obj);
            appendStrToFile(String.valueOf(filePath), json);
            appendStrToFile(String.valueOf(filePath), "\n");
        } else if (Files.exists(filePath)) {
            ArrayList<String> jsonStrings = new ArrayList<>();
            Scanner myreader = new Scanner(fileObject);
            while (myreader.hasNextLine()) {
                jsonStrings.add(myreader.nextLine());
            }
            PrintWriter writer = new PrintWriter(String.valueOf(filePath));
            writer.print("");
            writer.close();
            appendStrToFile(String.valueOf(filePath), "[");
            for (int i = 0; i < jsonStrings.size(); i++) {
                appendStrToFile(String.valueOf(filePath), jsonStrings.get(i));
                appendStrToFile(String.valueOf(filePath), ",");
            }
            final ObjectMapper mapperJson = new ObjectMapper();
            final String json = mapperJson.writeValueAsString(obj);
            appendStrToFile(String.valueOf(filePath), json);
            appendStrToFile(String.valueOf(filePath), "]");

        }
    }

    public ArrayList<String> convertObjectToJsonString(ArrayList<AddressBook> list) {
        ArrayList<String> listString = new ArrayList<>();
        final ObjectMapper mapperJson = new ObjectMapper();
        for (AddressBook addressBook : list) {
            try {
                String json = mapperJson.writeValueAsString(addressBook);
                listString.add(json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        System.out.println(listString);
        return listString;
    }

    public AddressBook updateJsonObject(String lastName, String email) {
        System.out.println(this.arrayOfContact);
        for (AddressBook addressBook : this.arrayOfContact) {
            if (addressBook.LAST_NAME.equals(lastName)) {
                addressBook.EMAIL = email;
                return addressBook;
            }
        }
        return null;
    }

    public int countEntries() {
        return this.arrayOfContact.size();
    }

    public ArrayList<AddressBook> convertJsonStringToListOObjects(String data) throws JSONException, JsonProcessingException {
        ObjectMapper objectMapper1 = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(AddressBook.class, new AddressBookDeserializer());
        objectMapper1.registerModule(simpleModule);
        ArrayList<AddressBook> list = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(data);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String jsonObjectAsString = jsonObject.toString();
            AddressBook r = objectMapper1.readValue(jsonObjectAsString, AddressBook.class);
            list.add(r);
        }
        return list;
    }

}

