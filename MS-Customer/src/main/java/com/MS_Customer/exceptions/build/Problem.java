package com.MS_Customer.exceptions.build;

import com.MS_Customer.enums.ErrorCodeEnum;
import com.MS_Customer.exceptions.customExceptions.BadRequestException;
import com.MS_Customer.exceptions.customExceptions.ConflictException;
import com.MS_Customer.exceptions.customExceptions.NotAllowedException;
import feign.FeignException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@RequiredArgsConstructor
public class Problem {

    private final int code;

    private final String status;

    private final String message;

    public Problem(HttpStatus httpStatus){
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = ErrorCodeEnum.SYSTEM_ERROR.getMessage();
    }

    public Problem(String message, HttpStatus status){
        this.code = status.value();
        this.status = status.name();
        this.message = message;
    }

    public Problem(ErrorCodeEnum errorCode, NotAllowedException exception){
        this.code = Integer.parseInt(exception.getMessageErrorCode());
        this.status = errorCode.name();
        this.message = errorCode.getMessage();
    }

    public Problem(ConstraintViolationException violationException){
        this.code = HttpStatus.BAD_REQUEST.value();
        this.status = HttpStatus.BAD_REQUEST.name();
        this.message = formatViolationMessages(violationException.getConstraintViolations());
    }

    public Problem(MethodArgumentNotValidException argumentNotValidException){
        this.code = HttpStatus.BAD_REQUEST.value();
        this.status = HttpStatus.BAD_REQUEST.name();
        this.message = Arrays.toString(argumentNotValidException.getDetailMessageArguments());
    }

    public Problem(FeignException feignException){
        this.code = HttpStatus.BAD_REQUEST.value();
        this.status = HttpStatus.BAD_REQUEST.name();
        this.message = feignException.getCause().toString();
    }

    public Problem(BadRequestException badRequestException){
        this.code = HttpStatus.BAD_REQUEST.value();
        this.status = HttpStatus.BAD_REQUEST.name();
        this.message = badRequestException.getMessage();
    }

    public Problem(ConflictException conflictException){
        this.code = HttpStatus.CONFLICT.value();
        this.status = HttpStatus.CONFLICT.name();
        this.message = conflictException.getMessage();
    }

    private String formatViolationMessages(Set<ConstraintViolation<?>> violations) {
        return violations.stream()
        .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
        .collect(Collectors.joining(", "));
    }

}
