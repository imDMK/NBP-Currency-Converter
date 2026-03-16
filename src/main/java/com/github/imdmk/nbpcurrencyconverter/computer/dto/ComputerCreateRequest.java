package com.github.imdmk.nbpcurrencyconverter.computer.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ComputerCreateRequest(
        String name,
        BigDecimal usdCost,
        LocalDate date
) {
}
