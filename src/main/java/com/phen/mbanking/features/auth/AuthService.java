package com.phen.mbanking.features.auth;

import com.phen.mbanking.features.auth.dto.RegisterRequest;
import com.phen.mbanking.features.auth.dto.RegisterResponse;
import com.phen.mbanking.features.auth.dto.VerificationRequest;
import jakarta.mail.MessagingException;

public interface AuthService {


    /**
     * Verify
     * @param verificationRequest {@link VerificationRequest}
     */
    void verify(VerificationRequest verificationRequest);

    /**
     * Send mail
     *
     * @param email in tb_user
     */
    void sendVerification(String email) throws MessagingException;


    /**
     * Register
     *
     * @param registerRequest {@link  RegisterRequest}
     * @return {@link  RegisterResponse}
     */
    RegisterResponse register(RegisterRequest registerRequest);


}
