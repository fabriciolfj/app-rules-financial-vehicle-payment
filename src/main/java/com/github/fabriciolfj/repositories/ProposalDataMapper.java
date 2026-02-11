package com.github.fabriciolfj.repositories;

import com.github.fabriciolfj.domain.entities.Proposal;

public class ProposalDataMapper {

    private ProposalDataMapper() { }

    public static ProposalData toData(final Proposal proposal) {
        return new ProposalData(
                proposal.getCode(),
                proposal.getCustomerDocument(),
                proposal.getCustomerEmail(),
                proposal.getStatus(),
                proposal.getCustomerBirthDate(),
                proposal.getAmount(),
                proposal.getDownPayment(),
                proposal.getTermMounts(),
                proposal.getVehicleModel(),
                proposal.getVehicleYear(),
                proposal.getVehicleValue(),
                proposal.getVehicleCondition(),
                proposal.getVehiclePurpose()
        );
    }
}
