package com.matlb.rabbitMq;

/**
 * Created by prassingh on 6/19/16.
 */
import com.matlb.domain.ScrapingResultMessage;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration
{
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("123456Bittu#");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        final Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setClassMapper(classMapper());
        return converter;
    }

    @Bean
    public DefaultClassMapper classMapper() {
        DefaultClassMapper typeMapper = new DefaultClassMapper();
        typeMapper.setDefaultType(ScrapingResultMessage.class);
        return typeMapper;
    }
}
