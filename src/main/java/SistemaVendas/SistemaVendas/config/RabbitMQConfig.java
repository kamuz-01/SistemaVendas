package SistemaVendas.SistemaVendas.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "notificacao-preco-reduzido";

    @Bean
    public Queue notificacaoQueue() {
        return new Queue(QUEUE_NAME, true); // Fila durável
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);

        // Configurar o conversor de mensagens
        SimpleMessageConverter converter = new SimpleMessageConverter();
        converter.setAllowedListPatterns(Arrays.asList("SistemaVendas.SistemaVendas.model.NotificacaoMessage"));
        factory.setMessageConverter(converter);

        return factory;
    }
}