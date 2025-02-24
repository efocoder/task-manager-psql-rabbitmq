package com.clem.taskmanager.shared;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiCode {
    INVALID_CREDENTIALS(401, HttpStatus.UNAUTHORIZED, "Wrong  username or password"),
    INVALID_TOKEN(401, HttpStatus.UNAUTHORIZED, "Invalid access token"),
    SUCCESS(400, HttpStatus.OK, "Request successful"),
    FAILURE(400, HttpStatus.OK, "Request failed"),
    NO_BODY(400, HttpStatus.BAD_REQUEST, "No request body supplied"),
    VALIDATION_ERROR(400, HttpStatus.BAD_REQUEST, "Validation error"),
    ROUTE_NOT_FOUND(404, HttpStatus.NOT_FOUND, "Route not found"),
    RECORD_NOT_FOUND(404, HttpStatus.NOT_FOUND, "Record not found"),
    SERVER_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR, "Sorry, something went wrong. Please try again later"),
    ;

    private final int code;

    private final String message;

    private final HttpStatus httpStatus;

    ApiCode(int code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
