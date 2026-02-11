package com.github.fabriciolfj.repositories;


import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@DynamoDbBean
public record ProposalData(
        @DynamoDbPartitionKey
        @DynamoDbSecondaryPartitionKey(indexNames = "StatusIndex")
        String proposal,
        @DynamoDbSortKey
        @DynamoDbAttribute("customer_document")
        @DynamoDbSecondaryPartitionKey(indexNames = "CustomerIndex")
        String customer,
        @DynamoDbSecondaryPartitionKey(indexNames = "EmailIndex")
        String email,
        @DynamoDbSecondarySortKey(indexNames = "StatusIndex")
        String status,
        @DynamoDbAttribute("customer_birth_date")
        LocalDate customerBirthDate,
        @DynamoDbAttribute("financial_amount")
        BigDecimal financialAmount,
        @DynamoDbAttribute("financial_down_payment")
        BigDecimal financialDownPayment,
        @DynamoDbAttribute("financial_term_mounts")
        Integer financialTermMounts,
        @DynamoDbAttribute("vehicle_model")
        String vehicleModel,
        @DynamoDbAttribute("vehicle_year")
        Integer vehicleYear,
        @DynamoDbAttribute("vehicle_value")
        BigDecimal vehicleValue,
        @DynamoDbAttribute("vehicle_condition")
        String vehicleCondition,
        @DynamoDbAttribute("vehicle_purpose")
        String vehiclePurpose
) { }
