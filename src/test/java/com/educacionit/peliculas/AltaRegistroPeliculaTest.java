package com.educacionit.peliculas;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.educacionit.entity.Genero;
import com.educacionit.entity.ImagenPelicula;
import com.educacionit.entity.Pelicula;
import com.educacionit.model.MapperFiles;
import com.educacionit.repository.IGeneroRepository;
import com.educacionit.repository.IPeliculaRepository;

@SpringBootTest
public class AltaRegistroPeliculaTest {
	@Autowired
	private IGeneroRepository iGeneroRepository;
	@Autowired
	private MapperFiles mapperFiles;
	@Autowired
	private IPeliculaRepository peliculaRepository;
	@Test
	public void validarRegistroPelicula() throws IOException {
		//precondicion generos existen en la base de datos
		Genero genero = new Genero();
		genero.setNombreGenero("genero1");
		Genero genero1 = iGeneroRepository.save(genero);
		
		genero = new Genero();
		genero.setNombreGenero("genero2");
		Genero genero2 = iGeneroRepository.save(genero);
		
		Pelicula pelicula = new Pelicula();
		pelicula.setTitulo("Titulo01");
		pelicula.setUrlWeb("url web pelicula 01");
		
		List<Genero> generos = new ArrayList<>();
		generos.add(genero1);
		generos.add(genero2);
		pelicula.setGeneros(generos);
		
		ImagenPelicula imagenPelicula = new ImagenPelicula();
		imagenPelicula.setNombreArchivo("StarWars-Episodio-v.jpg");
		imagenPelicula.setImagen(mapperFiles.inputStreamToByteArray(getClass().
								getResourceAsStream("./resources/StarWars-Episodio-v.jpg")));
		
		pelicula.setImagenPelicula(imagenPelicula);
		
		Pelicula peliculaGuardada = peliculaRepository.save(pelicula);
		
		List<Pelicula> peliculas = peliculaRepository.findAll();
		
		assertTrue(!peliculas.isEmpty() && peliculas.get(0).getId().equals(peliculaGuardada.getId()));
		
		peliculaRepository.deleteAll();
		iGeneroRepository.deleteAll();
		
		assertTrue(peliculaRepository.findAll().isEmpty() && iGeneroRepository.findAll().isEmpty());
		
	}
}
