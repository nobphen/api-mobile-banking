package com.phen.mbanking.mapper;

import com.phen.mbanking.domain.User;
import com.phen.mbanking.features.auth.dto.RegisterRequest;
import com.phen.mbanking.features.auth.dto.RegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromRegisterRequest(RegisterRequest registerRequest);

    @Mapping(target = "message", constant  = "You have register successfully,please verify email")
    @Mapping(target = "email", source = "user.email")
    RegisterResponse toRegisterResponse (User user);

}
