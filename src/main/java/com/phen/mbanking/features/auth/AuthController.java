package com.phen.mbanking.features.auth;

import com.phen.mbanking.features.account.dto.AccountCreateRequest;
import com.phen.mbanking.features.account.dto.AccountResponse;
import com.phen.mbanking.features.auth.dto.RegisterRequest;
import com.phen.mbanking.features.auth.dto.RegisterResponse;
import com.phen.mbanking.features.auth.dto.SendVerificationRequest;
import com.phen.mbanking.features.auth.dto.VerificationRequest;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthController {
    private final AuthService authService;


    /**
     * Create new user
     *
     * @param registerRequest {@link  RegisterRequest}
     * @return {@link RegisterResponse}
     */

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    RegisterResponse register(@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }


    /**
     * Send verification
     * @param sendVerificationRequest {@link  SendVerificationRequest}
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/send-verification")
    void sendVerification(@Valid @RequestBody SendVerificationRequest sendVerificationRequest) throws MessagingException {
        authService.sendVerification(sendVerificationRequest.email());
    }


    /**
     * Send verify
     * @param verificationRequest {@link  VerificationRequest}
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/verify")
    void verify(@Valid @RequestBody VerificationRequest verificationRequest) {
        authService.verify(verificationRequest);
    }

    /**
     * Re Send verification
     * @param sendVerificationRequest {@link  SendVerificationRequest}
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/resend-verification")
    void ReSendVerification(@Valid @RequestBody SendVerificationRequest sendVerificationRequest) throws MessagingException {
        authService.reSendVerification(sendVerificationRequest.email());
    }


}
