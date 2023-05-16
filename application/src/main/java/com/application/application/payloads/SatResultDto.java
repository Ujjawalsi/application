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
public class SatResultDto {

    private long id ;
    @NotEmpty(message = "Name field never be empty")
    private String name;
    @NotEmpty
    private double satScore;

    private boolean passed ;

}
