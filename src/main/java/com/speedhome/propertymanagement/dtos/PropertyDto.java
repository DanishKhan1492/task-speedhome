package com.speedhome.propertymanagement.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author Muhammad Danish Khan
 * created 21/5/21 - 2:06 PM
 */
@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PropertyDto {
    private Integer propertyId;

    @NotNull(message = "Name must not be null")
    private String propertyName;

    @Min(value = 100, message = "Size of Property must not be less than 100 sqft.")
    private Integer buildupSize;

    @Min(value = 1, message = "Property must have at least one bedroom")
    private Integer bedroom;
    @Min(value = 1, message = "Property must have at least one bathroom")
    private Integer bathroom;

    @NotNull(message = "Parking must not be null")
    @Min(value = 0, message = "Value must be 0 or greater")
    private Integer parking;

    private Date creationDate;

    private boolean status;

    @NotNull(message = "Floor Level must not be null")
    @Min(value = 1, message = "Property must have a minimum of floor value 1")
    @Max(value = 3, message = "Property must have a Maximum of floor value 3")
    private Integer floorLevelId;

    @NotNull(message = "Furnishing type must not be null")
    @Min(value = 1, message = "Property must have a minimum of furnishing type value 1")
    @Max(value = 3, message = "Property must have a minimum of furnishing type value 3")
    private Integer furnishingTypeId;

    private Integer createdBy;

    @Valid
    private AddressDto address;

    private FloorLevelDto floorLevel;

    private FurnishingTypesDto furnishing;

}
