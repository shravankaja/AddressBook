package com.addressbook;

import java.sql.*;
import java.util.*;

public class AddressBookDBService {
    ArrayList<HashMap<String, List<AddressBook>>> list = new ArrayList<>();
    ArrayList<AddressBook> listOfTableObjects = new ArrayList<>();
    HashMap<String, List<AddressBook>> contactFirstNameListTables = new HashMap<>();

    public Connection getConnectToDataBase() {
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

    public ArrayList<String> listDrivers() {
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        ArrayList<String> listOfDrivers = new ArrayList<>();
        while (driverList.hasMoreElements()) {
            Driver driver = (Driver) driverList.nextElement();
            System.out.println("  " + driver.getClass().getName());
            listOfDrivers.add(driver.getClass().getName());
        }
        return listOfDrivers;
    }

    public Connection initiateConnection() {
        try {
            Connection connection = this.getConnectToDataBase();
            if (connection == null) {
                System.out.println("No connection Estaablished");
            } else {
                return connection;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Statement returnStatementAfterConnection(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            return statement;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }


    public ArrayList<HashMap<String, List<AddressBook>>> readData(String sql) {

        try {
            Statement statement = this.returnStatementAfterConnection(this.initiateConnection());
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int phone = resultSet.getInt("phone_number");
                String email = resultSet.getString("email");
                String addressBooKName = resultSet.getString("address_book_name");
                String street = resultSet.getString("street");
                String state = resultSet.getString("state");
                String zip = resultSet.getString("zip");
                String city = resultSet.getString("city");
                String country = resultSet.getString("country");
                String contactType = resultSet.getString("contact_type");
                listOfTableObjects.add(new AddressBook(street, city, state, zip, country, firstName));
                listOfTableObjects.add(new AddressBook(firstName, contactType));
                listOfTableObjects.add(new AddressBook(firstName, lastName, phone, email, addressBooKName));
                contactFirstNameListTables.put(firstName, listOfTableObjects);
                list.add(contactFirstNameListTables);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return list;
    }

    public List<AddressBook> update(String columnToBeUpdated, String value, String firstName) throws AddressBookException, SQLException {
        ArrayList<String> address = new ArrayList<String>(
                Arrays.asList("street", "city", "state", "zip", "country"));
        ArrayList<String> details = new ArrayList<String>(
                Arrays.asList("first_name", "last_name", "phone_number", "email", "address_book_name"));
        ArrayList<String> type = new ArrayList<String>(
                Arrays.asList("contact_type"));
        String tableName;
        if (address.contains(columnToBeUpdated)) {
            tableName = "contact_addresses";
        } else if (details.contains(columnToBeUpdated)) {
            tableName = "contact_details";
        } else if (type.contains(columnToBeUpdated)) {
            tableName = "contact_type";
        } else {
            throw new AddressBookException("No such coloumn");
        }

        if (isNumeric(value)) {
            int valueInt = Integer.parseInt(value);
            String sql = String.format("update %s  set %s=%s where first_name='%s'",
                    tableName, columnToBeUpdated, valueInt, firstName);
            Statement statement = this.returnStatementAfterConnection(this.initiateConnection());
            int result = statement.executeUpdate(sql);
            if (result == 0) throw new AddressBookException("Not Updates");

        } else if (!isNumeric(value)) {
            String sql = String.format("update %s  set %s='%s' where first_name='%s'",
                    tableName, columnToBeUpdated, value, firstName);
            Statement statement = this.returnStatementAfterConnection(this.initiateConnection());
            int result = statement.executeUpdate(sql);
            if (result == 0) throw new AddressBookException("Not Updates");
        }
        return this.getContactObject(firstName);
    }

    public List<AddressBook> getContactObject(String firstName) {
        List<AddressBook> listOne = new ArrayList<>();
        String sql = String.format("select cd.*,c_d.street,c_d.city,c_d.state,c_d.zip,c_d.country,ct.contact_type " +
                "from contact_details as cd inner join contact_addresses as c_d on cd.first_name=c_d.first_name inner\n" +
                " join contact_types as ct on cd.first_name=ct.first_name where cd.first_name='%s'", firstName);
        this.readData(sql);
        for (int i = 0; i < list.size(); i++) {
            for (String s : contactFirstNameListTables.keySet()) {
                if (s.equals(firstName)) {
                    listOne = contactFirstNameListTables.get(s);
                }
            }
        }
        System.out.println(listOne);
        return listOne;
    }

    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

    public int getContactsGivenDateStateOrCity(String value) throws SQLException {
        int numberOfContacts = 0;
        String sql = null;
        ArrayList<String> cities = new ArrayList<String>(
                Arrays.asList("Hyderabad", "Delhi"));
        ArrayList<String> states = new ArrayList<String>(
                Arrays.asList("Telanagana", "Karnataka"));
        ArrayList<String> namesOfContacts = new ArrayList<>();
        if (value.contains("-")) {
            sql = String.format("select first_name from contact_details where date_added between " +
                    " cast('%s' as date) and date(now())", value);
        } else if (cities.contains(value)) {
            sql = String.format("select cd.first_name from contact_details as cd inner join contact_addresses " +
                    "as ca on cd.first_name=ca.first_name where city='%s'", value);
        } else if (states.contains(value)) {
            sql = String.format("select cd.first_name from contact_details as cd inner join contact_addresses " +
                    "as ca on cd.first_name=ca.first_name where state='%s'", value);
        }
        Statement statement = this.returnStatementAfterConnection(this.initiateConnection());
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            String name = resultSet.getString("first_name");
            namesOfContacts.add(name);
        }
        numberOfContacts = namesOfContacts.size();
        return numberOfContacts;
    }

    public List<AddressBook> write(String state, String street, String city, String contactType,
                                   String country, String firstName, String lastName,
                                   String addressBookName, String dateAdded, int zip, String email,
                                   int phoneNumber) {

        Connection connection = null;
        Statement statement = null;
        try {
            connection = this.initiateConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            String sql = String.format("insert into contact_details (first_name,last_name,phone_number,email" +
                    ",address_book_name,date_added) values ('%s','%s',%s,'%s','%s',cast('%s' as date))" +
                    "", firstName, lastName, phoneNumber, email, addressBookName, dateAdded);
            int result = statement.executeUpdate(sql);
            if (result == 0) throw new AddressBookException("Unable To Add");

        } catch (SQLException | AddressBookException exception) {
            exception.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {

            String sql = String.format("insert into contact_addresses(street,city,state,zip,country,first_name) " +
                    "values ('%s','%s','%s',%s,'%s','%s')", street, city, state, zip, country, firstName);
            int result = statement.executeUpdate(sql);
            if (result == 0) throw new AddressBookException("Unable To Add");
        } catch (SQLException exception) {
            exception.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (AddressBookException e) {
            e.printStackTrace();
        }

        try {
            String sql = String.format("insert into contact_types(first_name,contact_type) values " +
                    "('%s','%s')", firstName, contactType);
            int result = statement.executeUpdate(sql);
            if (result == 0) throw new AddressBookException("Unable To Add");
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
        try {
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return this.getContactObject(firstName);
    }
}
