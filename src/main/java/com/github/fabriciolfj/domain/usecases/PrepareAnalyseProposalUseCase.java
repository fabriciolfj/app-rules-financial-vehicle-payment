package com.github.fabriciolfj.domain.usecases;

import com.github.fabriciolfj.domain.entities.Proposal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.StructuredTaskScope;


@Slf4j
@Service
public record PrepareAnalyseProposalUseCase(List<StartAnalyseProposalGateway> startAnalyseProposalGateways) {

    public void execute(final Proposal proposal) {
        try (var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.allSuccessfulOrThrow())) {
            startAnalyseProposalGateways.forEach(c -> scope.fork(() -> c.process(proposal)));

            scope.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
