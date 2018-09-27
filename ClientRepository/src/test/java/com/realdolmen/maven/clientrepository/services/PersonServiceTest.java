package com.realdolmen.maven.clientrepository.services;

import com.realdolmen.maven.clientrepository.domain.Address;
import com.realdolmen.maven.clientrepository.domain.Firm;
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
    
    //@author Laurena
    /**
     * Test of findAll method, of class PersonService.
     */
    @Test
    public void testFindAll() throws Exception 
    {
        //init data
        List<Person> person = new ArrayList<>();
        when(personRepository.findAll()).thenReturn(person);
        //do the test
        List<Person> result = personService.findAll();
        //verify the result
        assertEquals(result, person);
        verify(personRepository,times(1)).findAll();
    }

    /**
     * Test of findById method, of class PersonService.
     */
    //@author Joren
    @Test
    public void testFindByIdSuccess() throws Exception 
    {
         //init data
        Person p2 = new Person();
        when(personRepository.findById(0)).thenReturn(p2);
        //do the test
        Person result = personService.findById(0);
        //verify the result
        assertEquals(result, p2);
        verify(personRepository,times(1)).findById(0);
    }
    
    @Test
    public void testFindByIdFail() throws Exception
    {
        //init data 
        Person p2 = new Person();
        when(personRepository.findById(0)).thenReturn(null);
        //do the test
        Person result = personService.findById(0);
        //verify the result
        assertNotEquals(result, p2);
        verify(personRepository,times(1)).findById(0);
    }
    
    //@author Jirka
    @Test
     public void testInsertPerson() throws NoQueryPossibleException
    {
        Address address = new Address();
        Person person = new Person("Jirka", "Ruzicka");
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

    //@author Brent
    @Test
    public void insertPersonTest() throws Exception 
    {
        Address address = new Address();
        Person person = new Person("Brent", "Schotte");
        List<Address> addressess = new ArrayList<>();
       addressess.add(address);
       
        person.setNumber(1);
        person.setAddress((ArrayList<Address>) addressess);
        when(personRepository.insertItem(person)).thenReturn(1);
        when(personRepository.findById(1)).thenReturn(person);
        
       
        personService.insertPerson(person);
        verify(personRepository, times(1)).insertItem(person);

    }
}
