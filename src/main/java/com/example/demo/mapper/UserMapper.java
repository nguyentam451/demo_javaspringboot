package com.example.demo.mapper;


import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.respone.UserRespone;
import com.example.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

// mapper dùng để map dữ liệu từ User Creation đến User entity
@Mapper (componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserRespone toUserRespone(User user);

    // Annotation mapping target dùng để mapping UserUpdateRequest đến User entity
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
