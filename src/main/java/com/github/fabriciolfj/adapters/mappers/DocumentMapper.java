package com.github.fabriciolfj.adapters.mappers;

import com.github.fabriciolfj.adapters.ia.AIResponse;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TextSplitter;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class DocumentMapper {

    private DocumentMapper() { }

    public static List<Document> toDocument(final AIResponse aiResponse, final String codeProposal, final TextSplitter textSplitter) {
        final Map<String, Object> map = toMap(aiResponse, codeProposal);
        return textSplitter.split(new Document(aiResponse.answer(), map));
    }

    private static Map<String, Object> toMap(final AIResponse response, final String codeProposal) {
        return Map.of(
                "code", codeProposal,
                "status", response.status(),
                "createdAt", Instant.now().toEpochMilli()
        );
    }
}
