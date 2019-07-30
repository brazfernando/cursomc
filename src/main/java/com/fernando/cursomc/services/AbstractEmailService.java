package com.fernando.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.fernando.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Override
	public void emailConfirmacaoPedido(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessage(obj);
		sendMail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessage(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado Código: " + obj.getId());
		// garante que a hora seja a configurada no servidor.
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}

}
