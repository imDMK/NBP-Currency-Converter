package com.github.imdmk.nbpcurrencyconverter.computer;

import com.github.imdmk.nbpcurrencyconverter.nbp.NbpClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public final class CurrencyService {

    private final NbpClient nbpClient;

    public CurrencyService(NbpClient nbpClient) {
        this.nbpClient = nbpClient;
    }

    public BigDecimal convertUsdToPln(BigDecimal usdAmount, LocalDate date) {
        BigDecimal rate = nbpClient.getUsdExchangeRate(date);
        return usdAmount.multiply(rate);
    }
}