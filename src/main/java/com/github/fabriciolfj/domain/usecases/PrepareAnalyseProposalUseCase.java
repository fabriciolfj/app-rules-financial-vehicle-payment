package com.github.fabriciolfj.domain.usecases;

import com.github.fabriciolfj.domain.entities.Proposal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public record PrepareAnalyseProposalUseCase(List<StartAnalyseProposalGateway> startAnalyseProposalGateways) {

    public void execute(final Proposal proposal) {
        startAnalyseProposalGateways.forEach(c -> c.process(proposal));
    }
}
