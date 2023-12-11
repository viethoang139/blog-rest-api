package com.leviethoang.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    @NotEmpty(message = "name can not be empty")
    private String name;
    @NotEmpty(message = "username can not be empty")
    @Size(min = 6 , max = 10, message = "The length of the username should be between 6 and 10")
    private String username;
    @NotEmpty(message = "password can not be empty")
    @Size(min = 5, max = 10, message = "The length of the password should be between 5 and 10")
    private String password;
    @NotEmpty(message = "address can not be empty")
    private String address;
    @NotEmpty(message = "phoneNumber can not be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "phoneNumber must be 10 digits")
    private String phoneNumber;
}
