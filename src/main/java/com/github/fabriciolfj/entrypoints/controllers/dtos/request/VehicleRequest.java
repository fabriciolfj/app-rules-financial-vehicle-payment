package com.github.fabriciolfj.entrypoints.controllers.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record VehicleRequest(@NotBlank(message = "modelo do veiculo e obrigatorio")
                             @Schema(description = "Modelo do veiculo", example = "FUSCA")
                             String model,
                             @NotNull(message = "ano do veiculo e obrigatorio")
                             @Min(value = 2000, message = "ano invalido")
                             @Schema(description = "Modelo do veiculo", example = "FUSCA")
                             Integer year,
                             @Min(value = 1000, message = "valor minimo invalido")
                             @Max(value = 999999999, message = "valor maximo invalido")
                             @Schema(description = "Valor do veiculo", example = "106000.00")
                             BigDecimal value,
                             @NotBlank(message = "condicao invalida")
                             @Schema(description = "Condicao do veiculo", example = "NOVO/USADO")
                             String condition,
                             @NotBlank(message = "finalidade do veiculo invalida")
                             @Schema(description = "Finalidade do veiculo", example = "PARTICULAR/COMERCIAL/TAXI_UBER")
                             String purpose) { }
