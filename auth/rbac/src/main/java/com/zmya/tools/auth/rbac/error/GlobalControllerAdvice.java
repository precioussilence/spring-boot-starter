package com.zmya.tools.auth.rbac.error;

import com.zmya.tools.auth.rbac.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(Throwable.class)
    public ApiResponse<String> handleGenericException(Exception ex, HttpServletRequest request) {
        log.error("unexpected exception: URI={}", request.getRequestURI(), ex);
        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ApiResponse<String> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        log.warn("access denied: username={},URI={},Message={}",
                SecurityContextHolder.getContext().getAuthentication().getName(),
                request.getRequestURI(),
                ex.getMessage());

        return ApiResponse.error(HttpStatus.FORBIDDEN);
    }

    // JSR Validation: @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrors.put(fieldName, errorMessage);
        });
        log.warn("JSR Validation: URI={},Message={}", request.getRequestURI(), fieldErrors);
        return ApiResponse.error(HttpStatus.BAD_REQUEST);
    }

    // Spring Validation: @Validated
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResponse<String> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            fieldErrors.put(violation.getPropertyPath().toString(), violation.getMessage());
        });
        log.warn("Spring Validation: URI={},Message={}", request.getRequestURI(), fieldErrors);
        return ApiResponse.error(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ApiResponse<String> handleBusinessException(BusinessException ex, HttpServletRequest request) {
        log.warn("business error: URI={},Message={}", request.getRequestURI(), ex.getMessage());
        return ApiResponse.error(ex.getCode(), ex.getMessage());
    }

}
