package com.phen.mbanking.features.auth;

import com.phen.mbanking.domain.Role;
import com.phen.mbanking.domain.User;
import com.phen.mbanking.domain.UserVerification;
import com.phen.mbanking.features.auth.dto.*;
import com.phen.mbanking.features.user.RoleRepository;
import com.phen.mbanking.features.user.UserRepository;
import com.phen.mbanking.mapper.UserMapper;
import com.phen.mbanking.util.RandomUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.awt.image.RasterFormatException;
import java.time.Instant;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImp implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private final UserVerificationRepository userVerificationRepository;

    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtEncoder accessTokenJwtEndCoder;

    private final JavaMailSender javaMailSender;

    /// inject mail
    @Value("${spring.mail.username}")
    private String emailAdmin;


    @Override
    public AuthResponse login(LoginRequest loginRequest) {


        /// Authentication client with username ( phoneNumber) and password
        Authentication auth = new UsernamePasswordAuthenticationToken(loginRequest.phoneNumber(), loginRequest.password());

        auth = daoAuthenticationProvider.authenticate(auth);


        log.info("Auth {}", auth.getPrincipal());


        /// Generate JWT token by JwtEndCode
        /// 1. Define Jwt claimsSet (Payload)
        Instant now = Instant.now();

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .id(auth.getName())
                .subject("Access APIs")
                .issuer(auth.getName())
                .issuedAt(now)
                .expiresAt(now.plus(30, ChronoUnit.MINUTES))
                .audience(List.of("Android", "IOS"))
                .claim("isAdmin", true)
                .build();


        /// 2. Generate token

        String accessToken = accessTokenJwtEndCoder
                .encode(JwtEncoderParameters.from(jwtClaimsSet)).
                getTokenValue();


        log.info("Access Token : {}", accessToken);

        return AuthResponse.
                builder()
                .tokenTyp("Bearer")
                .accessToken(accessToken)
                .build();
    }


    /**
     * Resend verification
     *
     * @param email in tb_user
     */
    @Override
    public void reSendVerification(String email) throws MessagingException {

        /// Validate email
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "User has been not found"
        ));

        /// Find existing verification
        UserVerification userVerification = userVerificationRepository
                .findByUser(user)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User has not been found"
                        )
                );


        /// Save data to tb_userVerification

        userVerification.setVerifiedCode(RandomUtil.random6Digits());
        userVerification.setExpiryTime(LocalTime.now().plusMinutes(1));

        /// Save data
        userVerificationRepository.save(userVerification);


        /// Prepare email for sending
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(email);
        helper.setFrom(emailAdmin);
        helper.setSubject("User Verification");
        helper.setText(userVerification.getVerifiedCode());

        javaMailSender.send(message);

    }


    /**
     * Verify
     *
     * @param verificationRequest {@link VerificationRequest}
     */
    @Override
    public void verify(VerificationRequest verificationRequest) {

        /// Validate email
        User user = userRepository
                .findByEmail(verificationRequest.email())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User has not found"
                ));

        /// Validate verified code
        UserVerification userVerification = userVerificationRepository
                .findByUserAndVerifiedCode(user, verificationRequest.verifiedCode())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User verification has not found"
                ));

        /// Validate code verified expired
        if (LocalTime.now().isAfter(userVerification.getExpiryTime())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Verification code has expired"
            );
        }

        /// Save
        user.setIsVerified(true);
        userRepository.save(user);
        userVerificationRepository.delete(userVerification);

    }


    /**
     * Send verified
     *
     * @param email in tb_user
     */
    @Override
    public void sendVerification(String email) throws MessagingException {

        /// Validate email
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "User has been not found"
        ));

        /// Find existing verification
        UserVerification userVerification = new UserVerification();


        /// Save data to tb_userVerification
        userVerification.setUser(user);

        userVerification.setVerifiedCode(RandomUtil.random6Digits());
        userVerification.setExpiryTime(LocalTime.now().plusMinutes(1));

        /// Save data
        userVerificationRepository.save(userVerification);


        /// Prepare email for sending
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(email);
        helper.setFrom(emailAdmin);
        helper.setSubject("User Verification");
        helper.setText(userVerification.getVerifiedCode());

        javaMailSender.send(message);

    }


    /**
     * Register
     *
     * @param registerRequest {@link  RegisterRequest}
     * @return {@link  RegisterResponse}
     */
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {

        /// Validate phone
        if (userRepository.existsByPhoneNumber(registerRequest.phoneNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Phone number is already been use"
            );
        }

        /// Validate email
        if (userRepository.existsByEmail(registerRequest.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email is already been user"
            );
        }


        /// Validate user password
        if (!registerRequest.password().equals(registerRequest.confirmedPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Password dest not match"
            );
        }

        /// Validate national card id
        if (userRepository.existsByNationalCardId(registerRequest.nationalCardId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "National card id is already been use"
            );
        }

        /// Validate term and policy
        if (!registerRequest.acceptTerm()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "You must accept term"
            );
        }

        /// Map data
        User user = userMapper.fromRegisterRequest(registerRequest);


        /// Set system data
        user.setUuid(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setProfileImage("profile/default-user.png");
        user.setIsDeleted(false);
        user.setIsBlocked(false);
        user.setIsVerified(false);


        /// Find role
        Role roleUser = roleRepository.findRoleUser(); /// default role
        Role roleCustomer = roleRepository.findRoleCustomer();
        List<Role> roles = List.of(roleUser, roleCustomer);
        user.setRoles(roles);

        /// Save to database
        userRepository.save(user);


        /// Style code 1
        return RegisterResponse
                .builder()
                .message("You have register successfully,please verify email")
                .email(user.getEmail())
                .build();


        /// Style code 2
        //return  userMapper.toRegisterResponse(user);

    }


}
