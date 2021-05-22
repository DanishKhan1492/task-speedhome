package com.speedhome.propertymanagement.dtos;

import lombok.Data;

import javax.persistence.Column;

/**
 * @author Muhammad Danish Khan
 * @created 21/5/21 - 12:34 PM
 */
@Data
public class FloorLevelDto {
    private Integer floorLevelId;
    private String floorLevelName;
}
