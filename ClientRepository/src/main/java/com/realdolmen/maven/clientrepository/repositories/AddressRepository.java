package com.realdolmen.maven.clientrepository.repositories;

import com.realdolmen.maven.clientrepository.domain.*;
import com.realdolmen.maven.clientrepository.exceptions.NoQueryPossibleException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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

    
    @Override
    public Address createObject(ResultSet resultSet) {
        Address address = null;
        try {
            address = new Address();
            address.setNumber(resultSet.getInt(KEY));
            address.setStreet(resultSet.getString(STREET));
            PostalCode code = new PostalCode();
            code.setNumber(resultSet.getInt(POSTALCODE));
            address.setPostalCode(code);
            address.setTypeAddress(resultSet.getString(TYPEADDRESS));
            return address;

        } catch (SQLException ex) {
            Logger.getLogger(AddressRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return address;
    }

    
    @Override
    public String getColumnString() {
        return "(" + TYPEADDRESS + "," + KEY + ", box, " + POSTALCODE + ", client_person  ,client_firm )";
    }

    
    @Override
    public String getValuesString(Address c) {
        if (c.getKlant() instanceof Person) {
            //insert into (type, number, box, postalCode, client person, clientfirm) into adress values ('home', 'straat', 1, null, 9000,  1, null)
            return "('" + c.getTypeAddress() + "',' " + c.getNumber() + "','" + null + "', '" + c.getPostalCode() + "','"+ c.getKlant().getNumber() + "','" + null + "')";

        }
        if (c.getKlant() instanceof Firm) {
            //insert into (type,, number, box, postalCode, client person, clientfirm) into adress values ('home', 'straat', 1, null, 9000,  null, 2)
            return "('" + c.getTypeAddress() + "', '" + c.getNumber() + "','" + null + "', '" + c.getPostalCode() + "','" + null + "','" + c.getKlant().getNumber() + "')";
            //box is altijd null
        }
        throw new IllegalArgumentException();
    }

    public List<Address> findAddressForPersonId(int id) throws NoQueryPossibleException {
//        C object = null;
////        try (Connection connection = createConnection()) {
////            PreparedStatement pstatement = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE " + idName + " = " + id);
////            ResultSet resultSet = pstatement.executeQuery();
////            if (resultSet.next()) {
////                System.out.println("in resultset");
////                object = createObject(resultSet);
////            }
////        } catch (Exception e) {
////            throw new NoQueryPossibleException("Find by id " + tableName + " can not be excecuted");
////        }
////        return object;
        return null;
    }
    
    public List<Address>findAddressForFirm(int id) throws NoQueryPossibleException
    {
        //        C object = null;
////        try (Connection connection = createConnection()) {
////            PreparedStatement pstatement = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE " + idName + " = " + id);
////            ResultSet resultSet = pstatement.executeQuery();
////            if (resultSet.next()) {
////                System.out.println("in resultset");
////                object = createObject(resultSet);
////            }
////        } catch (Exception e) {
////            throw new NoQueryPossibleException("Find by id " + tableName + " can not be excecuted");
////        }
////        return object;
        return null;
        
    }
}
