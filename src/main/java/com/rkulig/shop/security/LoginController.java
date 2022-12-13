package com.rkulig.shop.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginController {

    private final AuthenticationManager authenticationMenager;

    private Long expirationTime;
    private String secret;

    public LoginController(AuthenticationManager authenticationMenager,
                           @Value("${jwt.expirationTime}") Long expirationDate,
                           @Value("${jwt.secret}") String secret) {
        this.authenticationMenager = authenticationMenager;
        this.expirationTime = expirationDate;
        this.secret = secret;
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginCredentials loginCredentials){
        Authentication authenticate = authenticationMenager.authenticate(
                new UsernamePasswordAuthenticationToken(loginCredentials.getUsername(), loginCredentials.getPassword())
        );

        UserDetails principal = (UserDetails) authenticate.getPrincipal();

        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC256(secret));
        return new Token(token);
    }

    @Getter
    private static class LoginCredentials{
        private String username;
        private String password;
    }

    @Getter
    @AllArgsConstructor
    private static class Token{
        private String token;
    }
}
