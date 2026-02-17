
package com.github.fabriciolfj.repositories;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProposalDataRepository {

    private final DynamoDbTable<ProposalData> dataDynamoDbTable;

    public void save(final ProposalData data) {
        try {
            var existing = findByKey(data.getCustomer(), data.getProposal());

            if (existing != null) {
                dataDynamoDbTable.updateItem(data);
                log.info("proposal updated successfully {}", data.getProposal());
                return;
            }

            dataDynamoDbTable.putItem(data);
            log.info("proposal saved successfully {}", data.getProposal());

        } catch (Exception e) {
            log.error("error saving proposal {}", data.getProposal(), e);
            throw new RuntimeException("error saving proposal", e);
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
