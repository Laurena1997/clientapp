package com.realdolmen.maven.clientrepository.repositories;

import com.realdolmen.maven.clientrepository.domain.PostalCode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PostalCodeRepository extends AbstractRepository<PostalCode, Integer>{

    public static final String TABLE_NAME = "postal_code";
    public static final String KEY = "postal_code";
    public static final String CITY = "city";
    
    
    public PostalCodeRepository() {
         super(TABLE_NAME,KEY);
    }
    
    public PostalCodeRepository(String url){
        super(TABLE_NAME,KEY,url);
    }

    @Override
    public PostalCode createObject(ResultSet resultSet) {
        try {
            PostalCode postalCode = new PostalCode();
            postalCode.setNumber(resultSet.getInt(KEY));
            postalCode.setCity(resultSet.getString(CITY));
            return postalCode;
        } catch (SQLException ex) {
            Logger.getLogger(PostalCodeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


   
    @Override
    public String getColumnString() {
        return "("+KEY+","+CITY+")";
    }

 
    @Override
    public String getValuesString(PostalCode postalCode) {
        return "('"+postalCode.getNumber()+"','"+postalCode.getCity()+"')";
    }

}
