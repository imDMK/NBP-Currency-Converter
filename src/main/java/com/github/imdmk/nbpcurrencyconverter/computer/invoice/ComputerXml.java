package com.github.imdmk.nbpcurrencyconverter.computer.invoice;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class ComputerXml {

    @JacksonXmlProperty(localName = "nazwa")
    private String name;

    @JacksonXmlProperty(localName = "data_ksiegowania")
    private LocalDate accountingDate;

    @JacksonXmlProperty(localName = "koszt_USD")
    private BigDecimal costUsd;

    @JacksonXmlProperty(localName = "koszt_PLN")
    private BigDecimal costPln;

    public ComputerXml() {}

    public ComputerXml(String name, LocalDate accountingDate, BigDecimal costUsd, BigDecimal costPln) {
        this.name = name;
        this.accountingDate = accountingDate;
        this.costUsd = costUsd;
        this.costPln = costPln;
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