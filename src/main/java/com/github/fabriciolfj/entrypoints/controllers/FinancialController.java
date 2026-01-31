package com.github.fabriciolfj.entrypoints.controllers;

import com.github.fabriciolfj.entrypoints.controllers.dtos.DataFinancialRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/financial/v1")
public record FinancialController() {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "${api.financial.description}"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest}"),
            @ApiResponse(responseCode = "201", description = "${api.response-codes.creation}")
    })
    public void receiveInitFinancial(@Valid @RequestBody final DataFinancialRequest request) {
        log.info("receive payload financial {}", request);
    }
}
