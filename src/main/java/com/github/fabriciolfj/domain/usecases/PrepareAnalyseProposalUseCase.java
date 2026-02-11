package com.github.fabriciolfj.domain.usecases;

import com.github.fabriciolfj.domain.entities.Proposal;
import com.github.fabriciolfj.domain.exceptions.PrepareAnalyseProposalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.StructuredTaskScope;

import static java.util.concurrent.StructuredTaskScope.Joiner.anySuccessfulResultOrThrow;

@Slf4j
@Service
public record PrepareAnalyseProposalUseCase(List<StartAnalyseProposalGateway> startAnalyseProposalGateways) {

    public void execute(final Proposal proposal) {
        try (var scope = StructuredTaskScope.open(anySuccessfulResultOrThrow())) {
            startAnalyseProposalGateways.forEach(c -> scope.fork(() -> c.process(proposal)));

            scope.join();
        } catch (InterruptedException e) {
            log.error("fail processo prepare analyse proposal {}, detail error: {}", proposal.getCode(), e.getMessage());

            throw new PrepareAnalyseProposalException();
        }
    }
}
