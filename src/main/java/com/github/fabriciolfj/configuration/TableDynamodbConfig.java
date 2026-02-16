package com.github.fabriciolfj.configuration;


import com.github.fabriciolfj.repositories.ProposalData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Configuration
public class TableDynamodbConfig {

    private static final String TABLE_PROPOSAL = "proposal";

    @Bean
    public DynamoDbTable<ProposalData> proposalDataDynamoDbTable(final DynamoDbEnhancedClient client) {
        return client.table(TABLE_PROPOSAL, TableSchema.fromBean(ProposalData.class));
    }
}
