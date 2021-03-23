package com.addressbook;

public class AddressBookException extends Exception {

    String message;

    public AddressBookException(String message) {
        super();
        this.message = message;
        System.out.println(message);
    }
}
