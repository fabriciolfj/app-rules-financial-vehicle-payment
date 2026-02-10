package com.github.fabriciolfj.domain.entities;

import java.math.BigDecimal;

public record Financial(BigDecimal amount,
                        BigDecimal downPayment,
                        Integer termMounts) { }
