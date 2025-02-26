package org.example.sbertest.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.sbertest.DTO.response.commonResponse.BaseSuccessResponse;
import org.example.sbertest.handling.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        BaseSuccessResponse errorResponse = new BaseSuccessResponse(ErrorCodes.UNAUTHORISED.getCode());
        response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
