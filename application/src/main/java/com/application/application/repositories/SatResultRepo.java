package com.application.application.repositories;

import com.application.application.entities.SatResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SatResultRepo extends JpaRepository<SatResult,String> {

    @Query(value = "select * from sat_results r where r.name = ?1",nativeQuery = true)
    SatResult findByName(String name);
    @Query(value = "select count(*) from sat_results r where r.sat_score > ?1", nativeQuery = true)
    Long getCountMoreThanSatScore(double satScore);
}
