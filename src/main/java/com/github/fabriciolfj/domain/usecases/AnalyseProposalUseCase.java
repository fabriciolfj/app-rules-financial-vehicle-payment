package com.github.fabriciolfj.domain.usecases;

import com.github.fabriciolfj.domain.entities.Status;
import com.github.fabriciolfj.domain.entities.StatusProposal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalyseProposalUseCase {

    private final UpdateProposalGateway updateProposalGateway;

    public void execute(final String proposal, final String customer, final String status) {
        final var statusEnum = Status.toStatus(status);
        final var statusProposal = new StatusProposal(proposal, customer, statusEnum);

        updateProposalGateway.process(statusProposal);
    }
}
