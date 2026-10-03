package com.phen.mbanking.features.auth;

import com.phen.mbanking.features.account.dto.AccountCreateRequest;
import com.phen.mbanking.features.account.dto.AccountResponse;
import com.phen.mbanking.features.auth.dto.RegisterRequest;
import com.phen.mbanking.features.auth.dto.RegisterResponse;
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
     * Create new account
     *
     * @param registerRequest {@link  RegisterRequest}
     * @return {@link RegisterResponse}
     */

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/register")
    RegisterResponse register(@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

}
