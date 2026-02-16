package com.github.fabriciolfj.adapters;

import com.github.fabriciolfj.domain.entities.Proposal;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChatClientAdapter {

    private final ChatClient chatClient;

    public AIResponse process(final Resource template, final Proposal proposal) {
        return chatClient.prompt()
                .system(it -> it.param("", ""))
                .user(proposal.getCode())
                .advisors(it -> it.param(ChatMemory.CONVERSATION_ID, proposal.getCode()))
                .call()
                .responseEntity(AIResponse.class)
                .entity();
    }
}
