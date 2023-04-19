package com.esempio.RentalCar.config.Security;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")){
            throw new AuthenticationServiceException("Metodo di autenticazione non supportato" + request.getMethod());
        }
        UsernamePasswordAuthenticationToken authRequest = getAuthRequest(request);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest request){
        String username = request.getParameter("userId");
        String password = obtainPassword(request);
        username = (username == null) ? "" : username;
        password = (password == null) ? "" : password;
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
