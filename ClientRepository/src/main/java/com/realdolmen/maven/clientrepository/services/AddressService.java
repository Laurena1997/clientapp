package com.realdolmen.maven.clientrepository.services;

import com.realdolmen.maven.clientrepository.domain.Address;
import com.realdolmen.maven.clientrepository.exceptions.NoQueryPossibleException;
import com.realdolmen.maven.clientrepository.repositories.AddressRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SDOAX36
 */
public class AddressService {

    private AddressRepository addressRepository;
    private PersonService personService;
    private FirmService firmService;
    private PostalCodeService postalCodeService;

    public AddressService() {
    }

    public AddressService(AddressRepository addressRepository,
                          PostalCodeService postalCodeService,
                          PersonService personService,
                          FirmService firmService) {
        this.addressRepository = addressRepository;
        this.firmService = firmService;
        this.personService = personService;
        this.postalCodeService = postalCodeService;
    }

    public Address insertAddress(Address address) throws NoQueryPossibleException {
        int i = addressRepository.insertItem(address);
        return findAddressById(i);
    }
  
    public Address findAddressById(int id) throws NoQueryPossibleException {
        Address address = addressRepository.findById(id);
        address.setPostalCode(postalCodeService.findById(address.getPostalCode().getNumber()));
        return address;
    }

    public List<Address> findAllAddressForClientPerson(int id) throws NoQueryPossibleException {
        return personService.findById(id).getAddress();

    }
    public ArrayList<Address> findAllAddressFromClientFirm(int id) throws NoQueryPossibleException {
        return firmService.findById(id).getAddress();

    }

    public List<Address> findAddressForPerson(int id) throws NoQueryPossibleException{
        return addressRepository.findAddressForPersonId(id);
    }

    public List<Address> findAddressForFirm(int id){
        //find address for firm nog in repo maken
        //return addressRepository.findAddressForFirm(id);
        return null;
    }
}
