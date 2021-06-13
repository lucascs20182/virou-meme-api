package org.serratec.viroumemeapi.config;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.serratec.viroumemeapi.entities.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Configuration
public class EmailConfig {

	@Autowired
	JavaMailSender mailSender;

	public String sendEmail(ClienteEntity cliente, String msg, String assunto) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true);
		helper.setSubject(assunto);
		helper.setTo(cliente.getEmail());
		helper.setText(String.format(msg, cliente.getNome(), cliente.getUsername()), true);
		mailSender.send(message);
		return "E-mail, envidado com Sucesso!";
	}

}