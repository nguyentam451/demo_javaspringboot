package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
// class này dùng để trả API về theo cú pháp
@JsonInclude(JsonInclude.Include.NON_NULL) // để kết quả trả về ko có trường NULL
public class ApiRespone <T>{
    int code = 1000; // thanh cong
    String message;
    T result;
}
