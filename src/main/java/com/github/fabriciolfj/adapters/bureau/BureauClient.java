package com.github.fabriciolfj.adapters.bureau;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "bureau.url", accept = "application/json")
public interface BureauClient {

    @GetExchange("/{code}")
    CustomerFinancialResponse find(@PathVariable("code") final String customerCode);
}
