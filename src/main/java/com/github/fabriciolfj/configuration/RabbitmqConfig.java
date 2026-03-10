package com.github.fabriciolfj.configuration;

import com.github.fabriciolfj.properties.RabbitmqProperties;
import com.github.fabriciolfj.properties.RabbitmqQueueProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.JacksonJavaTypeMapper;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class RabbitmqConfig {

    private final RabbitmqProperties rabbitmqProperties;
    private final RabbitmqQueueProperties rabbitmqQueueProperties;

    @Bean("rabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(final ConnectionFactory connectionFactory) {
        final var container = new SimpleRabbitListenerContainerFactory();
        container.setMessageConverter(jsonMessageConverter());
        container.setConnectionFactory(connectionFactory);
        container.setMaxConcurrentConsumers(rabbitmqProperties.getConcurrencyMax());
        container.setConcurrentConsumers(rabbitmqProperties.getConcurrencyMin());

        return container;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        final var converter = new JacksonJsonMessageConverter();
        converter.setTypePrecedence(JacksonJavaTypeMapper.TypePrecedence.INFERRED);

        return converter;
    }

    @Bean
    public Queue queueAnalyse() {
        return QueueBuilder.durable(rabbitmqQueueProperties.getQueue()).build();
    }

    @Bean
    public Queue queueDlqAnalyse() {
        final Map<String, Object> args = Map.of(
                "x-dead-letter-exchange", "",
                "x-dead-letter-routing-key", rabbitmqQueueProperties.getDlq()
        );

        return QueueBuilder
                .durable(rabbitmqQueueProperties.getDlq())
                .withArguments(args)
                .build();
    }

    @Bean
    public Exchange exchangeAnalyse() {
        return ExchangeBuilder.directExchange(rabbitmqQueueProperties.getQueue())
                .build();
    }

    @Bean
    public Binding bindingAnalyse() {
        return BindingBuilder
                .bind(queueAnalyse())
                .to(exchangeAnalyse())
                .with(rabbitmqQueueProperties.getQueue())
                .noargs();
    }
}
