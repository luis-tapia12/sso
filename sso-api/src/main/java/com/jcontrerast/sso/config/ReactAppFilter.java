package com.jcontrerast.sso.config;

import com.jcontrerast.sso.utils.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.MessageFormat;

@Component
public class ReactAppFilter extends OncePerRequestFilter {
    @Override
    @SuppressWarnings("all")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isAppRequest(request)) {
            HttpServletRequest mutatedRequest = mutateRequestToIndexPage(request);
            filterChain.doFilter(mutatedRequest, response);
        } else if (request.getRequestURI().equals(request.getContextPath() + "/")) {
            String appBaseURL = MessageFormat.format(Constants.APP_BASE_HOLDER, request.getContextPath());
            response.sendRedirect(appBaseURL);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private HttpServletRequest mutateRequestToIndexPage(HttpServletRequest request) {
        return new HttpServletRequestWrapper(request) {
            @Override
            public String getRequestURI() {
                return MessageFormat.format(Constants.APP_INDEX_HOLDER, request.getContextPath());
            }
        };
    }

    private static boolean isAppRequest(HttpServletRequest request) {
        String appBaseURL = MessageFormat.format(Constants.APP_BASE_HOLDER, request.getContextPath());
        String acceptHeader = request.getHeader(HttpHeaders.ACCEPT);
        return acceptHeader != null && request.getRequestURI().startsWith(appBaseURL) && acceptHeader.contains(MediaType.TEXT_HTML_VALUE);
    }
}
