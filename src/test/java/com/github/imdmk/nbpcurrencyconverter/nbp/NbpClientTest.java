package com.github.imdmk.nbpcurrencyconverter.nbp;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class NbpClientTest {

    private RestClient.Builder builder;
    private MockRestServiceServer server;

    @BeforeEach
    void setup() {
        builder = RestClient.builder();
        server = MockRestServiceServer.bindTo(builder).build();
    }

    @Test
    void shouldReturnLastExchangeRate() {
        String jsonResponse = """
                {
                  "table": "A",
                  "currency": "dolar amerykański",
                  "code": "USD",
                  "rates": [
                    {
                      "no": "001/A/NBP/2026",
                      "effectiveDate": "2026-01-04",
                      "mid": 3.90
                    },
                    {
                      "no": "002/A/NBP/2026",
                      "effectiveDate": "2026-01-05",
                      "mid": 4.00
                    }
                  ]
                }
                """;

        server.expect(requestTo(Matchers.containsString("USD")))
                .andRespond(withSuccess(jsonResponse, MediaType.APPLICATION_JSON));

        NbpClient nbpClient = new NbpClient(builder.build());

        BigDecimal rate = nbpClient.getUsdExchangeRate(LocalDate.of(2026, 1, 5));

        assertThat(rate).isEqualByComparingTo("4.00");

        server.verify();
    }

    @Test
    void shouldThrowExceptionWhenRatesEmpty() {
        String jsonResponse = """
            {
              "table": "A",
              "currency": "dolar amerykański",
              "code": "USD",
              "rates": []
            }
            """;

        server.expect(requestTo(Matchers.containsString("USD")))
                .andRespond(withSuccess(jsonResponse, MediaType.APPLICATION_JSON));

        NbpClient nbpClient = new NbpClient(builder.build());

        assertThrows(
                RuntimeException.class,
                () -> nbpClient.getUsdExchangeRate(LocalDate.of(2026, 1, 5))
        );

        server.verify();
    }
}