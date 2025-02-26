package org.example.sbertest.services;

import org.example.sbertest.DTO.request.AuthUserRequest;
import org.example.sbertest.DTO.request.RegisterUserRequest;
import org.example.sbertest.DTO.response.LoginUserResponse;

public interface AuthService {

    LoginUserResponse signUp(RegisterUserRequest request);

    LoginUserResponse signIn(AuthUserRequest request);
}
