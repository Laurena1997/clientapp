package com.realdolmen.maven.clientrepository.services;

import com.realdolmen.maven.clientrepository.domain.Address;
import com.realdolmen.maven.clientrepository.domain.Person;
import com.realdolmen.maven.clientrepository.exceptions.NoQueryPossibleException;
import com.realdolmen.maven.clientrepository.repositories.PersonRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author SDOAX36
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Before
    public void init() {
        personService = new PersonService(personRepository);
    }

    /**
     * Test of findAll method, of class PersonService.
     */
    @Test
    public void testFindAll() throws Exception {
    }

    /**
     * Test of findById method, of class PersonService.
     */
    @Test
    public void testFindById() throws Exception {
    }
    
    //@author Jirka
    @Test
     public void testInsertPerson() throws NoQueryPossibleException
    {
        Address address = new Address();
        Person person = new Person("Laurena", "Nijs");
        List<Address> addressess = new ArrayList<>();
       addressess.add(address);
       
        person.setNumber(1);
        person.setAddress((ArrayList<Address>) addressess);
        when(personRepository.insertItem(person)).thenReturn(1);
        when(personRepository.findById(1)).thenReturn(person);
        
       
        personService.insertPerson(person);
        verify(personRepository, times(1)).insertItem(person);
    }

    
    @Test
    public void testRemovePerson() throws NoQueryPossibleException {
        Person person = new Person();
        person.setNumber(1);
        personService.removePerson(person);
        verify(personRepository, times(1)).deleteItem(person.getNumber());
    }

    @Test
    public void insertPersonTest() throws Exception {

    }
}
