package com.margo.kataproject.exception;

import com.margo.kataproject.dto.utils.ApiErrorResponse;
import com.margo.kataproject.dto.utils.exception.BusinessException;
import com.margo.kataproject.dto.utils.exception.TechnicalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Rest exception handler.
 */
@ControllerAdvice
public class RestExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * Instantiates a new Rest exception handler.
     */
    public RestExceptionHandler() {

    }

    /**
     * On constraint validation exception list.
     *
     * @param e the e
     * @return the list
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    List<ApiErrorResponse> onConstraintValidationException(ConstraintViolationException e) {
        List<ApiErrorResponse> errors =
                e.getConstraintViolations().stream().map(
                        violation -> new ApiErrorResponse(
                                "ERR_VALIDATION",
                                violation.toString(),
                                violation.getMessage()
                        )
                ).collect(Collectors.toList());
        logger.error("[422]" + errors);
        return errors;
    }

    /**
     * On method argument not valid exception list.
     *
     * @param e the e
     * @return the list
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    List<ApiErrorResponse> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ApiErrorResponse> errors =
                e.getBindingResult().getAllErrors().stream().map(
                        objectError -> new ApiErrorResponse(
                                "ERR_VALIDATION",
                                objectError instanceof FieldError ? ((FieldError) objectError).getField() : "",
                                objectError.getDefaultMessage()
                        )
                ).collect(Collectors.toList());
        logger.error("[422]" + errors);
        return errors;
    }

    /**
     * Handle message not readable exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleMessageNotReadableException(
            HttpMessageNotReadableException ex
    ) {
        String id = "ERR_VALIDATION";
        ApiErrorResponse body = new ApiErrorResponse(
                id,
                "Erreur de validation globale",
                "Erreur lors du parsing de requete"
        );
        ResponseEntity<ApiErrorResponse> response =
                ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(body);
        logException(ex, body);
        return response;
    }

    /**
     * Handle business exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorResponse> handleBusinessException(
            BusinessException ex,
            HttpServletRequest request
    ) {
        ApiErrorResponse body = ex.getResponse();
        String id = String.format("ERR_%s", getAPIName(request));
        if (ex.getResponse().getId() != null && !ex.getResponse().getId().equals(""))
            id = ex.getResponse().getId();
        body.setId(id);
        ResponseEntity<ApiErrorResponse> response =
                ResponseEntity.status(ex.getStatus()).body(body);
        logException(ex, body);
        return response;
    }

    /**
     * Handle technical exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<ApiErrorResponse> handleTechnicalException(
            TechnicalException ex,
            HttpServletRequest request
    ) {
        String id = String.format("ERR_%s", getAPIName(request));
        ApiErrorResponse body = new ApiErrorResponse(
                id,
                "Erreur technique",
                ex.getLocalizedMessage()
        );
        ResponseEntity<ApiErrorResponse> response =
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        logException(ex, body);
        return response;
    }


    /**
     * Handle exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(
            Exception ex
    ) {
        String id = "ERR_APPLICATION";
        ApiErrorResponse body = new ApiErrorResponse(
                id,
                "Erreur interne du serveur: " + ex.getClass(),
                ex.getLocalizedMessage()
        );
        ResponseEntity<ApiErrorResponse> response =
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        logException(ex, body);
        return response;
    }


    private String getAPIName(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String apiName = requestURI;
        if (requestURI.contains("accounting")) {
            apiName = "ACCOUNT";
        }
        return apiName;
    }

    private void logException(Exception ex, ApiErrorResponse errorResponse) {
        logger.error(String.format(
                "Exception ID: %s , body: %s, API response detail: %s, occurred in: %s",
                errorResponse.getId(),
                ex,
                errorResponse.getDetail(),
                ex.getStackTrace().length > 0 ?
                        ex.getStackTrace()[0].getClassName() : "Unknown class"
        ));
    }
}
