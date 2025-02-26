package org.example.sbertest.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.sbertest.DTO.response.commonResponse.BaseSuccessResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.sbertest.constants.Constants.BEARER_PREFIX;
import static org.example.sbertest.constants.Constants.HEADER_NAME;
import static org.example.sbertest.handling.ErrorCodes.UNAUTHORISED;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HEADER_NAME);
        if (authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
            final String jwt = authHeader.substring(7);
            try {
                final String userId = jwtService.extractUserId(jwt);

                if (userId != null && jwtService.isTokenValid(jwt)) {
                    CustomUserDetails userDetails = userDetailsService.loadUserByUsername(userId);

                    List<String> roles = jwtService.extractRoles(jwt);
                    Collection<GrantedAuthority> authorities = roles.stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toSet());

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, authorities
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    setErrorResponse(response, UNAUTHORISED.getCode());
                    return;
                }
            } catch (Exception e) {
                setErrorResponse(response, UNAUTHORISED.getCode());
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void setErrorResponse(HttpServletResponse response, Integer codes) throws IOException {
        BaseSuccessResponse errorResponse = new BaseSuccessResponse();
        errorResponse.setStatusCode(codes);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(HttpHeaders.CONTENT_TYPE);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(errorResponse);

        response.getWriter().write(jsonResponse);
    }
}
