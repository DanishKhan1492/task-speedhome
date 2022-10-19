package com.speedhome.propertymanagement.services;

import com.speedhome.propertymanagement.dtos.PropertyDto;
import com.speedhome.propertymanagement.dtos.SearchCriteria;
import com.speedhome.propertymanagement.utils.exceptions.NoPropertyFoundException;

import java.util.List;

/**
 * @author Muhammad Danish Khan
 * created 20/5/21 - 5:33 PM
 */
public interface PropertyService {
    PropertyDto createAndUpdateProperty(PropertyDto property);
    void approveProperty(Integer propertyId, Boolean status) throws NoPropertyFoundException;
    List<PropertyDto> searchProperty(SearchCriteria searchCriteria) throws NoPropertyFoundException;
    List<PropertyDto> getAllProperties();
}
