package com.realdolmen.maven.clientrepository.services;

import com.realdolmen.maven.clientrepository.domain.Address;
import com.realdolmen.maven.clientrepository.domain.Firm;
import com.realdolmen.maven.clientrepository.domain.Person;
import com.realdolmen.maven.clientrepository.exceptions.NoQueryPossibleException;
import com.realdolmen.maven.clientrepository.repositories.FirmRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Ignore;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FirmServiceTest {
    //Object to test
    private FirmService firmService;
    //Object(s) not to test but needed for the object to test => Mocks
    @Mock
    private FirmRepository firmRepository;
    
    @Before
    public void init() {
        firmService = new FirmService(firmRepository);
    }

    @Test
    public void testFindAll() throws Exception {
        //init data to test
        List<Firm> firms = new ArrayList<>();
        when(firmRepository.findAll()).thenReturn(firms);
        //test the method
        List<Firm> result = firmService.findAll();
        //verify the results
        assertEquals(result, firms);
        verify(firmRepository,times(1)).findAll();
    }
    
    //@ author Laurena
    @Test
    public void testFindByIdFirmSuccess() throws Exception 
    {
        //init data
        Firm firm1 = new Firm ();
        when(firmRepository.findById(0)).thenReturn(firm1);
        //do the test
        Firm result = firmService.findById(0);
        //verify the result
        assertEquals(result, firm1);
        verify(firmRepository,times(1)).findById(0);
    }
    
    //@ author Laurena
    @Test
    public void testFindByIdFirmFail() throws Exception
    {
         //init data
        Firm firm1 = new Firm ();
        when(firmRepository.findById(0)).thenReturn(null);
        //do the test
        Firm result = firmService.findById(0);
        //verify the result
        assertNotEquals(result, firm1);
        verify(firmRepository,times(1)).findById(0);
    }
    
    //@author Laurena
    @Test
    public void removeFirmTest() throws Exception
    {
        Firm firm = new Firm();
        firm.setNumber(1);
        firmService.removeFirm(firm);
        verify(firmRepository, times(1)).deleteItem(firm.getNumber());
    }
    
    //@author Joren
    @Test
    public void insertFirmTestSuccess() throws Exception 
    {
         Address address = new Address();
        Firm firm = new Firm();
        List<Address> addressess = new ArrayList<>();
        addressess.add(address);
       
        firm.setNumber(1);
        firm.setAddress((ArrayList<Address>) addressess);
        when(firmRepository.insertItem(firm)).thenReturn(1);
        when(firmRepository.findById(1)).thenReturn(firm);
        
       
        firmService.insertFirm(firm);
        verify(firmRepository, times(1)).insertItem(firm);
    }
    
    //@author Joren
    @Test
    public void insertFirmTestFail() throws Exception
    {
         Address address = new Address();
        Firm firm = new Firm();
        List<Address> addressess = new ArrayList<>();
        addressess.add(address);
       
        firm.setNumber(1);
        firm.setAddress((ArrayList<Address>) addressess);
        when(firmRepository.insertItem(firm)).thenReturn(1);
        when(firmRepository.findById(1)).thenReturn(null);
        
       
        firmService.insertFirm(firm);
        verify(firmRepository, times(1)).insertItem(firm);
    }
    
    
    
    
    
    @Test
    public void testRemovePerson() throws NoQueryPossibleException {
        Firm firm = new Firm();
        firm.setNumber(1);
        firmService.removeFirm(firm);
        verify(firmRepository, times(1)).deleteItem(firm.getNumber());
    }
}
