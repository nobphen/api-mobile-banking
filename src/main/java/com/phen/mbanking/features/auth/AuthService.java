package com.phen.mbanking.features.auth;

import com.phen.mbanking.features.auth.dto.RegisterRequest;
import com.phen.mbanking.features.auth.dto.RegisterResponse;

public interface AuthService {

    /**
     * Register
     * @param registerRequest {@link  RegisterRequest}
     * @return {@link  RegisterResponse}
     */
    RegisterResponse register(RegisterRequest registerRequest);
}
