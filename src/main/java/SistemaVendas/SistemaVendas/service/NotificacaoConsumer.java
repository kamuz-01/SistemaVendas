package SistemaVendas.SistemaVendas.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import SistemaVendas.SistemaVendas.config.RabbitMQConfig; // Adicione este import
import SistemaVendas.SistemaVendas.model.NotificacaoMessage;

@Service
public class NotificacaoConsumer {

    @Autowired
    private JavaMailSender mailSender;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME) // Use a constante
    public void consumirNotificacao(NotificacaoMessage mensagem) {
        System.out.println("Consumindo mensagem do RabbitMQ para: " + mensagem.getEmailCliente());
        enviarEmailNotificacao(mensagem);
    }

    private void enviarEmailNotificacao(NotificacaoMessage mensagem) {
        System.out.println("Tentando enviar e-mail para: " + mensagem.getEmailCliente());
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(mensagem.getEmailCliente());
        email.setSubject("Notificação: Preço do Produto Reduzido!");
        email.setText(
            "Olá,\n\n" +
            "O preço do produto '" + mensagem.getProdutoDescricao() + "' foi reduzido!\n" +
            "Preço anterior: R$" + String.format("%.2f", mensagem.getPrecoAntigo()) + "\n" +
            "Novo preço: R$" + String.format("%.2f", mensagem.getPrecoNovo()) + "\n\n" +
            "Aproveite para comprar novamente com esse novo valor!\n" +
            "Atenciosamente,\nSistema de Vendas." + "\n\n\n\n\n"+
			"Sistema de Vendas criado por: Karli de Jesus Munoz Manzano."
        );
        email.setFrom("karlidejesus0106@gmail.com");

        try {
            mailSender.send(email);
            System.out.println("E-mail enviado com sucesso para: " + mensagem.getEmailCliente());
        } catch (Exception e) {
            System.err.println("Erro ao enviar e-mail para " + mensagem.getEmailCliente() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}