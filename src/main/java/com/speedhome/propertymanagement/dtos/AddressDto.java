package com.speedhome.propertymanagement.dtos;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
 * @author Muhammad Danish Khan
 * @created 21/5/21 - 2:07 PM
 */
@Data
@ToString
public class AddressDto {
    private Integer addressId;
    @NotNull(message = "Address must not be null")
    private String fullAddress;
    private Integer postCode;
}
