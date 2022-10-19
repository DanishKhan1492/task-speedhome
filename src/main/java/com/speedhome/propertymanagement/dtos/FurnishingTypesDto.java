package com.speedhome.propertymanagement.dtos;

import lombok.Data;

import javax.persistence.Column;

/**
 * @author Muhammad Danish Khan
 * created 21/5/21 - 12:49 PM
 */
@Data
public class FurnishingTypesDto {
    private Integer furnishingId;
    private String furnishingType;
}
