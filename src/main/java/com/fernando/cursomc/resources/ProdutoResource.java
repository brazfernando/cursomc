package com.fernando.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.cursomc.domain.Categoria;
import com.fernando.cursomc.domain.Produto;
import com.fernando.cursomc.dto.CategoriaDTO;
import com.fernando.cursomc.dto.ProdutoDTO;
import com.fernando.cursomc.resources.utils.URL;
import com.fernando.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Produto obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nome", defaultValue = "") String nome, 
			@RequestParam(value = "categorias", defaultValue = "") String categorias, 
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> idsCategorias = URL.decodeIntList(categorias);
		Page<Produto> list = service.search(nomeDecoded, idsCategorias, page, linesPerPage, orderBy, direction);
		//Page não necessita da conversão feita no método de cima Nota-se pela retirada do stream e do collect.
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	
	
	
}
