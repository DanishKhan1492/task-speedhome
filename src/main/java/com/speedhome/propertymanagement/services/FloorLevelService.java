package com.speedhome.propertymanagement.services;

import com.speedhome.propertymanagement.dtos.FloorLevelDto;

import java.util.List;

/**
 * @author Muhammad Danish Khan
 * created 21/5/21 - 12:34 PM
 */
public interface FloorLevelService {
    List<FloorLevelDto> getAllFloors();
}
