package com.application.application.controllers;

import com.application.application.constants.AppConstants;
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

    @GetMapping("/{name}")
    public ResponseEntity<SatResultDto> getSatResult(@PathVariable String name){
        SatResultDto result = this.satResultService.getResult(name);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<SatResultDto>> getSatResults(@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER, required = false)Integer pageNumber,
                                                            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
                                                            @RequestParam(value = "sortBY", defaultValue = AppConstants.SORT_BY, required = false)String sortBy,
                                                            @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir){
        List<SatResultDto> allResults = this.satResultService.getAllResults(pageNumber,pageSize, sortBy,sortDir);
        return  new ResponseEntity<>(allResults,HttpStatus.OK);
    }


    @DeleteMapping("/{name}")
    public ResponseEntity<ApiResponse> deleteSatResult(@PathVariable String name){
        this.satResultService.deleteResult(name);
        return new ResponseEntity<>(new ApiResponse("SatResult deleted successfully", true),HttpStatus.OK);
    }

    @GetMapping("/rank/{name}")
    public ResponseEntity<ApiResponse> getRank(@PathVariable String name){
        long rank = this.satResultService.getRank(name);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Rank : "+rank,true), HttpStatus.OK);
    }

}
