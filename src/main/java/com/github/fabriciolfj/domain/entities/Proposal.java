package com.github.fabriciolfj.domain.entities;

import lombok.Builder;

@Builder
public class Proposal {

    private String code;
    private Customer customer;
    private Financial financial;
    private Vehicle vehicle;
    private Status status;
}
