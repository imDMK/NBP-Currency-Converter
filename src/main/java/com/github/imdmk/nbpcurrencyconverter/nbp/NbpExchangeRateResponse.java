package com.github.imdmk.nbpcurrencyconverter.nbp;

import java.util.List;

public record NbpExchangeRateResponse(
        String table,
        String currency,
        String code,
        List<NbpRateDto> rates
) { }