package com.educacionit.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.educacionit.dto.GeneroDTO;
import com.educacionit.entity.Genero;

@Mapper(componentModel = "spring")
public interface GeneroMapper {
	@Mapping(target = "peliculas", ignore = true)
	Genero generoDTOtoGenero(GeneroDTO generoDTO);
	GeneroDTO generoToGeneroDTO(Genero genero);
}
