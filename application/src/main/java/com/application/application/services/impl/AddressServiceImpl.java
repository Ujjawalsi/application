package com.application.application.services.impl;

import com.application.application.entities.Address;
import com.application.application.payloads.AddressDto;
import com.application.application.repositories.AddressRepo;
import com.application.application.services.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
   private AddressRepo addressRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public AddressDto createAddress(AddressDto addressDto) {
        Address address = this.modelMapper.map(addressDto, Address.class);
        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry());
        address.setPincode(addressDto.getPincode());
        Address savedAddress= this.addressRepo.save(address);
        return this.modelMapper.map(savedAddress, AddressDto.class);
    }

    @Override
    public AddressDto updateAddress(AddressDto addressDto, String name) {
        return null;
    }

    @Override
    public List<AddressDto> getAllAddresses() {
        return null;
    }

    @Override
    public void deletedAddress(String name) {

    }
}
