package com.fernando.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	//Evita locking em banco de dados... informa que não há necessidade de uma transação em banco de dados.
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
	
}
