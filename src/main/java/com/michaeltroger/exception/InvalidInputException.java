package com.michaeltroger.exception;

public class InvalidInputException extends IllegalArgumentException {

    public InvalidInputException(String userFacingErrorMessage) {
        super(userFacingErrorMessage, null);
    }

}
