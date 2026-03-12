package com.github.fabriciolfj.adapters.dataanalyse;

import com.github.fabriciolfj.domain.entities.Proposal;
import com.github.fabriciolfj.domain.usecases.StartAnalyseProposalGateway;
import com.github.fabriciolfj.repositories.ProposalDataMapper;
import com.github.fabriciolfj.repositories.ProposalDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StartAnalyseProposalAdapter implements StartAnalyseProposalGateway {

    private final ProposalDataRepository proposalDataRepository;

    @Override
    public void process(final Proposal proposal) {
        final var data = ProposalDataMapper.toData(proposal);
        log.info("document mappead to repository {}", data);

        proposalDataRepository.save(data);
    }
}
