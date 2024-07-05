package com.MS_Customer.exceptions;

import com.MS_Customer.exceptions.build.Problem;
import com.MS_Customer.exceptions.customExceptions.AddressNotFoundException;
import com.MS_Customer.exceptions.customExceptions.CustomerNotFound;
import com.MS_Customer.exceptions.customExceptions.NotAllowedException;
import com.MS_Customer.exceptions.customExceptions.StandardCustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler {

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

}
