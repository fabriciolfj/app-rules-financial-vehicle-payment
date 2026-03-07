package com.github.fabriciolfj.adapters.ia;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

import static com.github.fabriciolfj.adapters.mappers.DocumentMapper.toDocument;

@Slf4j
@Component
public record SaveVectorAdapter(VectorStore vectorStore,
                                TextSplitter textSplitter) {

    public void process(final AIResponse aiResponse, final String proposalCode) {
      final var documents = toDocument(aiResponse, proposalCode, textSplitter);

      vectorStore.accept(documents);
      log.info("save successfully vector to proposal {}", proposalCode);
    }
}
