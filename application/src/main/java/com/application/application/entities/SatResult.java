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
@Table(name = "sat_results")
public class SatResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @Column(nullable = false , unique = true)
    private String name;
    @Column(name = "sat_score", nullable = false)
    private double satScore;
    private boolean passed ;

    @OneToOne(mappedBy = "satResult",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Address address;
}
