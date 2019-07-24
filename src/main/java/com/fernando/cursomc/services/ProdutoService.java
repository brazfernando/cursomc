package com.fernando.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fernando.cursomc.domain.Categoria;
import com.fernando.cursomc.domain.Produto;
import com.fernando.cursomc.repositories.CategoriaRepository;
import com.fernando.cursomc.repositories.ProdutoRepository;
import com.fernando.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repositorio;
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto find(Integer id) {
		Produto obj = repositorio.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado Id:" + id + ", Tipo:" + Produto.class.getName());
		}
		return obj;
	}
	
	public Page<Produto> search(String nome, List<Integer> idsCategoria,Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAll(idsCategoria);
		return repositorio.search(nome, categorias, pageRequest);
	}

}
