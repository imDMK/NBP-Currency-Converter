package com.github.imdmk.nbpcurrencyconverter.computer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "computers")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate accountingDate;

    private BigDecimal costUsd;
    private BigDecimal costPln;

    public Computer() {}

    public Computer(
            Long id,
            String name,
            LocalDate accountingDate,
            BigDecimal costUsd,
            BigDecimal costPln
    ) {
        this.id = id;
        this.name = name;
        this.accountingDate = accountingDate;
        this.costUsd = costUsd;
        this.costPln = costPln;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(LocalDate accountingDate) {
        this.accountingDate = accountingDate;
    }

    public BigDecimal getCostUsd() {
        return costUsd;
    }

    public void setCostUsd(BigDecimal costUsd) {
        this.costUsd = costUsd;
    }

    public BigDecimal getCostPln() {
        return costPln;
    }

    public void setCostPln(BigDecimal costPln) {
        this.costPln = costPln;
    }
}
