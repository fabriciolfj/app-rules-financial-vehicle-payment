package com.github.fabriciolfj.domain.entities;

import com.github.fabriciolfj.domain.exceptions.ConditionVehicleNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static java.util.stream.Stream.of;

@Getter
@RequiredArgsConstructor
public enum ConditionVehicleEnum {

    ZERO_KM("novo"),
    USADO("usado");

    private final String description;


    public static ConditionVehicleEnum toEnum(final String description) {
        return of(ConditionVehicleEnum.values())
                .filter(v -> v.description.equalsIgnoreCase(description))
                .findFirst()
                .orElseThrow(ConditionVehicleNotFoundException::new);
    }
}
