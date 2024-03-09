package com.educacionit.service;

import java.util.List;

import com.educacionit.dto.GeneroDTO;
import com.educacionit.dto.PeliculaDTO;

public interface IGeneroService {

	List<PeliculaDTO> obtenerPorGenero(String genero);
	
	List<GeneroDTO> obtenerGeneros();
	GeneroDTO guardarGenero(GeneroDTO generoDTO);
	Boolean eliminarGenero(Integer id);
	GeneroDTO obtenerPorId(Integer id);
}
