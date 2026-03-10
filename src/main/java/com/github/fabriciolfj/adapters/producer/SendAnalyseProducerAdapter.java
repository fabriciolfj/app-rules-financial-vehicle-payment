package com.github.fabriciolfj.adapters.producer;

import com.github.fabriciolfj.domain.entities.Proposal;
import com.github.fabriciolfj.domain.usecases.NotifyAnalyseProposalGateway;
import com.github.fabriciolfj.properties.RabbitmqQueueProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class SendAnalyseProducerAdapter implements NotifyAnalyseProposalGateway {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitmqQueueProperties rabbitmqQueueProperties;

    public void process(final Proposal proposal) {
        final var dto = new ProposalCodeDto(proposal.getCode());

        rabbitTemplate.convertSendAndReceive(rabbitmqQueueProperties.getQueue(), dto);
        log.info("message send successfully {}", dto);
    }
}
