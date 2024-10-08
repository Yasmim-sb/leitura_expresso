package com.leituraexpresso.challenge.mscustomer.exceptions.build;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.leituraexpresso.challenge.mscustomer.enums.ErrorCodeEnum;
import com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions.NotAllowedException;
import feign.FeignException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class Problem {

    private final int code;

    private final String status;

    private final String message;

    public Problem(HttpStatus httpStatus) {
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = ErrorCodeEnum.SYSTEM_ERROR.getMessage();
    }

    public Problem(String message, HttpStatus status) {
        this.code = status.value();
        this.status = status.name();
        this.message = message;
    }

    public Problem(ErrorCodeEnum errorCode, NotAllowedException exception) {
        this.code = Integer.parseInt(exception.getMessageErrorCode());
        this.status = errorCode.name();
        this.message = errorCode.getMessage();
    }

    public Problem(ConstraintViolationException violationException) {
        this.code = HttpStatus.BAD_REQUEST.value();
        this.status = HttpStatus.BAD_REQUEST.name();
        this.message = formatViolationMessages(violationException.getConstraintViolations());
    }

    public Problem(MethodArgumentNotValidException argumentNotValidException) {
        this.code = HttpStatus.BAD_REQUEST.value();
        this.status = HttpStatus.BAD_REQUEST.name();
        this.message = Arrays.toString(argumentNotValidException.getDetailMessageArguments());
    }

    public Problem(FeignException feignException) {
        this.code = HttpStatus.BAD_REQUEST.value();
        this.status = HttpStatus.BAD_REQUEST.name();
        this.message = feignException.getCause().toString();
    }

    private String formatViolationMessages(Set<ConstraintViolation<?>> violations) {
        return violations.stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining(", "));
    }

    public Problem(ErrorCodeEnum errorCode, HttpStatus httpStatus) {
        this.code = httpStatus.value();
        this.status = errorCode.name();
        this.message = errorCode.getMessage();
    }

    public Problem(ErrorCodeEnum errorCodeEnum, NoResourceFoundException exception) {
        this.code = exception.getStatusCode().value();
        this.status = errorCodeEnum.name();
        this.message = errorCodeEnum.getMessage();
    }

    public Problem(ErrorCodeEnum errorCodeEnum, MethodArgumentTypeMismatchException exception) {
        this.code = Integer.parseInt(exception.getErrorCode());
        this.status = errorCodeEnum.name();
        this.message = errorCodeEnum.getMessage();
    }
}
