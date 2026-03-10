package com.github.fabriciolfj.adapters.ia;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public record FindProposalVectorAdapter(VectorStore vectorStore) {

    public Optional<Document> process(final String codeProposal) {
        var filter = new FilterExpressionBuilder()
                .eq("code", codeProposal)
                .build();

        var searchRequest = SearchRequest.builder()
                .query(codeProposal)
                .topK(10)
                .filterExpression(filter)
                .similarityThreshold(0.0)
                .build();

        return vectorStore.similaritySearch(searchRequest)
                .stream()
                .max(Comparator.comparingLong(doc ->
                        (Long) doc.getMetadata().getOrDefault("createdAt", 0L)));
    }
}
