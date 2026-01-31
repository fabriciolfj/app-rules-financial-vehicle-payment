package com.github.fabriciolfj.entrypoints.advice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Builder
@JsonInclude(NON_NULL)
public class ErrorDTO {

    private int code;
    private String message;
    private List<ErrorDetailsDTO> details;
}
