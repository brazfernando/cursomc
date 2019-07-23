package com.fernando.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fernando.cursomc.domain.Cliente;
import com.fernando.cursomc.dto.ClienteDTO;
import com.fernando.cursomc.repositories.ClienteRepository;
import com.fernando.cursomc.services.exceptions.DataIntegratyException;
import com.fernando.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repositorio;

	public Cliente find(Integer id) {
		Cliente obj = repositorio.findOne(id);
		if (obj == null) {	
			throw new ObjectNotFoundException("Objeto não encontrado Id:" + id + ", Tipo:" + Cliente.class.getName());
		}
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repositorio.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repositorio.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegratyException("Não é possível excluir por que há entidades relacionadas.");
		}
	
	}

	public List<Cliente> findAll() {
		return repositorio.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repositorio.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
