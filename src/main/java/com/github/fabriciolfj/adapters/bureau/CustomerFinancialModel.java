package com.github.fabriciolfj.adapters.bureau;

import java.math.BigDecimal;

public record CustomerFinancialModel(
        BigDecimal monthlyIncome,
        BigDecimal currentDebtRatio,
        Integer creditScore,
        boolean defaultHistory) {}