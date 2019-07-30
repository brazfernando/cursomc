package com.fernando.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.fernando.cursomc.domain.Pedido;

public interface EmailService {
	
	public void emailConfirmacaoPedido(Pedido obj);
	
	public void sendMail(SimpleMailMessage msg);

}
