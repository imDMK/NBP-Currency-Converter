package com.github.imdmk.nbpcurrencyconverter.computer;

import com.github.imdmk.nbpcurrencyconverter.computer.dto.ComputerResponse;
import com.github.imdmk.nbpcurrencyconverter.computer.invoice.InvoiceXmlService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ComputerService {

    private final ComputerRepository repository;

    private final CurrencyService currencyService;
    private final InvoiceXmlService invoiceXmlService;

    ComputerService(
            ComputerRepository repository,
            CurrencyService currencyService,
            InvoiceXmlService invoiceXmlService
    ) {
        this.repository = repository;
        this.currencyService = currencyService;
        this.invoiceXmlService = invoiceXmlService;
    }

    @Transactional
    public ComputerResponse createComputer(
            String name,
            BigDecimal usdCost,
            LocalDate date
    ) {
        BigDecimal plnCost = currencyService.convertUsdToPln(usdCost, date);

        Computer computer = new Computer();
        computer.setName(name);
        computer.setCostUsd(usdCost);
        computer.setCostPln(plnCost);
        computer.setAccountingDate(date);

        return toResponse(repository.save(computer));
    }

    public void generateInvoice() {
        List<Computer> computers = repository.findAll();
        if (computers.isEmpty()) {
            throw new IllegalStateException("No computers found to generate invoice");
        }

        invoiceXmlService.export(computers);
    }

    public List<ComputerResponse> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ComputerResponse> findByDate(LocalDate date) {
        return repository.findByAccountingDate(date)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ComputerResponse> getSorted(String sort) {
        return repository.findAll(Sort.by(sort))
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ComputerResponse> getAllComputers() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ComputerResponse toResponse(Computer computer) {
        return new ComputerResponse(
                computer.getName(),
                computer.getAccountingDate(),
                computer.getCostUsd(),
                computer.getCostPln()
        );
    }
}