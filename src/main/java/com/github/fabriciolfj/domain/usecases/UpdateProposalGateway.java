package com.github.fabriciolfj.domain.usecases;

import com.github.fabriciolfj.domain.entities.StatusProposal;

public interface UpdateProposalGateway {

    void process(final StatusProposal statusProposal);
}
