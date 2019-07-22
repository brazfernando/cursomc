package com.fernando.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fernando.cursomc.domain.Categoria;
import com.fernando.cursomc.repositories.CategoriaRepository;
import com.fernando.cursomc.services.exceptions.DataIntegratyException;
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

	public void delete(Integer id) {
		find(id);
		try {
			repositorio.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegratyException("Não é possível excluir uma categoria que possui produtos");
		}
	
	}

	public List<Categoria> findAll() {
		return repositorio.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repositorio.findAll(pageRequest);
	}

}
