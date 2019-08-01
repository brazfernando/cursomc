package com.fernando.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.fernando.cursomc.domain.Pedido;

public interface EmailService {

	public void emailConfirmacaoPedido(Pedido obj);

	public void sendMail(SimpleMailMessage msg);

	public void sendEmailConfirmacaoHtml(Pedido obj);

	public void sendEmailHtml(MimeMessage msg);

}
