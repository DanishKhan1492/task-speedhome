package com.speedhome.propertymanagement.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Muhammad Danish Khan
 * created 21/5/21 - 12:21 PM
 */
@Data
public class UserDto {
    @NotNull(message = "Username must not be null")
    @NotEmpty(message = "Username must not be empty")
    private String username;

    @NotNull(message = "Password must not be null")
    @NotEmpty(message = "Password must not be empty")
    private String password;

}
