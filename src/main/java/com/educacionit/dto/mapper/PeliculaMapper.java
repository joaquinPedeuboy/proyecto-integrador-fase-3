package com.educacionit.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.educacionit.dto.PeliculaDTO;
import com.educacionit.dto.ResumenPeliculaDTO;
import com.educacionit.entity.Genero;
import com.educacionit.entity.ImagenPelicula;
import com.educacionit.entity.Pelicula;
import com.educacionit.repository.IGeneroRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PeliculaMapper {
	private IGeneroRepository generoRepository;
	
	public Pelicula peliculaDtoToPelicula (PeliculaDTO peliculaDTO) {
		Pelicula pelicula = new Pelicula();
		pelicula.setTitulo(peliculaDTO.getTitulo());
		pelicula.setUrlWeb(peliculaDTO.getUrlWeb());
		ImagenPelicula imagenPelicula = new ImagenPelicula();
		imagenPelicula.setNombreArchivo(peliculaDTO.getNombreImagen());
		imagenPelicula.setImagen(peliculaDTO.getImagenPelicula());
		pelicula.setImagenPelicula(imagenPelicula);
		List<Genero> generos = new ArrayList<>();
		for(String g: peliculaDTO.getGeneros()) {
			generos.add(generoRepository.findByNombreGenero(g));
		}
		pelicula.setGeneros(generos);
		return pelicula;
	}
	
	public ResumenPeliculaDTO peliculaToResumenPeliculaDTO(Pelicula pelicula) {
		ResumenPeliculaDTO resumenPeliculaDTO = new ResumenPeliculaDTO();
		resumenPeliculaDTO.setNombreImagen(pelicula.getImagenPelicula().getNombreArchivo());
		String generosString = pelicula.getGeneros().stream().map(
				g->g.getNombreGenero()).collect(Collectors.joining(" - "));
		
		resumenPeliculaDTO.setStringGeneros(generosString);
		resumenPeliculaDTO.setTitulo(pelicula.getTitulo());
		resumenPeliculaDTO.setUrlWeb(pelicula.getUrlWeb());
		return resumenPeliculaDTO;
	}
	
	public PeliculaDTO peliculaToPeliculaDTO(Pelicula pelicula) {
		PeliculaDTO peliculaDTO = new PeliculaDTO();
		peliculaDTO.setTitulo(pelicula.getTitulo());
		peliculaDTO.setUrlWeb(pelicula.getUrlWeb());
		peliculaDTO.setNombreImagen(pelicula.getImagenPelicula().getNombreArchivo());
		List<String> generosString = pelicula.getGeneros().stream().map(
				g->g.getNombreGenero()).collect(Collectors.toList());
		peliculaDTO.setGeneros(generosString);
		peliculaDTO.setImagenPelicula(pelicula.getImagenPelicula().getImagen());
		
		return peliculaDTO;
	}
}
