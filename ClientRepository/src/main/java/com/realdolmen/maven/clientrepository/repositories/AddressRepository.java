package com.realdolmen.maven.clientrepository.repositories;

import com.realdolmen.maven.clientrepository.domain.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressRepository extends AbstractRepository<Address, Integer> {

    //private static final String TABLE;
    public static final String TABLE_NAME = "client_adress";
    public static final String KEY = "client_number";
    public static final String STREET = "street";
    public static final String POSTALCODE = "postal_code";
    public static final String TYPEADDRESS = "type_address";

    public AddressRepository() {
        super(TABLE_NAME, KEY);
    }

    protected AddressRepository(String url) {
        super(TABLE_NAME, KEY, url);
    }

    //TODO implement
    @Override
    public Address createObject(ResultSet resultSet) {
        Address address = null;
        try {
            address = new Address();
            address.setNumber(resultSet.getInt(KEY));
            address.setStreet(resultSet.getString(STREET));
            address.setPostalCode((PostalCode) resultSet.getObject(POSTALCODE));
            address.setTypeAddress(resultSet.getString(TYPEADDRESS));
            return address;

        } catch (SQLException ex) {
            Logger.getLogger(AddressRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return address;
    }

    //TODO implement
    @Override
    public String getColumnString() {
        return "(" + TYPEADDRESS + "," + KEY + ", box, " + POSTALCODE + ", client_person  ,client_firm )";
    }

    //TODO implement
    @Override
    public String getValuesString(Address c) {
        if (c.getKlant() instanceof Person) {
            //insert into (type, number, box, postalCode, client person, clientfirm) into adress values ('home', 'straat', 1, null, 9000,  1, null)
            return "(" + c.getTypeAddress() + ", " + c.getNumber() + "," + null + ", " + c.getPostalCode() + c.getKlant().getNumber() + "," + null + ")";

        }
        if (c.getKlant() instanceof Firm) {
            //insert into (type,, number, box, postalCode, client person, clientfirm) into adress values ('home', 'straat', 1, null, 9000,  null, 2)
            return "(" + c.getTypeAddress() + ", " + c.getNumber() + "," + null + ", " + c.getPostalCode() + null + "," + c.getKlant().getNumber() + ")";
            //box is altijd null
        }
        throw new IllegalArgumentException();

    }
}
