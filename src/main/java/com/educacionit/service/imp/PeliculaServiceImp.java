package com.educacionit.service.imp;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.educacionit.dto.PeliculaDTO;
import com.educacionit.dto.ResumenPeliculaDTO;
import com.educacionit.dto.mapper.PeliculaMapper;
import com.educacionit.entity.Pelicula;
import com.educacionit.repository.IPeliculaRepository;
import com.educacionit.service.IPeliculaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PeliculaServiceImp implements IPeliculaService{
	private PeliculaMapper peliculaMapper;
	private IPeliculaRepository peliculaRepository;

	@Override
	public ResumenPeliculaDTO registarPelicula(String movie, MultipartFile archivoImagen) {
		ResumenPeliculaDTO resumenPeliculaDTO = new ResumenPeliculaDTO();
		PeliculaDTO peliculaDTO = new PeliculaDTO();
		ObjectMapper objectMaper = new ObjectMapper();
		
			try {
				// mapeo de los parametros al DTO de pelicula
				peliculaDTO = objectMaper.readValue(movie, PeliculaDTO.class);
				peliculaDTO.setImagenPelicula(archivoImagen.getBytes());
				
				Pelicula pelicula = peliculaMapper.peliculaDtoToPelicula(peliculaDTO);
				
				Pelicula peliculaRegistrada = peliculaRepository.save(pelicula);
				
				resumenPeliculaDTO = peliculaMapper.peliculaToResumenPeliculaDTO(peliculaRegistrada);
				
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return resumenPeliculaDTO;
	}

	@Override
	public List<PeliculaDTO> buscarPorTitulo(String titulo) {
		List<Pelicula> peliculas = peliculaRepository.findByTituloContainingIgnoreCase(titulo);
		List<PeliculaDTO> peliculasDTO = peliculas.stream()
				.map(p->{
					PeliculaDTO dto = peliculaMapper.peliculaToPeliculaDTO(p);
					return dto;
				}).collect(Collectors.toList());
		return peliculasDTO;
	}

}
