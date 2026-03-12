package com.github.fabriciolfj.domain.entities;


import java.util.stream.Stream;

public enum Status {

    APPROVE,
    PENDING,
    DENIED;


    public static String toStatus(final String status) {
        return Stream.of(Status.values())
                .map(Status::name)
                .filter(name -> name.equals(status))
                .findAny().orElse(status);
    }
}
