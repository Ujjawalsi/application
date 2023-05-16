package com.application.application.services;

import com.application.application.payloads.SatResultDto;

import java.util.List;

public interface SatResultService {

    SatResultDto createResult(SatResultDto satResultDto);

    SatResultDto updateResult(SatResultDto satResultDto, String name);

    List<SatResultDto> getAllResults();

    void deleteResult(String name);
}
