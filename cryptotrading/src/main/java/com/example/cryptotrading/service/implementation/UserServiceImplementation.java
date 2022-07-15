package com.example.cryptotrading.service.implementation;

import com.example.cryptotrading.exceptions.InvalidUserCredentialsException;
import com.example.cryptotrading.exceptions.InvalidUserPasswordsException;
import com.example.cryptotrading.exceptions.UserAlreadyExsistsException;
import com.example.cryptotrading.model.User;
import com.example.cryptotrading.model.enumeration.Role;
import com.example.cryptotrading.repository.UserRepository;
import com.example.cryptotrading.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String firstName, String lastName, Role role)
            throws InvalidUserCredentialsException, InvalidUserPasswordsException, UserAlreadyExsistsException {

        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            throw new InvalidUserCredentialsException("Please provide correct user details!");
        }
        if(!password.equals(repeatPassword)){
            throw new InvalidUserPasswordsException("Please provide same passwords!");
        }
        if(this.userRepository.findByUsername(username) != null ){
            throw new UserAlreadyExsistsException("Please provide same passwords!");
        }
        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(username, encodedPassword, firstName, lastName, role);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
