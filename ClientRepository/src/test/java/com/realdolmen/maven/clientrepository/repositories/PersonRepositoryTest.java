
package com.realdolmen.maven.clientrepository.repositories;

import com.realdolmen.maven.clientrepository.domain.Person;
import com.realdolmen.maven.clientrepository.domain.PostalCode;
import com.realdolmen.maven.clientrepository.exceptions.NoQueryPossibleException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.junit.Test;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
import org.junit.Ignore;

@RunWith(MockitoJUnitRunner.class)
public class PersonRepositoryTest {
    
private static String URL = "jdbc:mysql://localhost:3306/clientdbtest?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private PersonRepository personRepository;

    @Mock
    private ResultSet resultSet;

    @Before
    public void init() {
        personRepository = new PersonRepository();
    }
    
    @Test
    public void testFindAll() throws NoQueryPossibleException{
        PersonRepository personRepository = new PersonRepository();
        List<Person> persons = personRepository.findAll();
        assertFalse(persons.isEmpty());
    }
    
    //TODO implement all tests
     @Test
    public void createObjectTestSucces() throws SQLException, NoQueryPossibleException {
        //init data
        when(resultSet.getInt(PersonRepository.KEY))
                .thenReturn(1);
        when(resultSet.getString(PersonRepository.FIRST_NAME))
                .thenReturn("first_name");
        when(resultSet.getString(PersonRepository.NAME)).thenReturn("name");
        
        //test the test object
        Person result = personRepository.createObject(resultSet);
        //verify the result
        assertEquals(1, result.getNumber());
        assertEquals("first_name", result.getFirstName());
        assertEquals("name", result.getName());
        verify(resultSet, times(1)).getInt(PersonRepository.KEY);
        verify(resultSet, times(1)).getString(PersonRepository.FIRST_NAME);
         verify(resultSet, times(1)).getString(PersonRepository.NAME);
    }

    @Test
    public void createObjectTestThrowsSQLException() throws SQLException {
        //init data
        when(resultSet.getInt(PersonRepository.KEY))
                .thenThrow(SQLException.class);
        //test the object
        Person result = personRepository.createObject(resultSet);
        //verify the result
        assertNull(result);
        verify(resultSet, times(1)).getInt(PersonRepository.KEY);
        verifyNoMoreInteractions(resultSet);
    }

    @Ignore
    public void insertDeleteTest() throws NoQueryPossibleException {
        insert();
        delete();
    }

    private void delete() throws NoQueryPossibleException {
        assertNotNull(personRepository.deleteItem(7999));
      
    }

    private void insert() throws NoQueryPossibleException {
        Person person = new Person();
        person.setNumber(99);
        person.setFirstName("Steven");
        person.setName("De Cock");
        
       
        
        assertNotNull(personRepository.insertItem(person));
    }
}
