package com.speedhome.propertymanagement.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Muhammad Danish Khan
 * created 21/5/21 - 7:34 PM
 */
@Data
@NoArgsConstructor
public class ResponseDto {
    private String status;
    private String message;

    public ResponseDto(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
