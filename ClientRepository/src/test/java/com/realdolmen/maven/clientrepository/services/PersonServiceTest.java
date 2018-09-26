package com.realdolmen.maven.clientrepository.services;

import com.realdolmen.maven.clientrepository.domain.Person;
import com.realdolmen.maven.clientrepository.exceptions.NoQueryPossibleException;
import com.realdolmen.maven.clientrepository.repositories.PersonRepository;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
