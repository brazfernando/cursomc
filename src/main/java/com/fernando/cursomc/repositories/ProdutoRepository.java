package com.fernando.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.cursomc.domain.Categoria;
import com.fernando.cursomc.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	
	/**
	 * 
	 * CDU:
	 * Recuperar todos produtos cujo nome continue um dado string, e que pertença a pelo menos uma
	 * categoria dentre as categorias de uma lista
	 * 
	 * 
	 * */
	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Produto obj " + "INNER JOIN obj.categorias cat" + " WHERE obj.nome LIKE %:nome%"
			+ " AND cat IN :categorias")
	Page<Produto> search(@Param("nome")String nome, @Param("categorias")List<Categoria> categorias, Pageable pageRequest);

	/**
	 * Método Alterantivo ao de cima.
	 * **/
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);

}
