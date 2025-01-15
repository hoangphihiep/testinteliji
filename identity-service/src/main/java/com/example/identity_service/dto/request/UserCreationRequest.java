package com.example.identity_service.dto.request;

import com.example.identity_service.validator.DobContraints;
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
    @Size(min = 4, message = "USERNAME_INVALID")
   String username;

    @Size(min = 9, message = "INVALID_PASSWORD")
    String password;
    String firstname;
    String lastname;

    @DobContraints(min = 16, message = "INVALID_DOB")
    LocalDate dob;
}
