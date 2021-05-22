package com.speedhome.propertymanagement.controllers;

import com.speedhome.propertymanagement.dtos.PropertyDto;
import com.speedhome.propertymanagement.dtos.PropertyStatusDto;
import com.speedhome.propertymanagement.dtos.ResponseDto;
import com.speedhome.propertymanagement.dtos.SearchCriteria;
import com.speedhome.propertymanagement.services.PropertyService;
import com.speedhome.propertymanagement.utils.exceptions.NoPropertyFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Muhammad Danish Khan
 * @created 21/5/21 - 4:10 PM
 */

@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;


    @PostMapping("/create")
    public ResponseEntity<PropertyDto> createProperty(@RequestBody @Validated PropertyDto propertyDto) {
        return new ResponseEntity<>(propertyService.createAndUpdateProperty(propertyDto), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<PropertyDto> updateProperty(@RequestBody @Validated PropertyDto propertyDto) {
        return new ResponseEntity<>(propertyService.createAndUpdateProperty(propertyDto), HttpStatus.OK);
    }

    @PutMapping("/approve")
    public ResponseEntity<ResponseDto> updatePropertyStatus(@RequestBody @Validated PropertyStatusDto propertyStatus) throws NoPropertyFoundException {
        propertyService.approveProperty(propertyStatus.getPropertyId(), propertyStatus.getPropertyStatus());
        return new ResponseEntity<>(new ResponseDto(HttpStatus.OK.getReasonPhrase(), "Property Approved"), HttpStatus.OK);
    }

    @PutMapping("/search")
    public ResponseEntity<List<PropertyDto>> searchProperties(@RequestBody SearchCriteria searchCriteria) throws NoPropertyFoundException {
        return new ResponseEntity<>(propertyService.searchProperty(searchCriteria), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PropertyDto>> getAllProperties() {
        return new ResponseEntity<>(propertyService.getAllProperties(), HttpStatus.OK);
    }
}
