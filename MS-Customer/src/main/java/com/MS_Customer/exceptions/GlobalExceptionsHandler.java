package com.MS_Customer.exceptions;

import com.MS_Customer.exceptions.build.Problem;
import com.MS_Customer.exceptions.customExceptions.AddressNotFoundException;
import com.MS_Customer.exceptions.customExceptions.CustomerNotFound;
import com.MS_Customer.exceptions.customExceptions.NotAllowedException;
import com.MS_Customer.exceptions.customExceptions.CustomerNotFoundException;
import com.MS_Customer.exceptions.customExceptions.FeignCepNotFoundException;
import com.MS_Customer.exceptions.customExceptions.StandardCustomException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    @ExceptionHandler(CustomerNotFound.class)
    public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFound ex) {
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
    public ResponseEntity<Object> handlerConstraintViolationException(MethodArgumentNotValidException ex){
        var problem = new Problem(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }
}
