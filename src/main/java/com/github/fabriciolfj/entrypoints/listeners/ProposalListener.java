package com.github.fabriciolfj.entrypoints.listeners;

import com.github.fabriciolfj.adapters.ia.FindProposalVectorAdapter;
import com.github.fabriciolfj.commons.NotifyAnalyseProposalDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class ProposalListener {

    private final FindProposalVectorAdapter findProposalVectorAdapter;

    @RabbitListener(queues = "${rabbitmq.analyse.queue}", containerFactory = "rabbitListenerContainerFactory")
    public void receive(final NotifyAnalyseProposalDTO dto) {
        log.info("receive message {}", dto);

        var result = findProposalVectorAdapter.process(dto.code());

        if (result.isEmpty()) {
            return;
        }

        log.info(result.get().getText());
        log.info(result.get().getMetadata().get("status").toString());
    }
}
