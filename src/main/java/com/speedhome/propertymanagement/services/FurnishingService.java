package com.speedhome.propertymanagement.services;

import com.speedhome.propertymanagement.dtos.FurnishingTypesDto;

import java.util.List;

/**
 * @author Muhammad Danish Khan
 * @created 21/5/21 - 12:49 PM
 */
public interface FurnishingService {
    List<FurnishingTypesDto> getAllFurnishingTypes();
}
