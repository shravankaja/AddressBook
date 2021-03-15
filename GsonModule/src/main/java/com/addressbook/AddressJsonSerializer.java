package com.addressbook;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.std.*;

import java.io.*;

public class AddressJsonSerializer extends StdSerializer<AddressBook> {

    public AddressJsonSerializer() {
        super(AddressBook.class);
    }

    @Override
    public void serialize(AddressBook value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        provider.defaultSerializeField("FirstName", value.getFIRST_NAME(), gen);
        provider.defaultSerializeField("LastName", value.getLAST_NAME(), gen);
        provider.defaultSerializeField("Email", value.getEMAIL(), gen);
        provider.defaultSerializeField("PhoneNumber", value.getPHONE_NUMBER(), gen);
        provider.defaultSerializeField("ZipCode", value.getZIP_CODE(), gen);
        provider.defaultSerializeField("Address", value.getADDRESS(), gen);
        provider.defaultSerializeField("City", value.getCITY(), gen);
        provider.defaultSerializeField("State", value.getSTATE(), gen);
        gen.writeEndObject();
    }

}
