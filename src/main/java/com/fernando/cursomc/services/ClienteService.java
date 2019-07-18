package com.fernando.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.cursomc.domain.Cliente;
import com.fernando.cursomc.repositories.ClienteRepository;
import com.fernando.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repositorio;

	public Cliente buscar(Integer id) {
		Cliente obj = repositorio.findOne(id);
		if (obj == null) {	
			throw new ObjectNotFoundException("Objeto não encontrado Id:" + id + ", Tipo:" + Cliente.class.getName());
		}
		return obj;
	}

}
