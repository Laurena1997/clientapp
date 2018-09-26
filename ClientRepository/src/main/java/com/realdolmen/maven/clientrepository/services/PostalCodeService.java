package com.realdolmen.maven.clientrepository.services;

import com.realdolmen.maven.clientrepository.domain.PostalCode;
import com.realdolmen.maven.clientrepository.exceptions.NoQueryPossibleException;
import com.realdolmen.maven.clientrepository.repositories.PostalCodeRepository;
import com.sun.corba.se.impl.ior.ObjectReferenceFactoryImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PostalCodeService {

    private PostalCodeRepository postalCodeRepository;

    public PostalCodeService(PostalCodeRepository postalCodeRepository) {
        this.postalCodeRepository = postalCodeRepository;
    }

    public boolean addPostalCode(int code, String city) {
//        if(code> 999 && code <10000){
//            return postalCodeRepository.insertPostalCode(code,city);   
//        }
        return false;
    }

    public List<PostalCode> findAllPostalCodes() throws NoQueryPossibleException {
        return postalCodeRepository.findAll();
    }

    public List<PostalCode> findAllPostalCodesFromTheNine() throws NoQueryPossibleException {
        List<PostalCode> postalCodes = postalCodeRepository.findAll();
        List<PostalCode> tempList = new ArrayList<>();
        for (PostalCode p : postalCodes) {
            if (p.getNumber() > 8999 && p.getNumber() < 10000) {
                tempList.add(p);
            }
        }
        return tempList;
    }

    public PostalCode findById(int id) throws NoQueryPossibleException {
        return postalCodeRepository.findById(id);
    }

    public boolean removePostalCode(PostalCode postalCode) throws NoQueryPossibleException {
        return postalCodeRepository.deleteItem(postalCode.getNumber());
    }

    public boolean updatePostalCode(PostalCode postalCode) throws NoQueryPossibleException {
        postalCodeRepository.updateItem(postalCode);
        return false;
    }

    public PostalCode insertPostalCode(PostalCode postalCode) throws NoQueryPossibleException {
        return postalCodeRepository.insertItem(postalCode);
    }
}
