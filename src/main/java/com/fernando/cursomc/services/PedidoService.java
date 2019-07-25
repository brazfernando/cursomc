package com.fernando.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.cursomc.domain.Pedido;
import com.fernando.cursomc.repositories.PedidoRepository;
import com.fernando.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repositorio;

	public Pedido find(Integer id) {
		Pedido obj = repositorio.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado Id:" + id + ", Tipo:" + Pedido.class.getName());
		}
		return obj;
	}


}
