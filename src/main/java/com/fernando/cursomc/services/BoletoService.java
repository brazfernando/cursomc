package com.fernando.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.fernando.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pgto, Date instante) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(instante);
		// Adiciona 7 dias apos o instante do pedido.
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		pgto.setDataVencimento(calendar.getTime());

	}

}
