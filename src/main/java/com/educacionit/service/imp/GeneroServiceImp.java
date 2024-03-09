package com.educacionit.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.educacionit.dto.GeneroDTO;
import com.educacionit.dto.PeliculaDTO;
import com.educacionit.dto.mapper.GeneroMapper;
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
	private GeneroMapper generoMapper;

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

	@Override
	public List<GeneroDTO> obtenerGeneros() {
		List<Genero> generos = generoRepository.findAll();
		List<GeneroDTO> generosDTO = generos.stream().map(
				g->{
					GeneroDTO generoDTO= generoMapper.generoToGeneroDTO(g);
					return generoDTO;
				}).collect(Collectors.toList());
		return generosDTO;
	}

	@Override
	public GeneroDTO guardarGenero(GeneroDTO generoDTO) {
		Genero genero = generoRepository.save(
				generoMapper.generoDTOtoGenero(generoDTO));
		GeneroDTO generoDTOGuardado = generoMapper.generoToGeneroDTO(genero);
		return generoDTOGuardado;
	}

	@Override
	public Boolean eliminarGenero(Integer id) {
		Boolean isDelete = false;
		Optional<Genero> genero = generoRepository.findById(id);
		if(genero.isPresent()) {
			generoRepository.deleteById(id);
			isDelete=true;
		}
		return isDelete;
	}

	@Override
	public GeneroDTO obtenerPorId(Integer id) {
		Optional<Genero> genero = generoRepository.findById(id);
		GeneroDTO generoPorDevolver = null;
		if(genero.isPresent()) {
			generoPorDevolver = generoMapper.generoToGeneroDTO(genero.get());
		}
				
		return generoPorDevolver;
	}

}
