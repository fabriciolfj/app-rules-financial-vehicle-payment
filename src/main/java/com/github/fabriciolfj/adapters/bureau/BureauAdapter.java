package com.github.fabriciolfj.adapters.bureau;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BureauAdapter {

    public CustomerFinancialModel process(final String code) {
        return new CustomerFinancialModel(
                new BigDecimal("8500.00"),
                new BigDecimal("25.50"),
                720,
                false
        );
    }
}
