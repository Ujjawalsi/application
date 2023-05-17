package com.application.application.controllers;

import com.application.application.payloads.AddressDto;
import com.application.application.services.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("/{name}")
    public ResponseEntity<AddressDto> createSatResult(@Valid @RequestBody AddressDto addressDto, @PathVariable String name){
        AddressDto address = this.addressService.createAddress(addressDto,name);
        return new ResponseEntity<>(addressDto, HttpStatus.CREATED);
    }

    @PutMapping("/{name}/{id}")
    public  ResponseEntity<AddressDto> updateSatResult(@Valid @RequestBody AddressDto addressDto , @PathVariable String name,
                                                         @PathVariable Long id){

        AddressDto updateAddress = this.addressService.updateAddress(addressDto,name,id);
        return  new ResponseEntity<>(updateAddress,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<AddressDto>> getSatResults(){
        List<AddressDto> allAddresses = this.addressService.getAllAddresses();
        return  new ResponseEntity<>(allAddresses,HttpStatus.OK);
    }

}
