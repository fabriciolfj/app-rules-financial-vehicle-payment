
package com.github.fabriciolfj.repositories;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProposalDataRepository {

    private final DynamoDbTable<ProposalData> dataDynamoDbTable;

    public void save(final ProposalData data) {
        try {
            dataDynamoDbTable.putItem(data);

            log.info("proposal save successfully {}", data.proposal());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
