package com.github.fabriciolfj.domain.usecases;


import com.github.fabriciolfj.domain.entities.Proposal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record OrchestratorUseCase(PrepareAnalyseProposalUseCase prepareAnalyseProposalUseCase,
                                  NotifyAnalyseProposalGateway notifyAnalyseProposalGateway) {

    public void execute(final Proposal proposal) {
        final var result = prepareAnalyseProposalUseCase.execute(proposal);

        notifyAnalyseProposalGateway.process(result);
        log.info("process proposal successfully {}", result.getCode());
    }
}
