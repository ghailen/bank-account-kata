package com.margo.kataproject.dto.utils.exception;

import com.margo.kataproject.dto.utils.ApiErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class BusinessException extends Exception {

    @Getter
    @Setter
    private ApiErrorResponse response;
    @Getter
    @Setter
    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public BusinessException(ApiErrorResponse response) {
        super(response.getLabel());
        this.response = response;
    }

    public BusinessException(String message, String detail) {
        super(message);
        this.response = new ApiErrorResponse("", message, detail);
    }

    public BusinessException(String id, String message, String detail) {
        super(message);
        this.response = new ApiErrorResponse(id, message, detail);
    }

    public BusinessException(String message) {
        this(message, message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.response = new ApiErrorResponse("", message, message);
    }

    public BusinessException(Throwable cause) {
        this(cause.getLocalizedMessage(), cause);
    }

    public BusinessException(String message, HttpStatus status) {
        this(message);
        this.status = status;
    }

    public BusinessException(String message, String detail, HttpStatus status) {
        this(message, detail);
        this.status = status;
    }

}
