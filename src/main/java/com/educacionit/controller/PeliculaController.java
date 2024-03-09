package com.educacionit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.educacionit.dto.PeliculaDTO;
import com.educacionit.dto.ResumenPeliculaDTO;
import com.educacionit.service.IGeneroService;
import com.educacionit.service.IPeliculaService;

@RestController
@RequestMapping("api/movie")
public class PeliculaController {
	@Autowired
	private IPeliculaService peliculaService;
	@Autowired
	private IGeneroService generoService;
	@PostMapping(value = "/save",consumes = {org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
												org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<ResumenPeliculaDTO> registrarPelicula(@RequestPart("movie") String movie,
																@RequestPart("file") List<MultipartFile> files){
		ResumenPeliculaDTO resumenPeliculaDTO = peliculaService.registarPelicula(movie, files.get(0));
		return new ResponseEntity<>(resumenPeliculaDTO,HttpStatus.CREATED);
	}
	
	@GetMapping("/findbytitle/{titulo}")
	public ResponseEntity<List<PeliculaDTO>> buscarPorTitulo(@PathVariable String titulo){
		List<PeliculaDTO> peliculas = peliculaService.buscarPorTitulo(titulo);
		return ResponseEntity.ok(peliculas);
	}
	
	@GetMapping("/findbygenre/{genero}")
	public ResponseEntity<List<PeliculaDTO>> buscarPorGenero(@PathVariable String genero) {
		List<PeliculaDTO> peliculas = generoService.obtenerPorGenero(genero);
		return ResponseEntity.ok(peliculas);
	}
}
