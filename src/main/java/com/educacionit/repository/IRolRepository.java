package com.educacionit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educacionit.TipoRol;
import com.educacionit.entity.Rol;

public interface IRolRepository extends JpaRepository<Rol, Long>{
	Optional<Rol> findByNombreRol(TipoRol nombreRol);

}
