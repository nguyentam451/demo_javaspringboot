package com.example.demo.controller;

import com.example.demo.dto.request.ApiRespone;
import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.respone.UserRespone;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping("/{userID}")
    UserRespone updateUser(@PathVariable String userID, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userID, request);
    }

    @DeleteMapping("/{userID}")
    String deleteUser(@PathVariable String userID){
        userService.deleteUser(userID);
        return "User has been delete";
    }
}
