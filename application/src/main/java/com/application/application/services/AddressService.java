package com.application.application.services;

import com.application.application.payloads.AddressDto;

import java.util.List;

public interface AddressService {

    AddressDto createAddress(AddressDto addressDto);

    AddressDto updateAddress(AddressDto addressDto , String name);

    List<AddressDto> getAllAddresses();

    void deletedAddress(String name);
}
