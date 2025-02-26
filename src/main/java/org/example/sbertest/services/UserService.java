package org.example.sbertest.services;

import org.example.sbertest.DTO.request.PutUserRequest;
import org.example.sbertest.DTO.response.PublicUserResponse;
import org.example.sbertest.DTO.response.PutUserResponse;
import org.example.sbertest.modules.UserEntity;
import java.util.List;
import java.util.UUID;

public interface UserService {

    List<PublicUserResponse> getAllUsers();

    PublicUserResponse getUserInfoById(UUID uuid);

    PublicUserResponse getUserInfo();

    void deleteUser();

    void blockUser(UUID id);

    void unblockUser(UUID id);

    PutUserResponse replaceUser(PutUserRequest userRequest);

    UserEntity getAuthUser();

    UserEntity getUserById(UUID uuid);
}
