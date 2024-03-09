package com.educacionit.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.educacionit.TipoRol;
import com.educacionit.dto.security.AuthResponse;
import com.educacionit.dto.security.AuthenticationRequest;
import com.educacionit.dto.security.RegisterRequest;
import com.educacionit.entity.Rol;
import com.educacionit.entity.Usuario;
import com.educacionit.repository.IRolRepository;
import com.educacionit.repository.IUsuarioRepository;
import com.educacionit.security.service.AuthService;
import com.educacionit.security.service.JWTService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService{
	private final PasswordEncoder passwordEncoder;
	private final IUsuarioRepository usuarioRepository;
	private final JWTService jwtService;
	private final AuthenticationManager authenticationManager;
	private final IRolRepository rolRepository;
	
	
	@Override
	public AuthResponse register(RegisterRequest request) {
		List<Rol> roles = new ArrayList<>();
		Optional<Rol> rol = rolRepository.findByNombreRol(TipoRol.ROLE_USER);
		if(rol.isPresent()) {
			roles.add(rol.get());
		}
		var user = Usuario.builder()
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.roles(roles)
				.build();
		usuarioRepository.save(user);
		var jwtToken= jwtService.generatedToken(user);
		return AuthResponse.builder().token(jwtToken).build();
	}

	@Override
	public AuthResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		
		var user = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();
		var jwtToken= jwtService.generatedToken(user);
		return AuthResponse.builder().token(jwtToken).build();
	}
	

}
