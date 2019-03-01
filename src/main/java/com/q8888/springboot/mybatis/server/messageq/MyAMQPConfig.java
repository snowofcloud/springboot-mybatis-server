package com.q8888.springboot.mybatis.server.messageq;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther xuxq
 * @date 2019/3/1 11:11
 */
@Configuration
public class MyAMQPConfig {

    @Bean
    public MessageConverter messageConverter () {
        return new Jackson2JsonMessageConverter();
    }
}
