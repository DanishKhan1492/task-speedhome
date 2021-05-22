package com.speedhome.propertymanagement.utils.exceptions;

/**
 * @author Muhammad Danish Khan
 * @created 21/5/21 - 3:31 PM
 */
public class NoPropertyFoundException extends Exception {
    private String message;

    public NoPropertyFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
