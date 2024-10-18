package com.example.demo.exception;

import com.example.demo.dto.request.ApiRespone;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiRespone> handlingRuntimeException(RuntimeException exception) {
        ApiRespone apiRespone = new ApiRespone();

        String code = exception.getMessage();

        System.out.println("code doan nay la: " + code);

        apiRespone.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiRespone.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());

        return ResponseEntity.badRequest().body(apiRespone);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiRespone> handlingAppException(AppException exception) {

        ErrorCode errorCode = exception.getErrorCode();

        ApiRespone apiRespone = new ApiRespone();

        apiRespone.setCode(errorCode.getCode());
        apiRespone.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiRespone);
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiRespone> handlingValidation(MethodArgumentNotValidException exception) {

        ApiRespone apiRespone = new ApiRespone();

        String enumkey = exception.getFieldError().getDefaultMessage();
        // Lúc này enumkey là dãy message USERNAME_INVALID từ class UserCreationRequest

        // lấy key là message từ enum error code
        ErrorCode errorCode = ErrorCode.CHARACTERS_INVALID;

        try {
            errorCode = ErrorCode.valueOf(enumkey);
        } catch (IllegalArgumentException e) {

        }
        apiRespone.setCode(errorCode.getCode());
        apiRespone.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiRespone);
    }

    @ExceptionHandler (value = MissingPathVariableException.class)
    ResponseEntity<ApiRespone> handlingPathVariableException(MissingPathVariableException exception){

        ApiRespone apiRespone = new ApiRespone();

        String message = exception.getMessage();

        System.out.println("message ở đoạn này là: " + message); // lỗi khi sai path ở controller

        ErrorCode errorCode = ErrorCode.PATH_VARIABLE_ERROR;

        apiRespone.setCode(errorCode.getCode());
        apiRespone.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiRespone);
    }
}
