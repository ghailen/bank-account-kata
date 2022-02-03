package com.margo.kataproject.dto.utils.exception;

import com.margo.kataproject.dto.utils.ApiErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class BusinessRuntimeException extends RuntimeException {

    private ApiErrorResponse response;
    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public BusinessRuntimeException(ApiErrorResponse response) {
        super(response.getLabel());
        this.response = response;
    }

    public BusinessRuntimeException(String message, String detail) {
        super(message);
        this.response = new ApiErrorResponse("", message, detail);
    }

    public BusinessRuntimeException(String id, String message, String detail) {
        super(message);
        this.response = new ApiErrorResponse(id, message, detail);
    }

    public BusinessRuntimeException(String message) {
        this(message, message);
    }

    public BusinessRuntimeException(String message, Throwable cause) {
        super(message, cause);
        this.response = new ApiErrorResponse("", message, message);
    }

    public BusinessRuntimeException(Throwable cause) {
        this(cause.getLocalizedMessage(), cause);
    }

    public BusinessRuntimeException(String message, HttpStatus status) {
        this(message);
        this.status = status;
    }

    public BusinessRuntimeException(String message, String detail, HttpStatus status) {
        this(message, detail);
        this.status = status;
    }

}
