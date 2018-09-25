
package com.realdolmen.maven.clientrepository.services;

import com.realdolmen.maven.clientrepository.domain.Address;
import com.realdolmen.maven.clientrepository.domain.Person;
import com.realdolmen.maven.clientrepository.exceptions.NoQueryPossibleException;
import com.realdolmen.maven.clientrepository.repositories.PersonRepository;
import java.util.ArrayList;
import java.util.List;


public class PersonService {
    
    private PersonRepository personRepository;
    private AddressService addressService;
    
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }
    
    //prints all persons 
    public List<Person> findAll() throws NoQueryPossibleException {
        List<Person> persons = new ArrayList<>();
        persons = personRepository.findAll();
        return persons;
    }
    
    public Person findById(int id) throws NoQueryPossibleException{
        return personRepository.findById(id);
    }
    
    public boolean removePerson(Person person) throws NoQueryPossibleException {
        //not implemented
        return personRepository.deleteItem(person.getNumber());
   }
  
    public Person insertPerson(Person person) throws NoQueryPossibleException{
        //insert also the default address
        if(person.getAddress().isEmpty()){
            //should not be empty
           throw new IllegalArgumentException("should not be empty");
//            addressService.insertAddress(address);
        }
        Address address = person.getAddress().get(0);
        address.setKlant(person);
        return personRepository.insertItem(person);
    }
    
}
