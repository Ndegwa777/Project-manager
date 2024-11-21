package com.wera.wera.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wera.wera.config.JwtProvider;
import com.wera.wera.entities.User;
import com.wera.wera.repositories.UserRepository;
import com.wera.wera.request.LoginRequest;
import com.wera.wera.response.AuthResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthServiceImp implements AuthService{
 
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private CustomUserDetailsImp customUserDetailsImp;

    // private SubscriptionService subscriptionService;


@Override
public AuthResponse createUser(User user) throws Exception{
    
    User userExists = userRepository.findByEmail(user.getEmail());

    if(userExists != null){
        throw new Exception("User with this email already exists");
    }

    User newUser = new User();
    
    newUser.setPassword(passwordEncoder.encode(user.getPassword()));
    newUser.setEmail(user.getEmail());
    newUser.setFullName(user.getFullName());

    userRepository.save(newUser);
    // User savedUser = userRepository.save(newUser);

    // subscriptionService.createSubscription(savedUser);

    Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = JwtProvider.generateToken(authentication);

    AuthResponse response = new AuthResponse();
    response.setMessage("Singup Successful");
    response.setJwt(jwt);
    userRepository.save(newUser);
    

    return response;
}


@Override
public AuthResponse loginUser(LoginRequest loginRequest) {
    String username = loginRequest.getEmail();
    String password = loginRequest.getPassword();

    Authentication authentication = authenticate(username, password);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = JwtProvider.generateToken(authentication);

    AuthResponse res = new AuthResponse();
    res.setMessage("Signin successful!");
    res.setJwt(jwt);
    
    return res;
}

public Authentication authenticate(String username, String password){
    UserDetails userDetails = customUserDetailsImp.loadUserByUsername(username);

    if(userDetails == null){
        throw new BadCredentialsException("Invalid username");
    }
    if(!passwordEncoder.matches(password, userDetails.getPassword())){
        throw new BadCredentialsException("Invalid password"); 
    }

    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
}
}
