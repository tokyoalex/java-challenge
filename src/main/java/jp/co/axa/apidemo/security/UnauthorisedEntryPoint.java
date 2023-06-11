package jp.co.axa.apidemo.security;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

public class UnauthorisedEntryPoint implements AuthenticationEntryPoint {
    public UnauthorisedEntryPoint() {
    }

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws
            IOException, ServletException {
        response.sendError(SC_UNAUTHORIZED, "Unauthorized: Authentication token was either missing or invalid.");
    }
}
