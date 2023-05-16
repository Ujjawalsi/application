package com.application.application.services.impl;

import com.application.application.constants.PassingMarks;
import com.application.application.entities.SatResult;
import com.application.application.payloads.SatResultDto;
import com.application.application.repositories.SatResultRepo;
import com.application.application.services.SatResultService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class SatResultServiceImpl implements SatResultService {

    @Autowired
    private SatResultRepo satResultRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public SatResultDto createResult(SatResultDto satResultDto) {
        SatResult satResult = this.modelMapper.map(satResultDto, SatResult.class);
        if (satResultDto.getSatScore() > PassingMarks.PASSING_MARKS ) {
            satResult.setPassed(true);
        }else{
            satResult.setPassed(false);
        }
        satResult.setSatScore(satResultDto.getSatScore());
        satResult.setName(satResultDto.getName());
        SatResult savedResult = this.satResultRepo.save(satResult);
        return this.modelMapper.map(savedResult,SatResultDto.class);

    }

    @Override
    public SatResultDto updateResult(SatResultDto satResultDto, String name) {

        SatResult satResult = this.satResultRepo.findByName(name);
        satResult.setSatScore(satResultDto.getSatScore());
        if(satResultDto.getSatScore() > PassingMarks.PASSING_MARKS){
            satResult.setPassed(true);
        }else {
            satResult.setPassed(false);
        }
        SatResult updatedSatResult = this.satResultRepo.save(satResult);
        return this.modelMapper.map(updatedSatResult,SatResultDto.class);


    }

    @Override
    public List<SatResultDto> getAllResults() {
        List<SatResult> satResultList = this.satResultRepo.findAll();
        List<SatResultDto> satResultDtos = satResultList.stream().map(satResult -> this.modelMapper.map(satResult,SatResultDto.class)).collect(Collectors.toList());
        return satResultDtos;
    }

    @Override
    public void deleteResult(String name) {
        SatResult satResult = this.satResultRepo.findByName(name);
        this.satResultRepo.delete(satResult);

    }

}
