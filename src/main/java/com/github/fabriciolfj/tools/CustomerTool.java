package com.github.fabriciolfj.tools;

import com.github.fabriciolfj.adapters.bureau.BureauAdapter;
import com.github.fabriciolfj.adapters.bureau.CustomerFinancialResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerTool implements Tools {

    private final BureauAdapter bureauAdapter;

    @Tool(name = "bureauDetails", description = "details finance customer")
    public CustomerFinancialResponse process(@ToolParam(description = "code customer") final String codeCustomer) {
        log.info("get details customer {}", codeCustomer);

        return bureauAdapter.process(codeCustomer);
    }
}
