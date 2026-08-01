package com.phen.mbanking.features.auth.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

        @NotBlank(message = "Phone number is required")
        @Size(min = 9,max = 10,message = "Phone number must be between 9 to 10 digits")
        String phoneNumber,

        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Pin is required")
        @Size(min = 4, max = 4, message = "Pin must be only 4 characters")
        String pin,

        @NotBlank(message = "Password is required")
        @Pattern(regexp="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$") // Regular Expression
        String password,

        @NotBlank(message = "Confirmed password number is required")
        @Pattern(regexp="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$") // Regular Expression
        String confirmedPassword,

        @NotBlank(message = "National card id is required")
        String nationalCardId,

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Gender is required")
        String gender,

        @NotNull(message = "Term must be accept")
        Boolean acceptTerm
) {
}
