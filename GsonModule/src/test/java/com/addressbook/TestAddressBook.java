package com.addressbook;

import org.codehaus.jackson.annotate.*;
import org.json.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.sql.*;
import java.time.*;
import java.util.*;

public class TestAddressBook {
    AddressBook addressBook = new AddressBook();
    AddressBookDBService addressBookDBService = new AddressBookDBService();

    @Test
    public void givenDataBaseWeShouldBeAbleToRetrieveAllTheRecords() throws IOException, JSONException {
        Assertions.assertEquals(2, addressBook.display());
    }

    @Test
    public void givenConnectionPaarametersShouldConnectToDaatabaseAndReturnConnection() {
        Connection connection = addressBookDBService.getConnectToDataBase();
        Assertions.assertTrue(checkNullConnection(connection));
    }

    public boolean checkNullConnection(Connection connection) {
        if (connection == null) {
            return false;
        }
        return true;
    }

    @Test
    void whenJdbcDriverClassIsLoadedWeShouldBeAbleToObtainListOfDrivers() {
        ArrayList<String> listOfDriversTest = new ArrayList<>();
        listOfDriversTest.add("com.mysql.jdbc.Driver");
        listOfDriversTest.add("com.mysql.fabric.jdbc.FabricMySQLDriver");
        Assertions.assertEquals(listOfDriversTest, addressBookDBService.listDrivers());
    }

    @Test
    void givenDatabaseWeShouldBeAbleToObtainNumberORecrodsInDatabse() throws IOException, JSONException {
        Assertions.assertEquals(4, addressBook.display());
    }

    @Test
    void givenValueToBeEditedForAContactToUpdateDatabase() throws SQLException, AddressBookException {
        Assertions.assertTrue(addressBook.update("email", "thruys@hu.com", "Bill"));
    }

    @Test
    void givenDateRangeWeShouldBeAbleToGetNumberORecords() throws SQLException, AddressBookException {
        Assertions.assertEquals(3, addressBook.getContactsGivenDateStateOrCity("Hyderabad"));
    }

    @Test
    void givenDataRecordShouldBeUpatedInAllTables() {
        Assertions.assertTrue(addressBook.writeToDatabase("Haryana", "GNagar", "Hyderabad",
                "Friend", "India", "ki", "Kaja", "ki",
                "2018-01-26", 50006, "SS@gmail.com", 741852));
    }

    @Test
    void givenMultipleContactsWeShouldBeAbleToAddThemUsingThreading() {
        ArrayList<AddressBook> listToAdd = new ArrayList<>(Arrays.asList(new AddressBook("Haryana", "GNagar", "Hyderabad",
                        "Friend", "India", "Abby", "Kaja", "ki", "2018-01-26", "50006",
                        "SS@gmail.com", 741852),
                new AddressBook("Haryana", "GNagar", "Hyderabad",
                        "Friend", "India", "p", "Kaja", "ki", "2018-01-26", "50006",
                        "SS@gmail.com", 741852)));
        Instant start = Instant.now();
        addressBook.writeMultipleContactsToAddressBook(listToAdd);
        Instant end = Instant.now();
        System.out.println("Duration with thread: " + Duration.between(start, end));
    }
}