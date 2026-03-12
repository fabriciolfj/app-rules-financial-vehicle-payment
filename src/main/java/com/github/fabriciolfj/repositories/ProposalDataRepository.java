
package com.github.fabriciolfj.repositories;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.IgnoreNullsMode;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.UpdateItemEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.ConditionalCheckFailedException;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProposalDataRepository {

    private final DynamoDbTable<ProposalData> dataDynamoDbTable;

    public void save(final ProposalData data) {
        log.info("saving proposal {}", data.getProposal());

        var expression = Expression.builder()
                .expression("attribute_not_exists(customer) AND attribute_not_exists(proposal)")
                .build();

        var putItemRequest = PutItemEnhancedRequest.builder(ProposalData.class)
                .item(data)
                .conditionExpression(expression)
                .build();

        try {
            dataDynamoDbTable.putItem(putItemRequest);
            log.info("proposal saved successfully {}", data.getProposal());
        } catch (ConditionalCheckFailedException e) {
            log.warn("proposal {} already exists for customer {}, updating...", data.getProposal(), data.getCustomer());
            update(data);
        } catch (Exception e) {
            log.error("error saving proposal {}", data.getProposal(), e);
            throw new RuntimeException("error saving proposal", e);
        }
    }

    public void update(final ProposalData data) {
        try {
            dataDynamoDbTable.updateItem(UpdateItemEnhancedRequest.builder(ProposalData.class)
                    .item(data)
                    .ignoreNullsMode(IgnoreNullsMode.MAPS_ONLY)
                    .build());
            log.info("proposal updated successfully {}", data.getProposal());
        } catch (Exception e) {
            log.error("error updating proposal {}", data.getProposal(), e);
            throw new RuntimeException("error updating proposal", e);
        }
    }

    private ProposalData findByKey(final String customer, final String proposal) {
        try {
            var key = Key.builder()
                    .partitionValue(customer)
                    .sortValue(proposal)
                    .build();

            return dataDynamoDbTable.getItem(key);
        } catch (Exception e) {
            return null;
        }
    }
}
