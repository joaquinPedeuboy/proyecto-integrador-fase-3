package com.educacionit.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "generos")
public class Genero {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gen_id")
	private Integer id;
	@Column(name = "gen_nombre",nullable = false,length = 15)
	private String nombreGenero;
	@ManyToMany(mappedBy = "generos",fetch = FetchType.EAGER)
	private List<Pelicula> peliculas;
}
