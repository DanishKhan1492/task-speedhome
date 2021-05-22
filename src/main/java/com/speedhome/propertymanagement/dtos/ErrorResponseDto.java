package com.speedhome.propertymanagement.dtos;

import lombok.Data;
import lombok.Getter;

/**
 * @author Muhammad Danish Khan
 * @created 21/5/21 - 12:15 PM
 */
@Data
public class ErrorResponseDto {
    private String status;
    private String message;

    public ErrorResponseDto(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
