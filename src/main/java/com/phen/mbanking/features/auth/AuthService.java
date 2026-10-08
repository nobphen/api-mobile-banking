package com.phen.mbanking.features.auth;

import com.phen.mbanking.features.auth.dto.*;
import jakarta.mail.MessagingException;

public interface AuthService {


    /**
     * Login
     * @param loginRequest  {@link LoginRequest}
     * @return {@link AuthResponse}
     */
    AuthResponse login(LoginRequest loginRequest);


    /**
     * Resend mail
     *
     * @param email in tb_user
     */
    void reSendVerification(String email) throws MessagingException;


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
