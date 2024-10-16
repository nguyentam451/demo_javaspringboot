package com.example.demo.controller;


import com.example.demo.dto.request.ApiRespone;
import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.respone.AuthenticationRespone;
import com.example.demo.entity.User;
import com.example.demo.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PutMapping
    ApiRespone<AuthenticationRespone> login(@RequestBody @Valid AuthenticationRequest request){
//        ApiRespone apiRespone = new ApiRespone();
//        apiRespone.setResult(authenticationService.login(request));
//        return apiRespone;

        boolean result = authenticationService.login(request);

        return ApiRespone.<AuthenticationRespone>builder()
                .result(AuthenticationRespone.builder()
                        .loginSucess(result) //set biến loginSucess ở class AuthenticationRespone = true/false
                        .build())
                .build();
    }

}
