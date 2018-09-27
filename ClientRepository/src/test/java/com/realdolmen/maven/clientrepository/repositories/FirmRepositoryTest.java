package com.realdolmen.maven.clientrepository.repositories;

import com.realdolmen.maven.clientrepository.domain.Firm;
import com.realdolmen.maven.clientrepository.exceptions.NoQueryPossibleException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


public class FirmRepositoryTest{
   
    private FirmRepository firmRepository;
    
    @Before
    public void init(){
        firmRepository = new FirmRepository(AbstractRepositoryTest.URL);
    }
    
    @Test
    public void findAllTestResultNotEmpty() throws NoQueryPossibleException{
        firmRepository =  new FirmRepository();
        assertFalse(firmRepository.findAll().isEmpty());
    }
    
   @Test
    public void insertDeleteTest() throws NoQueryPossibleException {
        insert();
        delete();
    }

    private void delete() throws NoQueryPossibleException {
        assertNotNull(firmRepository.deleteItem(8000));
    }

    private void insert() throws NoQueryPossibleException {
        Firm firm = new Firm();
        firm.setNumber(8000);
        firm.setTaxNumber("1861");
        firm.setField("nv");
        assertNotNull(firmRepository.insertItem(firm));
        
    }
}
    
    


