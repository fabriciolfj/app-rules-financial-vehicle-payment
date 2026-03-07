package com.github.fabriciolfj.adapters.ia;

import com.github.fabriciolfj.domain.entities.Proposal;
import com.github.fabriciolfj.domain.usecases.StartAnalyseProposalGateway;
import com.github.fabriciolfj.tools.CustomerTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.github.fabriciolfj.adapters.ia.ParamsEnrichMapper.buildParams;

@Slf4j
@Component
public class IAAnalyseAdapter implements StartAnalyseProposalGateway {

    private final Resource prompt;
    private final ChatClientAdapter chatClientAdapter;
    private final CustomerTool tool;
    private final SaveVectorAdapter saveVectorAdapter;

    public IAAnalyseAdapter(
            @Value("classpath:/promptTemplates/systemPromptTemplateAnalise.st")
            final Resource prompt,
            final ChatClientAdapter chatClientAdapter,
            final CustomerTool tool,
            final SaveVectorAdapter saveVectorAdapter) {
        this.prompt = prompt;
        this.chatClientAdapter = chatClientAdapter;
        this.tool = tool;
        this.saveVectorAdapter = saveVectorAdapter;
    }

    @Override
    public void process(final Proposal proposal) {
        final var result = chatClientAdapter.process(prompt, proposal, buildParams(proposal), List.of(tool));

        log.info("return analyse ia {}", result);
        saveVectorAdapter.process(result, proposal.getCode());
    }
}
