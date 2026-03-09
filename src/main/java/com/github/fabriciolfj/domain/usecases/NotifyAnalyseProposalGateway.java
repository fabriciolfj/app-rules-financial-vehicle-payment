package com.github.fabriciolfj.domain.usecases;

import com.github.fabriciolfj.domain.entities.Proposal;

public interface NotifyAnalyseProposalGateway {

    void process(final Proposal proposal);
}
