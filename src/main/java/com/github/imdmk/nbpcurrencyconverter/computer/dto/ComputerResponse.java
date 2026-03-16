package com.github.imdmk.nbpcurrencyconverter.computer.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ComputerResponse(
        String name,
        LocalDate accountingDate,
        BigDecimal costUsd,
        BigDecimal costPln
) {}