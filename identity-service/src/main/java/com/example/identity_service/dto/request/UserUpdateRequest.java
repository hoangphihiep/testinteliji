package com.example.identity_service.dto.request;

import com.example.identity_service.validator.DobContraints;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String password;
    String firstname;
    String lastname;
    LocalDate dob;
    @DobContraints(min = 18, message = "INVALID_DOB")
    List<String> roles;
}
