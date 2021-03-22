package com.addressbook;

import org.json.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.io.*;

public class TestAddressBook {
    AddressBook addressBook = new AddressBook();
    @Test
    public void givenDataBaseWeShouldBeAbleToRetrieveAllTheRecords() throws IOException, JSONException {
        Assertions.assertEquals(2,addressBook.display());
    }
}
