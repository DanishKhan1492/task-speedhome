package com.speedhome.propertymanagement.dtos;

import lombok.Data;

/**
 * @author Muhammad Danish Khan
 * @created 21/5/21 - 12:23 PM
 */
@Data
public class AuthResponse {
    private Integer userId;
    private String token;

    public AuthResponse(Integer userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
