package com.github.fabriciolfj.repositories;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
public record ProposalData(
        @DynamoDbPartitionKey
        String proposal,
        @DynamoDbSortKey
        @DynamoDbSecondaryPartitionKey(indexNames = "CustomerIndex")
        String customer,
        @DynamoDbSecondaryPartitionKey(indexNames = "EmailIndex")
        String email
) { }
