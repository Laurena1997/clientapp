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

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    //Insert Address

    public Address insertAddress(Address address) throws NoQueryPossibleException {
        return addressRepository.insertItem(address);
    }

    //address -> reporsirtoy.findbyid
    //addres.getPostalCode.getPostalCode()
    //postalCodeService.findById(address.getPostalCode.getPostalCode())
    //address.setPostalCode(PostalCode)
    //return address  
    public Address findAddressById(int id) throws NoQueryPossibleException {
        return addressRepository.findById(id);

    }
    //findAllAddressForClientPerson(int id)
    //zoek een adres die van een bepaald persoon    
    //findAllAddressFromClientFirm(int id)

//findAllAddressForClientPerson(int id)
    public List<Address> findAllAddressForClientPerson(int id) throws NoQueryPossibleException {
        /* List<Address> addresses = new ArrayList<>();
        List<Address> allAddresses = addressRepository.findAll();
        for (Address a : allAddresses) {
            addresses.add(a);
        }
        return addresses;
        ^waarschijnlijk fout^
        
        nogni juist, je moet address en person nog linken, hoe is de vraag*/
        return personService.findById(id).getAddress();

    }
    //zoek een adres die van een bepaald persoon

    //findAllAddressFromClientFirm(int id)
    public ArrayList<Address> findAllAddressFromClientFirm(int id) throws NoQueryPossibleException {
        return firmService.findById(id).getAddress();

    }
}
