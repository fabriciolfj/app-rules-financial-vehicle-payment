package com.github.fabriciolfj.entrypoints.controllers.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record FinancialRequest(@Min(value = 1000, message = "valor financiamento invalido")
                               @Schema(description = "Valor de financiamento do veiculo", example = "106000.00")
                               BigDecimal financedAmount,
                               @Positive(message = "valor entrada invalido")
                               @Schema(description = "Valor da entrada", example = "10000.00")
                               BigDecimal downPayment,
                               @Positive(message = "quantidade parcelas invalida")
                               @Schema(description = "Prazo em meses", example = "48")
                               Integer termMounts) {
}
