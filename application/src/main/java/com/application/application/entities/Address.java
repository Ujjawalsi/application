package com.application.application.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String country ;
    @Column(name = "pin_code",nullable = false)
    private String pincode;
    @OneToOne
    @JoinColumn(name = "sat_result_id", nullable = false)
    private SatResult satResult;

}
