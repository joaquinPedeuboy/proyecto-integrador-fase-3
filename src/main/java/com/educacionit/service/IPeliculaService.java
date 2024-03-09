package com.educacionit.service;

import org.springframework.web.multipart.MultipartFile;

import com.educacionit.dto.ResumenPeliculaDTO;

public interface IPeliculaService {
	ResumenPeliculaDTO registarPelicula(String movie, MultipartFile archivoImagen);
}
