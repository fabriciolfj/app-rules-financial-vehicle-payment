package com.github.fabriciolfj.adapters.ia;

import com.github.fabriciolfj.domain.entities.Proposal;
import com.github.fabriciolfj.tools.Tools;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class ChatClientAdapter {

    private final ChatClient chatClient;

    public AIResponse process(final Resource template, final Proposal proposal, final Map<String, Object> params, final List<Tools> tools) {
        return chatClient.prompt()
                .system(it -> it.text(template)
                        .params(params))
                .user(proposal.getCode())
                .advisors(it -> it.param(ChatMemory.CONVERSATION_ID, proposal.getCode()))
                .toolCallbacks(ToolCallbacks.from(tools.toArray()))
                .call()
                .responseEntity(AIResponse.class)
                .entity();
    }
}
