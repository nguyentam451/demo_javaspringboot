package com.example.demo.controller;

import com.example.demo.dto.request.ApiRespone;
import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.respone.AuthenticationRespone;
import com.example.demo.dto.respone.UserRespone;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    // Cách ko chuẩn hóa theo API Respone
//    @PostMapping
//    User createUser(@RequestBody @Valid UserCreationRequest request){
//       return userService.creatUser(request);
//    }

    @PostMapping
    ApiRespone<User> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiRespone apiRespone = new ApiRespone();
        apiRespone.setResult(userService.creatUser(request));
        return apiRespone;
    }

    @GetMapping
    List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{userID}")
    UserRespone getUser(@PathVariable String userID){
        return userService.getUser(userID);
    }

//    @PutMapping("/{userID}")
//    UserRespone updateUser(@PathVariable String userID, @RequestBody UserUpdateRequest request){
//        return userService.updateUser(userID, request);
//    }

    @PutMapping("/{userID}")
    ApiRespone<UserRespone> updateUser2(@PathVariable String userID, @RequestBody UserUpdateRequest request){
        userService.updateUser(userID, request);

        // mã hóa password hiển thị trên API khi update đến user
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        return ApiRespone.<UserRespone>builder()
                .result(UserRespone.builder()
                        .password(passwordEncoder.encode(request.getPassword()))
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .dateOfBirth(request.getDateOfBirth())
                        .build())
                .build();
    }

    @DeleteMapping("/{userID}")
    String deleteUser(@PathVariable String userID){
        userService.deleteUser(userID);
        return "User has been deleted";
    }
}
