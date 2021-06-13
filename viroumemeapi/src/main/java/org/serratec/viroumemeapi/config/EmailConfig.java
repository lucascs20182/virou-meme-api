package org.serratec.viroumemeapi.config;

import java.time.format.DateTimeFormatter;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.serratec.viroumemeapi.entities.ClienteEntity;
import org.serratec.viroumemeapi.entities.DetalhesPedidoEntity;
import org.serratec.viroumemeapi.entities.PedidoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
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

//	public String sendRecoveryEmail(RecoveryEntity entity, String url) throws MessagingException {
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setTo(entity.getIdClinete().getEmail());
//		message.setSubject("Recuperação de Senha");
//		message.setText("Para definir uma nova senha, entrar no link:\n"+url);
//		mailSender.send(message);
//		return "E-mail, envidado com Sucesso!";
//	}

	public String sendEmailOrderCompleted(PedidoEntity entity) throws MessagingException {
		SimpleMailMessage message = new SimpleMailMessage();
		String conteudoPedido;
		String produtos = "";

		for (DetalhesPedidoEntity dpdr : entity.getProdutosDoPedido()) {
//			produtos.concat(dpdr.getProduto().getNome() + " - Quantidade: " + dpdr.getQuantidade());

			produtos += dpdr.getProduto().getNome() + " - Quantidade: " + dpdr.getQuantidade();
		}

		message.setSubject("Seu Pedido foi finalizado");
		message.setTo(entity.getCliente().getEmail());

		conteudoPedido = "Hey " + entity.getCliente().getNome()
				+ ", seu pedido foi finalizado com sucesso! Segue a baixo os detalhes!" + "\nNúmero do Pedido: "
				+ entity.getNumeroPedido() + "\nProdutos:\n" + produtos + "\nData de Entrega: "
				+ entity.getDataEntrega().format(DateTimeFormatter.ofPattern("dd/MM/yyy")) + "\nValor Total: " + "R$ "
				+ entity.getValorTotal();
		message.setText(conteudoPedido);

		mailSender.send(message);
		return "E-mail, envidado com Sucesso!";
	}

}