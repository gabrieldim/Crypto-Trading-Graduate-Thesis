package com.example.cryptotrading.config.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.cryptotrading.config.JWTAuthConstants;
import com.example.cryptotrading.exceptions.InvalidUserPasswordsException;
import com.example.cryptotrading.model.User;
import com.example.cryptotrading.model.dto.UserDetailsDto;
import com.example.cryptotrading.repository.UserRepository;
import com.example.cryptotrading.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

@Component
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public String loggedUserUsername;

    public void setLoggedUserUsername(String loggedUserUsername) {
        this.loggedUserUsername = loggedUserUsername;
    }

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.setAuthenticationManager(authenticationManager);
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        User creds = null;
        try {
            creds = new ObjectMapper().readValue(request.getInputStream(), User.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(creds == null){
            throw new UsernameNotFoundException("Invalid user credentials exception!");
        }

        User user = userRepository.findByUsername(creds.getUsername());
        if(user == null){
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(null, creds.getPassword(),
                            null));
        }
        this.setLoggedUserUsername(user.getUsername());
        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return user.getAuthorities();
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getUsername();
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };
        if(!passwordEncoder.matches(creds.getPassword(),userDetails.getPassword())){
            try {
                throw new InvalidUserPasswordsException("The password is not correct!");
            } catch (InvalidUserPasswordsException e) {
                throw new RuntimeException(e);
            }
        }

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), creds.getPassword(),
                                                            userDetails.getAuthorities()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        this.generateJwt(response, authResult);
        super.successfulAuthentication(request, response, chain, authResult);
    }

    public String generateJwt(HttpServletResponse response, Authentication authResult) throws JsonProcessingException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByUsername(loggedUserUsername);
        if(user == null){
            return "user not found!";
        }
        String token = JWT.create()
                .withSubject(new ObjectMapper().writeValueAsString(UserDetailsDto.of(user)))
                .withExpiresAt(new Date(System.currentTimeMillis() + JWTAuthConstants.EXPIRATION_DATE))
                .sign(Algorithm.HMAC256(JWTAuthConstants.SECRET.getBytes()));
        response.addHeader(JWTAuthConstants.HEADER, JWTAuthConstants.TOKEN_PREFIX + token);
        return JWTAuthConstants.TOKEN_PREFIX + token;
    }

}
