package com.educacionit.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.educacionit.dto.PeliculaDTO;
import com.educacionit.dto.ResumenPeliculaDTO;

public interface IPeliculaService {
	ResumenPeliculaDTO registarPelicula(String movie, MultipartFile archivoImagen);
	
	List<PeliculaDTO> buscarPorTitulo (String titulo);
	
}
