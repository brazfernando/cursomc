package com.fernando.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.cursomc.domain.Categoria;
import com.fernando.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService  {

	@Autowired
	private CategoriaRepository repositorio;
	
	
	public Categoria buscar(Integer id) {
		Categoria obj = repositorio.findOne(id);
		return obj;
	}
	
}
