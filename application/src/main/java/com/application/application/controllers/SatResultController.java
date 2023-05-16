package com.application.application.controllers;

import com.application.application.payloads.ApiResponse;
import com.application.application.payloads.SatResultDto;
import com.application.application.services.SatResultService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class SatResultController {

    @Autowired
    private SatResultService satResultService;


    @PostMapping("/")
    public ResponseEntity<SatResultDto> createSatResult(@Valid @RequestBody SatResultDto satResultDto){
        SatResultDto resultDto = this.satResultService.createResult(satResultDto);
        return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
    }

    @PutMapping("/{name}")
    public  ResponseEntity<SatResultDto> updateSatResult(@Valid @RequestBody SatResultDto satResultDto , @PathVariable String name){

        SatResultDto updateResult = this.satResultService.updateResult(satResultDto, name);
        return  new ResponseEntity<>(updateResult,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<SatResultDto>> getSatResults(){
        List<SatResultDto> allResults = this.satResultService.getAllResults();
        return  new ResponseEntity<>(allResults,HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<ApiResponse> deleteSatResult(@PathVariable String name){
        this.satResultService.deleteResult(name);
        return new ResponseEntity<>(new ApiResponse("SatResult deleted successfully", true),HttpStatus.OK);
    }

}
