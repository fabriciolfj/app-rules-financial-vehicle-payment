package com.github.fabriciolfj.repositories;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@DynamoDbBean
public class ProposalData {
        private String proposal;
        private String customer;
        private String email;
        private String status;
        private LocalDate customerBirthDate;
        private BigDecimal financialAmount;
        private BigDecimal financialDownPayment;
        private Integer financialTermMounts;
        private String vehicleModel;
        private Integer vehicleYear;
        private BigDecimal vehicleValue;
        private String vehicleCondition;
        private String vehiclePurpose;

        public ProposalData() {
        }

        public ProposalData(String proposal, String customer, String email, String status,
                            LocalDate customerBirthDate, BigDecimal financialAmount,
                            BigDecimal financialDownPayment, Integer financialTermMounts,
                            String vehicleModel, Integer vehicleYear, BigDecimal vehicleValue,
                            String vehicleCondition, String vehiclePurpose) {
                this.proposal = proposal;
                this.customer = customer;
                this.email = email;
                this.status = status;
                this.customerBirthDate = customerBirthDate;
                this.financialAmount = financialAmount;
                this.financialDownPayment = financialDownPayment;
                this.financialTermMounts = financialTermMounts;
                this.vehicleModel = vehicleModel;
                this.vehicleYear = vehicleYear;
                this.vehicleValue = vehicleValue;
                this.vehicleCondition = vehicleCondition;
                this.vehiclePurpose = vehiclePurpose;
        }

        @DynamoDbSortKey
        @DynamoDbSecondarySortKey(indexNames = "StatusIndex")
        public String getProposal() {
                return proposal;
        }

        public void setProposal(String proposal) {
                this.proposal = proposal;
        }

        @DynamoDbPartitionKey
        @DynamoDbAttribute("customer")
        public String getCustomer() {
                return customer;
        }

        public void setCustomer(String customer) {
                this.customer = customer;
        }

        @DynamoDbSecondaryPartitionKey(indexNames = "EmailIndex")
        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        @DynamoDbSecondaryPartitionKey(indexNames = "StatusIndex")
        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }

        @DynamoDbAttribute("customer_birth_date")
        public LocalDate getCustomerBirthDate() {
                return customerBirthDate;
        }

        public void setCustomerBirthDate(LocalDate customerBirthDate) {
                this.customerBirthDate = customerBirthDate;
        }

        @DynamoDbAttribute("financial_amount")
        public BigDecimal getFinancialAmount() {
                return financialAmount;
        }

        public void setFinancialAmount(BigDecimal financialAmount) {
                this.financialAmount = financialAmount;
        }

        @DynamoDbAttribute("financial_down_payment")
        public BigDecimal getFinancialDownPayment() {
                return financialDownPayment;
        }

        public void setFinancialDownPayment(BigDecimal financialDownPayment) {
                this.financialDownPayment = financialDownPayment;
        }

        @DynamoDbAttribute("financial_term_mounts")
        public Integer getFinancialTermMounts() {
                return financialTermMounts;
        }

        public void setFinancialTermMounts(Integer financialTermMounts) {
                this.financialTermMounts = financialTermMounts;
        }

        @DynamoDbAttribute("vehicle_model")
        public String getVehicleModel() {
                return vehicleModel;
        }

        public void setVehicleModel(String vehicleModel) {
                this.vehicleModel = vehicleModel;
        }

        @DynamoDbAttribute("vehicle_year")
        public Integer getVehicleYear() {
                return vehicleYear;
        }

        public void setVehicleYear(Integer vehicleYear) {
                this.vehicleYear = vehicleYear;
        }

        @DynamoDbAttribute("vehicle_value")
        public BigDecimal getVehicleValue() {
                return vehicleValue;
        }

        public void setVehicleValue(BigDecimal vehicleValue) {
                this.vehicleValue = vehicleValue;
        }

        @DynamoDbAttribute("vehicle_condition")
        public String getVehicleCondition() {
                return vehicleCondition;
        }

        public void setVehicleCondition(String vehicleCondition) {
                this.vehicleCondition = vehicleCondition;
        }

        @DynamoDbAttribute("vehicle_purpose")
        public String getVehiclePurpose() {
                return vehiclePurpose;
        }

        public void setVehiclePurpose(String vehiclePurpose) {
                this.vehiclePurpose = vehiclePurpose;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                ProposalData that = (ProposalData) o;
                return Objects.equals(proposal, that.proposal) &&
                        Objects.equals(customer, that.customer);
        }

        @Override
        public int hashCode() {
                return Objects.hash(proposal, customer);
        }

        @Override
        public String toString() {
                return "ProposalData{" +
                        "proposal='" + proposal + '\'' +
                        ", customer='" + customer + '\'' +
                        ", email='" + email + '\'' +
                        ", status='" + status + '\'' +
                        '}';
        }
}