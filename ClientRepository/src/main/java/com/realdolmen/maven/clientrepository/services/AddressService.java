package com.realdolmen.maven.clientrepository.services;

import com.realdolmen.maven.clientrepository.exceptions.NoQueryPossibleException;
import com.realdolmen.maven.clientrepository.repositories.AddressRepository;

/**
 *
 * @author SDOAX36
 */
public class AddressService {

    private AddressRepository addressRepository;

    public AddressService() {
    }
    

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    //Insert Address

    //findAddressById
    //address -> reporsirtoy.findbyid
    //addres.getPostalCode.getPostalCode()
    //postalCodeService.findById(address.getPostalCode.getPostalCode())
    //address.setPostalCode(PostalCode)
    //return address  
    public void findAddressById(int id) throws NoQueryPossibleException {
        //return addressRepository.findById(id);

    }
    //findAllAddressForClientPerson(int id)
    //zoek een adres die van een bepaald persoon    
    //findAllAddressFromClientFirm(int id)

//findAllAddressForClientPerson(int id)
    public void findAllAddressForClientPerson(int id) {

    }
    //zoek een adres die van een bepaald persoon

    //findAllAddressFromClientFirm(int id)
    public void findAllAddressFromClientFirm(int id) {

    }
}
