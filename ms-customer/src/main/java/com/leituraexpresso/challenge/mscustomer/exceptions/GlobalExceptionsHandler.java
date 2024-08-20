package com.leituraexpresso.challenge.mscustomer.exceptions;

import com.leituraexpresso.challenge.mscustomer.exceptions.build.Problem;
import com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions.*;
import feign.FeignException;
import jakarta.validation.ConstraintViolationException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> handlerCustomerNotFoundException(){
        StandardCustomException customerNotFoundException = new CustomerNotFoundException();
        var problem = new Problem(customerNotFoundException.getMessageErrorCode(), customerNotFoundException.getHttpStatus());
        return ResponseEntity.status(customerNotFoundException.getHttpStatus()).body(problem);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<Object> handlerAddressNotFoundException(){
        StandardCustomException addressNotFoundException = new AddressNotFoundException();
        var problem = new Problem(addressNotFoundException.getMessageErrorCode(), addressNotFoundException.getHttpStatus());
        return ResponseEntity.status(addressNotFoundException.getHttpStatus()).body(problem);
    }

    @ExceptionHandler(NotAllowedException.class)
    public ResponseEntity<Object> handleNotAllowedException(NotAllowedException ex) {
        Problem problem = new Problem(ex.getMessageErrorCode(), ex.getHttpStatus());
        return ResponseEntity.status(ex.getHttpStatus()).body(problem);
    }

    @ExceptionHandler(NotPossibleToAlterAddressException.class)
    public ResponseEntity<Object> handleNotPossibleAlterAddressException(NotPossibleToAlterAddressException ex) {
        Problem problem = new Problem(ex.getMessageErrorCode(), ex.getHttpStatus());
        return ResponseEntity.status(ex.getHttpStatus()).body(problem);
    }

    @ExceptionHandler(NotAllowedToChangePasswordFromOtherCustomerException.class)
    public ResponseEntity<Object> handlerNotAllowedToChangePasswordFromOtherCustomerException(){
        StandardCustomException notAllowed = new NotAllowedToChangePasswordFromOtherCustomerException();
        var problem = new Problem(notAllowed.getMessageErrorCode(), notAllowed.getHttpStatus());
        return ResponseEntity.status(notAllowed.getHttpStatus()).body(problem);
    }

    @ExceptionHandler(EmailAddressAlreadyExistsException.class)
    public ResponseEntity<Object> handlerEmailAddressAlreadyExistsException(){
        StandardCustomException EmailExists = new EmailAddressAlreadyExistsException();
        var problem = new Problem(EmailExists.getMessageErrorCode(), EmailExists.getHttpStatus());
        return ResponseEntity.status(EmailExists.getHttpStatus()).body(problem);
    }

    @ExceptionHandler(CPFAlreadyInUseException.class)
    public ResponseEntity<Object> handlerCPFAlreadyInUseException(){
        StandardCustomException CPFExists = new CPFAlreadyInUseException();
        var problem = new Problem(CPFExists.getMessageErrorCode(), CPFExists.getHttpStatus());
        return ResponseEntity.status(CPFExists.getHttpStatus()).body(problem);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handlerConstraintViolationException(ConstraintViolationException ex){
        var problem = new Problem(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        var problem = new Problem(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }

    @ExceptionHandler(FeignException.class)
    public final ResponseEntity<Object> handlerFeignException(FeignException e){
        var problem = new Problem(e);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(problem);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> handleIllegalStateException(IllegalStateException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, String>> handleBadRequestException(BadRequestException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Map<String, String>> handleConflictException(ConflictException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
