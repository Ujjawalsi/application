package com.application.application.services.impl;

import com.application.application.constants.PassingMarks;
import com.application.application.constants.RankValue;
import com.application.application.entities.SatResult;
import com.application.application.payloads.SatResultDto;
import com.application.application.payloads.SatResultResponse;
import com.application.application.repositories.SatResultRepo;
import com.application.application.services.SatResultService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public SatResultDto getResult(String name) {
        SatResult satResult = this.satResultRepo.findByName(name);
        return this.modelMapper.map(satResult,SatResultDto.class);
    }

    @Override
    public List<SatResultDto> getAllResults(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort =(sortDir.equalsIgnoreCase("asc"))? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber,pageSize, sort);
        Page<SatResult> pageSatResultList = this.satResultRepo.findAll(pageable);
        List<SatResult> satResultList = pageSatResultList.getContent();
        List<SatResultDto> satResultDtos = satResultList.stream().map(satResult -> this.modelMapper.map(satResult,SatResultDto.class)).collect(Collectors.toList());
        SatResultResponse postResponse = new SatResultResponse();
        postResponse.setContent(satResultDtos);
        postResponse.setPageNumber(pageSatResultList.getNumber());
        postResponse.setPageSize(pageSatResultList.getSize());
        postResponse.setTotalElements(pageSatResultList.getTotalElements());
        postResponse.setTotalPages(pageSatResultList.getTotalPages());
        postResponse.setLastPage(pageSatResultList.isLast());
        return satResultDtos;
    }

    @Override
    public void deleteResult(String name) {
        SatResult satResult = this.satResultRepo.findByName(name);
        this.satResultRepo.delete(satResult);

    }

    @Override
    public long getRank(String name) {
        SatResult satResult = this.satResultRepo.findByName(name);
        double satScore = satResult.getSatScore();
      Long count =  this.satResultRepo.getCountMoreThanSatScore(satScore);
        long rank = count + RankValue.RANK_VALUE;
        return rank;
    }

}
