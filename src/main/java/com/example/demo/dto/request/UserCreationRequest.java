package com.example.demo.dto.request;

import com.example.demo.exception.ErrorCode;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @Size(min = 3, message = "USERNAME_INVALID")
    String username;

    @Size(min = 8, message = "PASWORD_INVALID")
    String password;

    String firstName;
    String lastName;
    LocalDate dateOfBirth;

}
