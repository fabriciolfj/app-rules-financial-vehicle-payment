package com.github.fabriciolfj.tools;

import com.github.fabriciolfj.adapters.bureau.BureauAdapter;
import com.github.fabriciolfj.adapters.bureau.CustomerFinancialModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerTool {

    private final BureauAdapter bureauAdapter;

    @Tool(name = "bureauDetails", description = "details finance customer")
    public CustomerFinancialModel getDetailsFinance(@ToolParam(description = "code customer") final String code) {
        log.info("get details customer {}", code);

        return bureauAdapter.process(code);
    }
}
