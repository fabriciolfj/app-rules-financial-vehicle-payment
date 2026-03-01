package com.github.fabriciolfj.adapters.vector;

import com.github.fabriciolfj.adapters.ia.AIResponse;
import com.github.fabriciolfj.domain.entities.Proposal;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

@lombok.extern.slf4j.Slf4j
@Slf4j
@RequiredArgsConstructor
@Component
public class SaveVectorAdapter {

    private final VectorStore vectorStore;
    private final TokenTextSplitter tokenTextSplitter;

    public void process(final Proposal proposal, final AIResponse aiResponse) {
        final var context = buildContent(proposal, aiResponse);
        final var document = tokenTextSplitter.split(Document.builder()
                        .text(context)
                .build());

        vectorStore.accept(document);
        log.info("save response ai {}", proposal.getCode());
    }

    private String buildContent(final Proposal proposal, final AIResponse aiResponse) {
        return """
                CODE : %s
                ANSWER : s%
                STATUS : s%
                """.formatted(proposal.getCode(), aiResponse.answer(), aiResponse.status());
    }
}
