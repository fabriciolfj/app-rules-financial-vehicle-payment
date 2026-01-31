package com.github.fabriciolfj.entrypoints.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.fabriciolfj.annotation.ValidDocument;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CustomerRequest (
        @NotBlank(message = "{CUSTOMER.document}")
        @ValidDocument(message = "{CUSTOMER.documentInvalid}")
        @Schema(description = "CPF do cliente sem formatação", example = "12345678901")
        String document,
        @NotBlank(message = "email nao informado")
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "email invalido")
        @Schema(description = "Email do cliente", example = "joao.silva@email.com")
        String email,
        @JsonFormat(pattern = "YYYY-mm-DD")
        @NotBlank(message = "data nascimento nao informada")
        @Schema(description = "Data de nascimento do cliente", example = "2000-09-29")
        String birdDate
){
}
