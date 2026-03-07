package com.github.fabriciolfj.adapters.producer;

import com.github.fabriciolfj.properties.RabbitmqQueueProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Slf4j
@RequiredArgsConstructor
public class SendAnalyseProducerAdapter {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitmqQueueProperties rabbitmqQueueProperties;

    public void process(final String proposalCode) {
        final var proposal = new ProposalCodeDto(proposalCode);

        rabbitTemplate.convertSendAndReceive(rabbitmqQueueProperties.getQueue(), proposal);
        log.info("message send successfully {}", proposalCode);
    }
}
