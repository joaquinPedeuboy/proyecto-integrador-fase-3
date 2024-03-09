package com.educacionit.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.educacionit.dto.PeliculaDTO;
import com.educacionit.dto.mapper.PeliculaMapper;
import com.educacionit.entity.Genero;
import com.educacionit.repository.IGeneroRepository;
import com.educacionit.service.IGeneroService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GeneroServiceImp implements IGeneroService{
	private IGeneroRepository generoRepository;
	private PeliculaMapper peliculaMapper;

	@Override
	public List<PeliculaDTO> obtenerPorGenero(String genero) {
		Genero generoBuscado = generoRepository.findByNombreGenero(genero);
		List<PeliculaDTO> peliculas = generoBuscado.getPeliculas().stream().map(
				p->{
					PeliculaDTO dto = peliculaMapper.peliculaToPeliculaDTO(p);
					return dto;
				}).collect(Collectors.toList());
		return peliculas;
	}

}
