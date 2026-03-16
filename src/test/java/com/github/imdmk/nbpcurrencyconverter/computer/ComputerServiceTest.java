package com.github.imdmk.nbpcurrencyconverter.computer;

import com.github.imdmk.nbpcurrencyconverter.computer.dto.ComputerResponse;
import com.github.imdmk.nbpcurrencyconverter.computer.invoice.InvoiceXmlService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ComputerServiceTest {

    private ComputerRepository repository;
    private CurrencyService currencyService;
    private InvoiceXmlService invoiceXmlService;

    private ComputerService computerService;

    @BeforeEach
    void setup() {
        repository = Mockito.mock(ComputerRepository.class);
        currencyService = Mockito.mock(CurrencyService.class);
        invoiceXmlService = Mockito.mock(InvoiceXmlService.class);

        computerService = new ComputerService(repository, currencyService, invoiceXmlService);
    }

    @Test
    void shouldCreateComputer() {
        // given
        String name = "ACER Aspire";
        BigDecimal usd = new BigDecimal("100");
        LocalDate date = LocalDate.of(2026, 1, 5);

        BigDecimal pln = new BigDecimal("400");

        when(currencyService.convertUsdToPln(usd, date)).thenReturn(pln);

        Computer savedComputer = new Computer();
        savedComputer.setName(name);
        savedComputer.setAccountingDate(date);
        savedComputer.setCostUsd(usd);
        savedComputer.setCostPln(pln);

        when(repository.save(any())).thenReturn(savedComputer);

        // when
        ComputerResponse result = computerService.createComputer(name, usd, date);

        // then
        assertThat(result.name()).isEqualTo(name);
        assertThat(result.accountingDate()).isEqualTo(date);
        assertThat(result.costUsd()).isEqualByComparingTo("100");
        assertThat(result.costPln()).isEqualByComparingTo("400");
    }

    @Test
    void shouldGenerateInvoice() {
        // given
        Computer computer = new Computer();
        computer.setName("ACER");

        when(repository.findAll()).thenReturn(List.of(computer));

        // when
        computerService.generateInvoice();

        // then
        verify(invoiceXmlService).export(anyList());
    }

    @Test
    void shouldThrowExceptionWhenGeneratingInvoiceWithoutComputers() {
        // given
        when(repository.findAll()).thenReturn(List.of());

        // then
        org.junit.jupiter.api.Assertions.assertThrows(
                IllegalStateException.class,
                () -> computerService.generateInvoice()
        );
    }

    @Test
    void shouldSearchByName() {
        // given
        Computer computer = new Computer();
        computer.setName("ACER");

        when(repository.findByNameContainingIgnoreCase("acer"))
                .thenReturn(List.of(computer));

        // when
        List<ComputerResponse> result = computerService.searchByName("acer");

        // then
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().name()).isEqualTo("ACER");
    }

    @Test
    void shouldFindByDate() {
        // given
        LocalDate date = LocalDate.of(2026, 1, 5);

        Computer computer = new Computer();
        computer.setAccountingDate(date);

        when(repository.findByAccountingDate(date))
                .thenReturn(List.of(computer));

        // when
        List<ComputerResponse> result = computerService.findByDate(date);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().accountingDate()).isEqualTo(date);
    }
}