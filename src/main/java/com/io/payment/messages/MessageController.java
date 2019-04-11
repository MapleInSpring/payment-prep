package com.io.payment.messages;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@AllArgsConstructor
@RequestMapping("/messages")
public class MessageController {

    private RabbitTemplate template;
    private Receiver receiver;

    @PostMapping("")
    public void sendMessage(@RequestBody MessageDTO message) throws InterruptedException {
        System.out.println("sending message " + message.getContent());

        template.convertAndSend(RabbitConfig.topicExchangeName, "foo.bar.baz", message.getContent());
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
}
