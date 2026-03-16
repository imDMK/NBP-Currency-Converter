package com.github.imdmk.nbpcurrencyconverter.nbp;

import com.github.imdmk.nbpcurrencyconverter.exception.NbpApiException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public final class NbpClient {

    private static final String BASE_URL = "https://api.nbp.pl/api/exchangerates/rates/A/USD/";
    private static final int FALLBACK_DAYS = 7;

    private final RestClient restClient;

    public NbpClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public BigDecimal getUsdExchangeRate(LocalDate date) {
        LocalDate startDate = date.minusDays(FALLBACK_DAYS);
        String uri = BASE_URL + startDate + "/" + date + "?format=json";

        NbpExchangeRateResponse response = restClient.get()
                .uri(uri)
                .retrieve()
                .body(NbpExchangeRateResponse.class);

        if (response == null || response.rates().isEmpty()) {
            throw new NbpApiException("No exchange rate returned from NBP API");
        }

        List<NbpRateDto> rates = response.rates();
        return rates.getLast().mid();
    }
}