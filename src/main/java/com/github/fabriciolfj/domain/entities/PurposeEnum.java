package com.github.fabriciolfj.domain.entities;

import com.github.fabriciolfj.domain.exceptions.PurposeNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum PurposeEnum {
    COMERCIAL("comercial"),
    PARTICULAR("particular"),
    TAXI_UBER("taxi_uber");

    private final String description;

    public static PurposeEnum toEnum(final String description) {
        return Stream.of(PurposeEnum.toEnum(description))
                .filter( v -> v.description.equals(description))
                .findFirst()
                .orElseThrow(PurposeNotFoundException::new);
    }
}
