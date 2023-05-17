package com.application.application.services;

import com.application.application.payloads.AddressDto;

import java.util.List;

public interface AddressService {

    AddressDto createAddress(AddressDto addressDto,String name);

    AddressDto updateAddress(AddressDto addressDto , String name , Long id);

    List<AddressDto> getAllAddresses();

}
