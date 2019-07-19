package com.fernando.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.cursomc.domain.Categoria;
import com.fernando.cursomc.repositories.CategoriaRepository;
import com.fernando.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repositorio;

	public Categoria find(Integer id) {
		Categoria obj = repositorio.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado Id:" + id + ", Tipo:" + Categoria.class.getName());
		}
		return obj;
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repositorio.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repositorio.save(obj);
	}

}
