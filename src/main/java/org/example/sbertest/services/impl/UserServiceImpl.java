package org.example.sbertest.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sbertest.DTO.request.PutUserRequest;
import org.example.sbertest.DTO.response.PublicUserResponse;
import org.example.sbertest.DTO.response.PutUserResponse;
import org.example.sbertest.handling.CustomException;
import org.example.sbertest.handling.ErrorCodes;
import org.example.sbertest.mapper.UserMapper;
import org.example.sbertest.modules.UserEntity;
import org.example.sbertest.repositories.UserRepository;
import org.example.sbertest.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserRepository repository;

    public List<PublicUserResponse> getAllUsers() {
        return userMapper.fromListUserEntityToListPublicUserResponse(repository.findAll());
    }

    public PublicUserResponse getUserInfoById(UUID id) {
        return userMapper.fromUserEntityToPublicUserResponse(getUserById(id));
    }

    public PublicUserResponse getUserInfo() {
        return userMapper.fromUserEntityToPublicUserResponse(getAuthUser());
    }


    @Transactional
    public PutUserResponse replaceUser(PutUserRequest userRequest) {
        return userMapper.fromUserEntityToPutUserResponse(userMapper.fromPutUserRequestToUpdateUserEntity(getAuthUser(), userRequest));
    }

    public void deleteUser() {
        repository.delete(getAuthUser());
    }

    public UserEntity getAuthUser() {
        return repository.findById(UUID.fromString(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()))
                .orElseThrow(() -> new CustomException(ErrorCodes.USER_NOT_FOUND));
    }

    @Transactional
    public void blockUser(UUID id) {
        UserEntity user = repository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCodes.USER_NOT_FOUND));

        user.setStatus(false);
        repository.save(user);
    }

    @Transactional
    public void unblockUser(UUID id) {
        UserEntity user = repository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCodes.USER_NOT_FOUND));

        user.setStatus(true);
        repository.save(user);
    }

    public UserEntity getUserById(UUID uuid) {
        return repository.findById(uuid).orElseThrow(() -> new CustomException(ErrorCodes.USER_NOT_FOUND));
    }
}
