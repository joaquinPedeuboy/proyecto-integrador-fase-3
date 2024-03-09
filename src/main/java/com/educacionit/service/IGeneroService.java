package com.educacionit.service;

import java.util.List;

import com.educacionit.dto.PeliculaDTO;

public interface IGeneroService {

	List<PeliculaDTO> obtenerPorGenero(String genero);
}
