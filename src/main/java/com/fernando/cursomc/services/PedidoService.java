package com.fernando.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.cursomc.domain.ItemPedido;
import com.fernando.cursomc.domain.PagamentoComBoleto;
import com.fernando.cursomc.domain.Pedido;
import com.fernando.cursomc.domain.enums.EstadoPagamento;
import com.fernando.cursomc.repositories.ItemPedidoRepository;
import com.fernando.cursomc.repositories.PagamentoRepository;
import com.fernando.cursomc.repositories.PedidoRepository;
import com.fernando.cursomc.repositories.ProdutoRepository;
import com.fernando.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repositorio;
	@Autowired
	private BoletoService boletoService;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private ClienteService clienteService;

	public Pedido find(Integer id) {
		Pedido obj = repositorio.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado Id:" + id + ", Tipo:" + Pedido.class.getName());
		}
		return obj;
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pgto, obj.getInstante());
		}
		obj = repositorio.save(obj);
		pagamentoRepository.save(obj.getPagamento());

		for(ItemPedido ip: obj.getItens()) {
			ip.setDesconto(0.00);
			ip.setPreco(produtoRepository.findOne(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		
		itemPedidoRepository.save(obj.getItens());
		
		return obj;
	}

}
