package com.phen.mbanking.features.auth;

import com.phen.mbanking.domain.Role;
import com.phen.mbanking.domain.User;
import com.phen.mbanking.features.auth.dto.RegisterRequest;
import com.phen.mbanking.features.auth.dto.RegisterResponse;
import com.phen.mbanking.features.user.RoleRepository;
import com.phen.mbanking.features.user.UserRepository;
import com.phen.mbanking.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

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
