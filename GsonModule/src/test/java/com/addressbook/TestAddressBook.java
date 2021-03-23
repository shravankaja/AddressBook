package com.addressbook;

import org.codehaus.jackson.annotate.*;
import org.json.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.sql.*;
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
        Assertions.assertEquals(3, addressBook.display());
    }

    @Test
    void givenValueToBeEditedForAContactToUpdateDatabase() throws SQLException, AddressBookException {
        Assertions.assertTrue(addressBook.update("email", "abc@hu.com", "Abby"));
    }

    @Test
    void givenDateRangeWeShouldBeAbleToGetNumberORecords() throws SQLException, AddressBookException {
        Assertions.assertEquals(3, addressBook.getContactsAddedInDateRange("2018-01-01"));
    }
}