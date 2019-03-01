package com.q8888.springboot.mybatis.server.messageq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @auther xuxq
 * @date 2019/3/1 11:16
 */
@Service
public class MQService {

    @RabbitListener(queues = "fanout.queue")
    public void receive (Message message) {
        System.out.println("收到消息 : " + new String(message.getBody()));
    }
}
