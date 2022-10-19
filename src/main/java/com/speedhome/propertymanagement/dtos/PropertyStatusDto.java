package com.speedhome.propertymanagement.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Muhammad Danish Khan
 * created 21/5/21 - 7:40 PM
 */
@Data
@NoArgsConstructor
public class PropertyStatusDto {
    @NotNull(message = "Property ID must not be null")
    private Integer propertyId;
    @NotNull(message = "Status must not be null")
    private Boolean propertyStatus;
}
