package org.example.sbertest.mapper;

import org.example.sbertest.DTO.request.PutUserRequest;
import org.example.sbertest.DTO.request.RegisterUserRequest;
import org.example.sbertest.DTO.response.LoginUserResponse;
import org.example.sbertest.DTO.response.PublicUserResponse;
import org.example.sbertest.DTO.response.PutUserResponse;
import org.example.sbertest.modules.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = RoleMapper.class)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    UserEntity fromRegisterUserRequestToUserEntity(RegisterUserRequest registerUserRequest);

    LoginUserResponse fromUserEntityToLoginUserResponse(UserEntity userEntity, String token);

    PublicUserResponse fromUserEntityToPublicUserResponse(UserEntity user);

    List<PublicUserResponse> fromListUserEntityToListPublicUserResponse(List<UserEntity> list);

    PutUserResponse fromUserEntityToPutUserResponse(UserEntity user);

    UserEntity fromPutUserRequestToUpdateUserEntity(@MappingTarget UserEntity user, PutUserRequest userRequest);

}
