package com.realdolmen.maven.clientrepository.repositories;

import com.realdolmen.maven.clientrepository.domain.PostalCode;
import com.realdolmen.maven.clientrepository.exceptions.NoQueryPossibleException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PostalCodeRepositoryTest {

    private static String URL = "jdbc:mysql://localhost:3306/clientdbtest?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private PostalCodeRepository postalCodeRepository;

    @Mock
    private ResultSet resultSet;

    @Before
    public void init() {
        postalCodeRepository = new PostalCodeRepository(URL);
    }

    @Test
    public void findAllTestSuccess() throws NoQueryPossibleException {
        List<PostalCode> postalCodes = postalCodeRepository.findAll();
        assertFalse(postalCodes.isEmpty());
    }

    @Test
    public void createObjectTestSucces() throws SQLException, NoQueryPossibleException {
        //init data
        when(resultSet.getInt(PostalCodeRepository.KEY))
                .thenReturn(1);
        when(resultSet.getString(PostalCodeRepository.CITY))
                .thenReturn("stad");
        //test the test object
        PostalCode result = postalCodeRepository.createObject(resultSet);
        //verify the result
        assertEquals(1, result.getNumber());
        assertEquals("stad", result.getCity());
        verify(resultSet, times(1)).getInt(PostalCodeRepository.KEY);
        verify(resultSet, times(1)).getString(PostalCodeRepository.CITY);
    }

    @Test
    public void createObjectTestThrowsSQLException() throws SQLException {
        //init data
        when(resultSet.getInt(PostalCodeRepository.KEY))
                .thenThrow(SQLException.class);
        //test the object
        PostalCode result = postalCodeRepository.createObject(resultSet);
        //verify the result
        assertNull(result);
        verify(resultSet, times(1)).getInt(PostalCodeRepository.KEY);
        verifyNoMoreInteractions(resultSet);
    }

    @Test
    public void insertDeleteTest() throws NoQueryPossibleException {
        insert();
        delete();
    }

    private void delete() throws NoQueryPossibleException {
        assertNotNull(postalCodeRepository.deleteItem(9999));
    }

    private void insert() throws NoQueryPossibleException {
        PostalCode postalCode = new PostalCode();
        postalCode.setNumber(9999);
        postalCode.setCity("A New City");
        assertNotNull(postalCodeRepository.insertItem(postalCode));
    }
}
