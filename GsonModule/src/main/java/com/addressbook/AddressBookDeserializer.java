package com.addressbook;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.*;

import java.io.*;

public class AddressBookDeserializer extends StdDeserializer<AddressBook> {
    public AddressBookDeserializer() {
        this(null);
    }

    public AddressBookDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public AddressBook deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        String firstname = node.get("FirstName").textValue();
        String lastname = node.get("LastName").textValue();
        String email = node.get("Email").textValue();
        String phonenumber =  node.get("PhoneNumber").textValue();
        String zipcode =  node.get("ZipCode").textValue();
        String address = node.get("Address").textValue();
        String city = node.get("City").textValue();
        String state = node.get("State").textValue();
        AddressBook user = new AddressBook(firstname, lastname,address, city,state,zipcode,
                phonenumber,email);
        return user;
    }

}
