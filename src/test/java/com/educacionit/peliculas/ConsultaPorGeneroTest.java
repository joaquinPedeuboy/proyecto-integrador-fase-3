package com.educacionit.peliculas;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.educacionit.dto.PeliculaDTO;
import com.educacionit.entity.Genero;
import com.educacionit.entity.ImagenPelicula;
import com.educacionit.entity.Pelicula;
import com.educacionit.repository.IGeneroRepository;
import com.educacionit.repository.IPeliculaRepository;
import com.educacionit.service.IGeneroService;

@SpringBootTest
public class ConsultaPorGeneroTest {

	@Autowired
	private IGeneroRepository generoRepository;
	@Autowired
	private IPeliculaRepository peliculaRepository;
	@Autowired
	private IGeneroService generoService;
	
	
	@Test
	public void validarConsulta() {
		try {
			//Insnancias de generos
			Genero genero = new Genero();
			genero.setNombreGenero("Animacion");			
			Genero genero1 = generoRepository.save(genero);
			
			genero = new Genero();
			genero.setNombreGenero("Fantasia");			
			Genero genero2 = generoRepository.save(genero);
			
			genero = new Genero();
			genero.setNombreGenero("Super Heroes");			
			Genero genero3 = generoRepository.save(genero);
			
			//Instancia de pelicula 1
			Pelicula pelicula = new Pelicula();
			pelicula.setTitulo("Los Vengadores Endgame");
			pelicula.setUrlWeb("https://www.filmaffinity.com/ar/film993884.html");
			List<Genero> generos = new ArrayList<>();
			generos.add(genero1);
			generos.add(genero3);
			pelicula.setGeneros(generos);
			InputStream inputStream = getClass().getResourceAsStream("./resources/Los-Vengadores-Endgame.jpg");
			ImagenPelicula imagenPelicula = new ImagenPelicula();
			imagenPelicula.setNombreArchivo("Los-Vengadores-Endgame.jpg");
			imagenPelicula.setImagen(inputStream.readAllBytes());
			pelicula.setImagenPelicula(imagenPelicula);
			
			Pelicula peliculaGuardada1 = peliculaRepository.save(pelicula);
			
			// instancia de pelicula 2
			pelicula = new Pelicula();
			pelicula.setTitulo("Star Wars Episodio V");
			pelicula.setUrlWeb("https://www.filmaffinity.com/ar/film605090.html");
			generos = new ArrayList<>();
			generos.add(genero1);
			generos.add(genero2);
			pelicula.setGeneros(generos);
			inputStream = getClass().getResourceAsStream("./resources/StarWars-Episodio-V.jpg");
			imagenPelicula = new ImagenPelicula();
			imagenPelicula.setNombreArchivo("StarWars-Episodio-V.jpg");
			imagenPelicula.setImagen(inputStream.readAllBytes());
			pelicula.setImagenPelicula(imagenPelicula);
			
			Pelicula peliculaGuardada2 = peliculaRepository.save(pelicula);
			
			// instancia de pelicula 3
			pelicula = new Pelicula();
			pelicula.setTitulo("Tiburon");
			pelicula.setUrlWeb("https://www.cinesargentinos.com.ar/pelicula/8454-tiburon/");
			generos = new ArrayList<>();
			generos.add(genero1);
			generos.add(genero2);
			pelicula.setGeneros(generos);
			inputStream = getClass().getResourceAsStream("./resources/Tiburon.jpg");
			imagenPelicula = new ImagenPelicula();
			imagenPelicula.setNombreArchivo("Tiburon.jpg");
			imagenPelicula.setImagen(inputStream.readAllBytes());
			pelicula.setImagenPelicula(imagenPelicula);
			
			Pelicula peliculaGuardada3 = peliculaRepository.save(pelicula);
		
			// Consulta 
			
			List<PeliculaDTO> peliculas = generoService.obtenerPorGenero("Fantasia");
			
			for(PeliculaDTO p: peliculas) {
				System.out.println(p.getTitulo());
			}
			
			assertTrue(peliculas.size()==2);
			
			//peliculaRepository.deleteAll();
			//generoRepository.deleteAll();
			
			//assertTrue(peliculaRepository.findAll().isEmpty() && generoRepository.findAll().isEmpty());
			
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
