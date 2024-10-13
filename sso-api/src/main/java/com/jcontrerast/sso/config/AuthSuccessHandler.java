package com.jcontrerast.sso.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcontrerast.sso.dto.ServiceResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper mapper;
    private final String url;

    public AuthSuccessHandler(
            ObjectMapper mapper,
            String url
    ) {
        this.mapper = mapper;
        this.url = url;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        ServiceResponseDTO dto = ServiceResponseDTO.create("Authentication Success");
        dto.addDetail(request.getContextPath() + url);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().print(mapper.writeValueAsString(dto));
    }
}
