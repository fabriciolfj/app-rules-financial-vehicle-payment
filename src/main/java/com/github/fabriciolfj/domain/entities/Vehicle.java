package com.github.fabriciolfj.domain.entities;

import java.math.BigDecimal;

public record Vehicle(String model,
                      Integer year,
                      BigDecimal value,
                      ConditionVehicleEnum condition,
                      PurposeEnum purpose) { }
