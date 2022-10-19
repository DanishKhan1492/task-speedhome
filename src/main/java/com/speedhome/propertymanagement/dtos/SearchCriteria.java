package com.speedhome.propertymanagement.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Muhammad Danish Khan
 * created 21/5/21 - 3:38 PM
 */

@Data
public class SearchCriteria {
    private String propertyName;
    private String address;
    private Integer bathroom;
    private Integer bedroom;
    private Integer floorLevelId;
    private Integer furnishingTypeId;
    private Integer buildUpSize;

    @NotNull(message = "page size must not be null")
    private Integer pageSize;
    @NotNull(message = "pageNumber must not be null")
    private Integer pageNumber;
}
