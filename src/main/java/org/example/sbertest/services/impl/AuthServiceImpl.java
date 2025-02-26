package org.example.sbertest.services.impl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sbertest.DTO.request.AuthUserRequest;
import org.example.sbertest.DTO.request.RegisterUserRequest;
import org.example.sbertest.DTO.response.LoginUserResponse;
import org.example.sbertest.handling.CustomException;
import org.example.sbertest.handling.ErrorCodes;
import org.example.sbertest.mapper.UserMapper;
import org.example.sbertest.modules.UserEntity;
import org.example.sbertest.repositories.UserRepository;
import org.example.sbertest.security.CustomUserDetails;
import org.example.sbertest.security.JwtService;
import org.example.sbertest.services.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;

    private final UserRepository repository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public LoginUserResponse signUp(RegisterUserRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            throw new CustomException(ErrorCodes.USER_ALREADY_EXISTS);
        }

        if (request.getRoles() == null || request.getRoles().isEmpty()) {
            throw new CustomException(ErrorCodes.USER_ROLE_NOT_NULL);
        }

        UserEntity user = userMapper.fromRegisterUserRequestToUserEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        UserEntity newUser = repository.save(user);

        return generateLoginResponse(newUser);
    }

    @Transactional
    public LoginUserResponse signIn(AuthUserRequest request) {

        UserEntity user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCodes.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCodes.PASSWORD_NOT_VALID);
        }

        return generateLoginResponse(user);
    }

    private LoginUserResponse generateLoginResponse(UserEntity user) {
        CustomUserDetails customUserDetails = new CustomUserDetails(user.getId(), user.getPassword(), user.getRoles());
        String jwt = jwtService.generateToken(customUserDetails);
        return userMapper.fromUserEntityToLoginUserResponse(user, jwt);
    }

}
