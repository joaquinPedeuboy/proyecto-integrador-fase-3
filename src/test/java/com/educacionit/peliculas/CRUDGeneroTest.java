package com.educacionit.peliculas;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.educacionit.dto.GeneroDTO;
import com.educacionit.repository.IGeneroRepository;
import com.educacionit.service.IGeneroService;

@SpringBootTest
public class CRUDGeneroTest {
	@Autowired
	private IGeneroService generoService;
	@Autowired
	private IGeneroRepository generoRepository;
	
	@Test
	public void validarCRUD() {
		GeneroDTO generoDTO = new GeneroDTO();
		generoDTO.setNombreGenero("Animacion");			
		GeneroDTO generoDTO1 = generoService.guardarGenero(generoDTO);
		
		generoDTO = new GeneroDTO();
		generoDTO.setNombreGenero("Fantasia");			
		GeneroDTO generoDTO2 = generoService.guardarGenero(generoDTO);
		
		generoDTO = new GeneroDTO();
		generoDTO.setNombreGenero("Super Heroes");			
		GeneroDTO generoDTO3 = generoService.guardarGenero(generoDTO);
		
		assertTrue(generoService.obtenerGeneros().size()==3);
		
		GeneroDTO generoBuscado = generoService.obtenerPorId(generoDTO1.getId());
		
		assertTrue(generoBuscado.getNombreGenero().equals(generoDTO1.getNombreGenero()));
		
		GeneroDTO otroGenero = generoService.obtenerPorId(generoDTO2.getId());
		otroGenero.setNombreGenero("Terror");
		
		GeneroDTO generoModificado = generoService.guardarGenero(otroGenero);
		assertTrue(generoDTO2.getId().equals(generoModificado.getId()) &&
				!generoDTO2.getNombreGenero().endsWith(generoModificado.getNombreGenero()));
		
		Boolean isDelete1 = generoService.eliminarGenero(generoDTO1.getId());
		Boolean isDelete2 = generoService.eliminarGenero(generoModificado.getId());
		Boolean isDelete3 = generoService.eliminarGenero(generoDTO3.getId());
		
		assertTrue(isDelete1 && isDelete2 && isDelete3);
		
		assertTrue(generoService.obtenerGeneros().isEmpty());
	}
}
