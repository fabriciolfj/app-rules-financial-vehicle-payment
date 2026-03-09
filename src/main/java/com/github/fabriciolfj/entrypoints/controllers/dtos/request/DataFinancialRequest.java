package com.github.fabriciolfj.entrypoints.controllers.dtos.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DataFinancialRequest(
        @NotNull(message = "cliente nao informado")
        @Schema(description = "Dados do cliente")
        @Valid CustomerRequest customer,
        @NotNull(message = "dados para financiamento nao informados")
        @Schema(description = "Dados do financiamento")
        @Valid FinancialRequest financial,
        @NotNull(message = "dados do veiculo nao informado")
        @Schema(description = "Dados do veiculo")
        @Valid VehicleRequest vehicle) { }
