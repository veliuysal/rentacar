package com.bilgeadam.rentacar.services;

import com.bilgeadam.rentacar.dto.model.AddressDTO;
import com.bilgeadam.rentacar.entities.Address;
import com.bilgeadam.rentacar.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address saveAddress(AddressDTO dto) {
        Address address = new Address();
        address.setAddressLine(dto.getAddressLine());
        address.setCity(address.getCity());
        address.setCountry(address.getCountry());
        address.setDistrict(address.getDistrict());
        address.setPostCode(address.getPostCode());
        return addressRepository.save(address);
    }
}
