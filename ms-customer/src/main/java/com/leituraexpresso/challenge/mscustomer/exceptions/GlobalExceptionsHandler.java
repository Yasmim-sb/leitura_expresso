package com.leituraexpresso.challenge.mscustomer.exceptions;

import com.leituraexpresso.challenge.mscustomer.exceptions.build.Problem;
import com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions.*;
import feign.FeignException;
import jakarta.validation.ConstraintViolationException;
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
    public ResponseEntity<Object> handlerCustomerNotFoundExeption(){
        StandardCustomException customerNotFoundExeption = new CustomerNotFoundException();
        var problem = new Problem(customerNotFoundExeption.getMessageErrorCode(), customerNotFoundExeption.getHttpStatus());
        return ResponseEntity.status(customerNotFoundExeption.getHttpStatus()).body(problem);
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

    @ExceptionHandler(FeignCepNotFoundException.class)
    public ResponseEntity<Object> handlerFeignCepNotFoundException(){
        StandardCustomException feignCepNotFoundException = new FeignCepNotFoundException();
        var problem = new Problem(feignCepNotFoundException.getMessageErrorCode(), feignCepNotFoundException.getHttpStatus());
        return ResponseEntity.status(feignCepNotFoundException.getHttpStatus()).body(problem);
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
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
//        Map<String, String> errorResponse = new HashMap<>();
//        errorResponse.put("error", ex.getMessage());
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(IllegalStateException.class)
//    public ResponseEntity<Map<String, String>> handleIllegalStateException(IllegalStateException ex) {
//        Map<String, String> errorResponse = new HashMap<>();
//        errorResponse.put("error", ex.getMessage());
//        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
//    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Problem> handleBadRequestException(BadRequestException ex) {
        var problem = new Problem(ex);
        return ResponseEntity.status(problem.getCode()).body(problem);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Problem> handleConflictException(ConflictException ex) {
        var problem = new Problem(ex);
        return ResponseEntity.status(problem.getCode()).body(problem);
    }


}
