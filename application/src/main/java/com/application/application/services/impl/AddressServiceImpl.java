package com.application.application.services.impl;

import com.application.application.entities.Address;
import com.application.application.entities.SatResult;
import com.application.application.exceptions.ResourceNotFoundException;
import com.application.application.payloads.AddressDto;
import com.application.application.repositories.AddressRepo;
import com.application.application.repositories.SatResultRepo;
import com.application.application.services.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
   private AddressRepo addressRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SatResultRepo satResultRepo;
    @Override
    public AddressDto createAddress(AddressDto addressDto, String name) {
        SatResult satResult = this.satResultRepo.findByName(name);
        Address address = this.modelMapper.map(addressDto, Address.class);
        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry());
        address.setPincode(addressDto.getPincode());
        address.setSatResult(satResult);
        Address savedAddress= this.addressRepo.save(address);
        return this.modelMapper.map(savedAddress, AddressDto.class);
    }

    @Override
    public AddressDto updateAddress(AddressDto addressDto, String name, Long id) {
        SatResult satResult = this.satResultRepo.findByName(name);
       Address address = this.addressRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Address","Id", id));
       address.setCity(addressDto.getCity());
       address.setCountry(addressDto.getCountry());
       address.setPincode(addressDto.getPincode());
       address.setSatResult(satResult);
        Address updatedAddress = this.addressRepo.save(address);
        return this.modelMapper.map(updatedAddress, AddressDto.class);
    }

    @Override
    public List<AddressDto> getAllAddresses() {
        List<Address> addressList = this.addressRepo.findAll();
        List<AddressDto> addressDtoList = addressList.stream().map(address -> this.modelMapper.map(address, AddressDto.class)).collect(Collectors.toList());
        return addressDtoList;
    }

}
