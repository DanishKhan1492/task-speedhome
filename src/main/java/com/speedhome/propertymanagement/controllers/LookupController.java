package com.speedhome.propertymanagement.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.speedhome.propertymanagement.dtos.FloorLevelDto;
import com.speedhome.propertymanagement.dtos.FurnishingTypesDto;
import com.speedhome.propertymanagement.dtos.PropertyDto;
import com.speedhome.propertymanagement.services.FloorLevelService;
import com.speedhome.propertymanagement.services.FurnishingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Muhammad Danish Khan
 * @created 21/5/21 - 4:07 PM
 */
@RestController
@RequestMapping("/lookup")
public class LookupController {

    private FloorLevelService floorLevelService;

    private FurnishingService furnishingService;

    public LookupController(FloorLevelService floorLevelService, FurnishingService furnishingService) {
        this.floorLevelService = floorLevelService;
        this.furnishingService = furnishingService;
    }

    @GetMapping("/floors")
    public ResponseEntity<List<FloorLevelDto>> getAllFloorLevels() throws JsonProcessingException {
        return new ResponseEntity<>(floorLevelService.getAllFloors(), HttpStatus.OK);
    }

    @GetMapping("/furnishingtypes")
    public ResponseEntity<List<FurnishingTypesDto>> getAllFurnishingTypes() {
        return new ResponseEntity<>(furnishingService.getAllFurnishingTypes(), HttpStatus.OK);
    }
}
