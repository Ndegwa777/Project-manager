package com.wera.wera.service;

import com.wera.wera.entities.User;
import com.wera.wera.request.LoginRequest;
import com.wera.wera.response.AuthResponse;

public interface AuthService {

    AuthResponse createUser(User user) throws Exception;

    AuthResponse loginUser(LoginRequest loginRequest);
    
}
