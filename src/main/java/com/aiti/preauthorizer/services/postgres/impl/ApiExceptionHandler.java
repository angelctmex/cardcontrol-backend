package com.aiti.preauthorizer.services.postgres.impl;

import com.aiti.preauthorizer.controller.CardControlController;
import com.aiti.preauthorizer.controller.CardController;
import com.aiti.preauthorizer.dto.exceptions.GenericErrorMobileResponse;
import com.aiti.preauthorizer.dto.exceptions.RequestBadException;
import com.aiti.preauthorizer.dto.exceptions.RequestConflictException;
import com.aiti.preauthorizer.dto.exceptions.ResourceNotFoundException;
import com.aiti.preauthorizer.utils.ConstantsRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(assignableTypes = { CardControlController.class, CardController.class } )
public class ApiExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /** Representa status 412 Resource Not Found **/
    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(final ResourceNotFoundException exception, final HttpServletRequest request) {

        GenericErrorMobileResponse error = new GenericErrorMobileResponse();
        error.setStatus(HttpStatus.PRECONDITION_FAILED + "-" + HttpStatus.PRECONDITION_FAILED.getReasonPhrase());
        error.setMessage(exception.getMessage());
        error.setCode(exception.getCodeError());
        log.debug("Error _: " + error.getCode() + " Request Uri _:" + request.getRequestURI());

        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(error);
    }

    /** Representa status 400 Resource Bad Request **/
    @ResponseBody
    @ExceptionHandler({RequestBadException.class})
    public ResponseEntity<?> handleResourceBadRequest(final RequestBadException exception, final HttpServletRequest request) {

        GenericErrorMobileResponse error = new GenericErrorMobileResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value() + " " + HttpStatus.BAD_REQUEST.getReasonPhrase());
        error.setMessage(exception.getMessage());
        error.setCode(exception.getCodeError());
        log.debug("Error _: " + error.getCode() + " Request Uri _:" + request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /** Representa status 409 Resource Conflict (Un recurso existente) **/
    @ResponseBody
    @ExceptionHandler({RequestConflictException.class})
    public ResponseEntity<?> handleResourceConflict(final RequestConflictException exception, final HttpServletRequest request) {

        GenericErrorMobileResponse error = new GenericErrorMobileResponse();
        error.setStatus(HttpStatus.CONFLICT.value() + " " + HttpStatus.CONFLICT.getReasonPhrase());
        error.setMessage(exception.getMessage());
        error.setCode(exception.getCodeError());
        log.debug("Error _: " + error.getCode() + " Request Uri _:" + request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    /** Representa status 500 Internal Server Error (Todos los Errores no controlados) **/
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleExceptionErrorInternal(final Exception exception, final HttpServletRequest request) {

        GenericErrorMobileResponse error = new GenericErrorMobileResponse();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value() + " " + HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        error.setMessage(exception.toString());
        error.setCode(getCodeErrorResponse(request.getRequestURI(), request.getMethod()));
        log.debug("Error _: " + error.getCode() + " Request Uri _:" + request.getRequestURI() + " Method_:" + request.getMethod());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    private String getCodeErrorResponse(String uri, String method) {
        String urlrequest = getUltimoElementoUri(uri);
        String code = "";

        switch (urlrequest) {
            case "cards":
                code = ConstantsRest.GET_USER_INTERNAL_SERVER_ERROR;
                break;
            case "seed":
                code = ConstantsRest.GET_SEED_INTERNAL_SERVER_ERROR;
                break;
            case "channels":
                if(method.equals("GET"))
                    code = ConstantsRest.GET_CARD_INTERNAL_SERVER_ERROR;
                else if (method.equals("POST"))
                    code = ConstantsRest.POST_CARD_INTERNAL_SERVER_ERROR;
                else if (method.equals("PUT"))
                    code = ConstantsRest.PUT_CARD_INTERNAL_SERVER_ERROR;
                break;
            case "amountlimits":
                if(method.equals("GET"))
                    code = ConstantsRest.GET_CHNN_INTERNAL_SERVER_ERROR;
                else if (method.equals("POST"))
                    code = ConstantsRest.POST_CHNN_INTERNAL_SERVER_ERROR;
                else if (method.equals("PUT"))
                    code = ConstantsRest.PUT_CHNN_INTERNAL_SERVER_ERROR;
                break;
            case "movementslimits":
                if(method.equals("GET"))
                    code = ConstantsRest.GET_MOVE_INTERNAL_SERVER_ERROR;
                else if (method.equals("POST"))
                    code = ConstantsRest.POST_MOVE_INTERNAL_SERVER_ERROR;
                else if (method.equals("PUT"))
                    code = ConstantsRest.PUT_MOVE_INTERNAL_SERVER_ERROR;
                break;
            case "enrollment":
                if(method.equals("GET"))
                    code = ConstantsRest.GET_ENROLL_INTERNAL_SERVER_ERROR;
                else if (method.equals("POST"))
                    code = ConstantsRest.POST_ENROLL_INTERNAL_SERVER_ERROR;
                else if (method.equals("PUT"))
                    code = ConstantsRest.PUT_ENROLL_INTERNAL_SERVER_ERROR;
                break;
            case "enabled":
                code = ConstantsRest.PUT_STATUS_INTERNAL_SERVER_ERROR;
                break;
        }
        return code;
    }

    private String getUltimoElementoUri(String uri) {
        String[] split = uri.split("/");
        int cantidasSplit = split.length;
        String ultimoElementoSplit = split[cantidasSplit-1];
        log.debug("split : " + ultimoElementoSplit);
        return ultimoElementoSplit;
    }
}
