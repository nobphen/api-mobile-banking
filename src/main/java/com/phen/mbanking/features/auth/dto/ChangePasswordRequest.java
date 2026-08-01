package com.phen.mbanking.features.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequest(

        @NotBlank(message = "Old password is required")
        String oldPassword,

        @NotBlank(message = "New password is required")
        String newPassword,

        @NotBlank(message = "Confirmed new password is required")
        String confirmedNewPassword
) {
}
