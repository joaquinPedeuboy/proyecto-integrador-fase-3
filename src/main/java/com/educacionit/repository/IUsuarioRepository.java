package com.educacionit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educacionit.entity.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String email);
}
