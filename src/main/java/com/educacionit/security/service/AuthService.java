package com.educacionit.security.service;

import com.educacionit.dto.security.AuthResponse;
import com.educacionit.dto.security.AuthenticationRequest;
import com.educacionit.dto.security.RegisterRequest;

public interface AuthService {

	AuthResponse register(RegisterRequest request);
	AuthResponse authenticate(AuthenticationRequest request);
}
