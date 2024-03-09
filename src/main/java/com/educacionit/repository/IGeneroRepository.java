package com.educacionit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educacionit.entity.Genero;

@Repository
public interface IGeneroRepository extends JpaRepository<Genero, Integer>{

	Genero findByNombreGenero(String nombreGenero);
}
