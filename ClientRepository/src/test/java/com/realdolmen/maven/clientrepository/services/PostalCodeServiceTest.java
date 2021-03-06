/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.maven.clientrepository.services;

import com.realdolmen.maven.clientrepository.domain.Address;
import com.realdolmen.maven.clientrepository.domain.Person;
import com.realdolmen.maven.clientrepository.domain.PostalCode;
import com.realdolmen.maven.clientrepository.exceptions.NoQueryPossibleException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.realdolmen.maven.clientrepository.repositories.PostalCodeRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

/**
 *
 * @author SDOAX36
 */
@RunWith(MockitoJUnitRunner.class)
public class PostalCodeServiceTest {
    
    PostalCodeService postalCodeService;
    
    @Mock
    private PostalCodeRepository postalCodeRepository;
    
    @Before
    public void init() {
        postalCodeService = new PostalCodeService(postalCodeRepository);
    }
    
    public PostalCodeServiceTest() {
    }

    /**
     * Test of addPostalCode method, of class PostalCodeService.
     */
    @Test
    public void testAddPostalCode() {
    }

    
    //@author Brent
    /**
     * Test of findAllPostalCodes method, of class PostalCodeService.
     */
    @Test
    public void testFindAllPostalCodes() throws NoQueryPossibleException
    {
        //init data
        List<PostalCode>postalCodes = new ArrayList<>();
        when(postalCodeRepository.findAll()).thenReturn(postalCodes);
        
        //do the acutal test
        List<PostalCode>result = postalCodeService.findAllPostalCodes();
        
        //verify the result
        assertEquals(postalCodes, result);
        verify(postalCodeRepository, times(1)).findAll();
        
    }
    
    //@Author Laurena
    private void createPostal(List<PostalCode> listToCreate)
    {
        for(int i = 7; i<10; i++)
        {
            PostalCode postalCode = new PostalCode();
            postalCode.setNumber(i *1000);
            listToCreate.add(postalCode);
        }
    }

    //@author Laurena
    /**
     * Test of findAllPostalCodesFromTheNine method, of class PostalCodeService.
     */
    @Test
    public void testFindAllPostalCodesFromTheNine() throws NoQueryPossibleException{
        //init test data
        List<PostalCode> postalCodes = new ArrayList<>();
        //@author Joren
        PostalCode postC = new PostalCode();
        postC.setNumber(9000);
        postalCodes.add(postC);
        
        //when(postalCodeService.findAllPostalCodesFromTheNine()).thenReturn(postalCodes);
        when(postalCodeRepository.findAll()).thenReturn(postalCodes);
        //do the actual test
        List<PostalCode>result = postalCodeService.findAllPostalCodesFromTheNine();
        //verify the result
        System.out.println(result);
        System.out.println(postalCodes);
        assertEquals(postalCodes, result);
        assertEquals(1, result.size());
        assertEquals(9000,result.get(0).getNumber());
        verify(postalCodeRepository, times(1)).findAll();
    }
    
    //@author Brent
    /**
     * Test of findById method, of class PostalCodeService.
     */
    @Test
    public void testFindByIdPostalCodeSuccess() throws Exception 
    {
         //init data
        PostalCode postalcode = new PostalCode();
        when(postalCodeRepository.findById(0)).thenReturn(postalcode);
        //do the test
        PostalCode result = postalCodeService.findById(0);
        //verify the result
        assertEquals(result, postalcode);
        verify(postalCodeRepository,times(1)).findById(0);
    }
    
    //@ author Jirka
    @Test
    public void testFindByIdPostalCodeFail() throws Exception
    {
          //init data 
        PostalCode postalcode = new PostalCode();
        when(postalCodeRepository.findById(0)).thenReturn(null);
        //do the test
        PostalCode result = postalCodeService.findById(0);
        //verify the result
        assertNotEquals(result, postalcode);
        verify(postalCodeRepository,times(1)).findById(0);
    }
    
    
    @Test
    public void testRemovePostalCode() throws NoQueryPossibleException {
        PostalCode postalCode = new PostalCode();
        postalCode.setNumber(1000);
        postalCodeService.removePostalCode(postalCode);
        verify(postalCodeRepository, times(1)).deleteItem(postalCode.getNumber());        
    }

    //@author Laurena
    /**
     * Test of updatePostalCode method, of class PostalCodeService.
     */
    @Test
    public void testUpdatePostalCode() throws NoQueryPossibleException 
    {
        PostalCode postalCode = new PostalCode();
        postalCode.setNumber(3320);
        postalCodeService.updatePostalCode(postalCode);
        verify(postalCodeRepository, times(1)).updateItem(postalCode);        
    }
    
    //@author Jirka
    @Test
    public void insertPostalCodeTest() throws NoQueryPossibleException 
    {
        Address address = new Address();
        PostalCode postalCode = new  PostalCode();
        List<Address> addressess = new ArrayList<>();
       addressess.add(address);
       
        postalCode.setNumber(1);
        postalCode.setCity("city");
        when(postalCodeRepository.insertItem(postalCode)).thenReturn(1);
        when(postalCodeRepository.findById(1)).thenReturn(postalCode);
        
       
        postalCodeService.insertPostalCode(postalCode);
        verify(postalCodeRepository, times(1)).insertItem(postalCode);

   
    }
}
