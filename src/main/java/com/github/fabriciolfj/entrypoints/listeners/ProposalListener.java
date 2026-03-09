package com.github.fabriciolfj.entrypoints.listeners;

import com.github.fabriciolfj.commons.NotifyAnalyseProposalDTO;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProposalListener {

    @RabbitListener(queues = "${rabbitmq.analyse.queue}", containerFactory = "rabbitListenerContainerFactory")
    public void receive(final NotifyAnalyseProposalDTO dto, final Channel channel) {
        log.info("receive message {}", dto);
    }
}
