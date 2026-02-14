package com.github.fabriciolfj.entrypoints.controllers.mapper;

import com.github.fabriciolfj.domain.entities.*;
import com.github.fabriciolfj.entrypoints.controllers.dtos.DataFinancialRequest;

import java.time.LocalDate;
import java.util.UUID;

public class ProposalMapper {

    private ProposalMapper() { }

    public static Proposal toEntity(final DataFinancialRequest request) {
        final var vehicleRequest = request.vehicle();
        final var condition = ConditionVehicleEnum.toEnum(
                vehicleRequest.condition()
        );
        final var purpose = PurposeEnum.toEnum(
                vehicleRequest.purpose()
        );

        final var customerRequest = request.customer();
        final var financialRequest = request.financial();
        return Proposal.builder()
                .code(UUID.randomUUID().toString())
                .vehicle(new Vehicle(vehicleRequest.model(), vehicleRequest.year(), vehicleRequest.value(), condition, purpose))
                .customer(new Customer(customerRequest.document(), LocalDate.parse(customerRequest.birdDate()), customerRequest.email()))
                .financial(new Financial(financialRequest.financedAmount(), financialRequest.downPayment(), financialRequest.termMounts()))
                .status(Status.PENDING)
                .build();
    }
}
