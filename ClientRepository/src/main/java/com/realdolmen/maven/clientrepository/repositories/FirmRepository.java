/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.maven.clientrepository.repositories;

import com.realdolmen.maven.clientrepository.domain.Firm;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author SDOAX36
 */
public class FirmRepository extends AbstractRepository<Firm, Integer>{

    public static final String TABLE_NAME = "client_firm";
    public static final String KEY = "client_number";
    public static final String NAME = "name";
    public static final String TAX_ID = "tax_id";
    public static final String FIELD = "field";
    
    public FirmRepository() {
        super(TABLE_NAME,KEY);
    }
    
    //This method is used for testing the db using the testdb url
    protected FirmRepository(String url){
        super(TABLE_NAME,KEY,url);
    }

    @Override
    public Firm createObject(ResultSet resultSet) {
        Firm firm = null;
        try {
            firm = new Firm();
            firm.setNumber(resultSet.getInt(KEY));
            firm.setName(resultSet.getString(NAME));
            firm.setTaxNumber(resultSet.getString(TAX_ID));
            firm.setField(resultSet.getString(FIELD));
            return firm;

        } catch (SQLException ex) {
            Logger.getLogger(FirmRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return firm;
    }

    @Override
    public String getColumnString() {
        return "("+TAX_ID+","+NAME+")";
    }

    @Override
    public String getValuesString(Firm c) {
        return "('" + c.getTaxNumber() +"','" + c.getName() + "')";
    }  
}
