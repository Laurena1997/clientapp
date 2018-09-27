package com.realdolmen.maven.clientrepository.services;

import com.realdolmen.maven.clientrepository.domain.Firm;
import com.realdolmen.maven.clientrepository.exceptions.NoQueryPossibleException;
import com.realdolmen.maven.clientrepository.repositories.FirmRepository;
import java.util.List;

public class FirmService {

    private FirmRepository firmRepository;

    public FirmService(FirmRepository firmRepository) {
        this.firmRepository = firmRepository;
    }

    public List<Firm> findAll() throws NoQueryPossibleException {
        return firmRepository.findAll();
    }
    

    public Firm findById(int id) throws NoQueryPossibleException {
        return firmRepository.findById(id);
    }
    
   

    public boolean removeFirm(Firm firm) throws NoQueryPossibleException {
        return firmRepository.deleteItem(firm.getNumber());
    }

    public Firm insertFirm(Firm firm) throws NoQueryPossibleException {
        int i=  firmRepository.insertItem(firm);
        return findById(i);
    }
}
