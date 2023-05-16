package com.application.application.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private long id ;
    @NotEmpty(message = "City field never be empty")
    private String city;
    @NotEmpty(message = "Country filed never be empty")
    private String country ;
    @NotEmpty(message = "Pincode field never be empty")
    private String pincode;

}
