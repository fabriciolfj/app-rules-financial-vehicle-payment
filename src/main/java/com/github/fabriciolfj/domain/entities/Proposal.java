package com.github.fabriciolfj.domain.entities;

import lombok.Builder;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class Proposal {

    private String code;
    private Customer customer;
    private Financial financial;
    private Vehicle vehicle;
    private Status status;

    public String getCustomerDocument() {
        return customer.document();
    }

    public String getCustomerEmail() {
        return customer.email();
    }

    public LocalDate getCustomerBirthDate() {
        return customer.birtDate();
    }

    public BigDecimal getAmount() {
        return financial.amount();
    }

    public BigDecimal getDownPayment() {
        return financial.downPayment();
    }

    public int getTermMounts() {
        return financial.termMounts();
    }

    public String getStatus() {
        return status.name();
    }

    public String getVehicleModel() {
        return vehicle.model();
    }

    public Integer getVehicleYear() {
        return vehicle.year();
    }

    public BigDecimal getVehicleValue() {
        return vehicle.value();
    }

    public String getVehicleCondition() {
        return vehicle.condition().getDescription();
    }

    public String getVehiclePurpose() {
        return vehicle.purpose()
                .getDescription();
    }
}
