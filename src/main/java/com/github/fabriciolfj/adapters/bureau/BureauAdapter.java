package com.github.fabriciolfj.adapters.bureau;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
@RequiredArgsConstructor
public class BureauAdapter {

    private final BureauClient bureauClient;

    public CustomerFinancialResponse process(final String codeCustomer) {
        log.info("request financial customer {}", codeCustomer);
        return bureauClient.find(codeCustomer);
    }
}
