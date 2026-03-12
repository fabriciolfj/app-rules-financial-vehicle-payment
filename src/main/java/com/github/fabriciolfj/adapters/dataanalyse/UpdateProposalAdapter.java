package com.github.fabriciolfj.adapters.dataanalyse;

import com.github.fabriciolfj.domain.entities.StatusProposal;
import com.github.fabriciolfj.domain.usecases.UpdateProposalGateway;
import com.github.fabriciolfj.repositories.ProposalDataMapper;
import com.github.fabriciolfj.repositories.ProposalDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateProposalAdapter implements UpdateProposalGateway {

    private final ProposalDataRepository proposalDataRepository;

    @Override
    public void process(StatusProposal statusProposal) {
        final var data = ProposalDataMapper.toData(statusProposal);
        proposalDataRepository.update(data);
    }
}
