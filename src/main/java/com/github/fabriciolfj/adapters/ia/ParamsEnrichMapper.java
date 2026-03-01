package com.github.fabriciolfj.adapters.ia;

import com.github.fabriciolfj.domain.entities.Proposal;
import com.github.fabriciolfj.utils.ConstantsEnrichAnalyse;

import java.time.LocalDate;
import java.util.Map;

public class ParamsEnrichMapper {

    private ParamsEnrichMapper() { }

    public static Map<String, Object> buildParams(final Proposal proposal) {
        return Map.ofEntries(
                Map.entry(ConstantsEnrichAnalyse.CODE, proposal.getCode()),
                Map.entry(ConstantsEnrichAnalyse.VEHICLE_MODEL,      proposal.getVehicleModel()),
                Map.entry(ConstantsEnrichAnalyse.VEHICLE_YEAR,       String.valueOf(proposal.getVehicleYear())),
                Map.entry(ConstantsEnrichAnalyse.VEHICLE_VALUE,      proposal.getVehicleValue().toPlainString()),
                Map.entry(ConstantsEnrichAnalyse.FINANCED_AMOUNT,    proposal.getAmount().toPlainString()),
                Map.entry(ConstantsEnrichAnalyse.DOWN_PAYMENT,       proposal.getDownPayment().toPlainString()),
                Map.entry(ConstantsEnrichAnalyse.TERM_MONTHS,        String.valueOf(proposal.getTermMounts())),
                Map.entry(ConstantsEnrichAnalyse.REQUEST_DATE,       LocalDate.now().toString()),
                Map.entry(ConstantsEnrichAnalyse.CODE_CUSTOMER,      proposal.getCustomerDocument())
        );
    }
}
