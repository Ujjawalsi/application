package com.application.application.services;

import com.application.application.payloads.SatResultDto;

import java.util.List;

public interface SatResultService {

    SatResultDto createResult(SatResultDto satResultDto);

    SatResultDto updateResult(SatResultDto satResultDto, String name);

    SatResultDto getResult(String name);

    List<SatResultDto> getAllResults(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    void deleteResult(String name);

    long getRank(String name);
}
