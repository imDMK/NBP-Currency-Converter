package com.github.imdmk.nbpcurrencyconverter.computer;

import com.github.imdmk.nbpcurrencyconverter.nbp.NbpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class CurrencyServiceTest {

    private NbpClient nbpClient;
    private CurrencyService currencyService;

    @BeforeEach
    void setup() {
        nbpClient = Mockito.mock(NbpClient.class);
        currencyService = new CurrencyService(nbpClient);
    }

    @Test
    void shouldConvertUsdToPln() {
        // given
        LocalDate date = LocalDate.of(2026, 1, 5);
        BigDecimal usdAmount = new BigDecimal("100");
        BigDecimal rate = new BigDecimal("4.00");

        when(nbpClient.getUsdExchangeRate(date))
                .thenReturn(rate);

        // when
        BigDecimal result = currencyService.convertUsdToPln(usdAmount, date);

        // then
        assertThat(result).isEqualByComparingTo("400.00");
    }

    @Test
    void shouldCallNbpClientToGetExchangeRate() {
        // given
        LocalDate date = LocalDate.of(2026, 1, 5);
        BigDecimal usdAmount = new BigDecimal("50");

        when(nbpClient.getUsdExchangeRate(date))
                .thenReturn(new BigDecimal("3.5"));

        // when
        currencyService.convertUsdToPln(usdAmount, date);

        // then
        Mockito.verify(nbpClient).getUsdExchangeRate(date);
    }
}