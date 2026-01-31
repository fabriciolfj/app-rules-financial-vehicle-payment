#!/bin/sh

ENDPOINT="http://localstack:4566"

# Tabela de Usuários com GSI e LSI
aws dynamodb create-table \
    --table-name propose \
    --attribute-definitions \
        AttributeName=code,AttributeType=S \
        AttributeName=proposal,AttributeType=S \
        AttributeName=email,AttributeType=S \
        AttributeName=status,AttributeType=S \
    --key-schema \
        AttributeName=code,KeyType=HASH \
        AttributeName=proposal,KeyType=RANGE \
    --local-secondary-indexes \
        "[{
            \"IndexName\": \"StatusIndex\",
            \"KeySchema\": [
                {\"AttributeName\":\"code\",\"KeyType\":\"HASH\"},
                {\"AttributeName\":\"status\",\"KeyType\":\"RANGE\"}
            ],
            \"Projection\": {\"ProjectionType\":\"ALL\"}
        }]" \
    --global-secondary-indexes \
            "[{
                \"IndexName\": \"ProposalIndex\",
                \"KeySchema\": [
                    {\"AttributeName\":\"proposal\",\"KeyType\":\"HASH\"}
                ],
                \"Projection\": {\"ProjectionType\":\"ALL\"}
            }]" \
    --global-secondary-indexes \
        "[{
            \"IndexName\": \"EmailIndex\",
            \"KeySchema\": [
                {\"AttributeName\":\"email\",\"KeyType\":\"HASH\"}
            ],
            \"Projection\": {\"ProjectionType\":\"ALL\"},
            \"ProvisionedThroughput\": {
                \"ReadCapacityUnits\": 5,
                \"WriteCapacityUnits\": 5
            }
        }]" \
    --provisioned-throughput \
        ReadCapacityUnits=5,WriteCapacityUnits=5 \
    --endpoint-url $ENDPOINT \
    --region us-east-1

echo "✅ Tabela propose criada"
