package com.github.fabriciolfj.adapters.bureau;

import java.math.BigDecimal;

public record CustomerFinancialResponse(
        BigDecimal monthlyIncome,
        BigDecimal currentDebtRatio,
        Integer creditScore,
        DefaultHistory defaultHistory) {

    public record DefaultHistory(
            boolean hasRecentDefault,
            Integer defaultCount,
            Integer maxDelayDays,
            boolean hasActiveRestrictions) { }
}