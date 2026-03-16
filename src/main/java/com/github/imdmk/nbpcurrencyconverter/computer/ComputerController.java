package com.github.imdmk.nbpcurrencyconverter.computer;

import com.github.imdmk.nbpcurrencyconverter.computer.dto.ComputerCreateRequest;
import com.github.imdmk.nbpcurrencyconverter.computer.dto.ComputerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/computers")
final class ComputerController {

    private final ComputerService computerService;

    ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @PostMapping("/init")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void initializeComputers() {
        computerService.createComputer(
                "ACER Aspire",
                new BigDecimal("345"),
                LocalDate.of(2026, 1, 5)
        );
        computerService.createComputer(
                "DELL Latitude",
                new BigDecimal("543"),
                LocalDate.of(2026, 1, 11)
        );
        computerService.createComputer(
                "HP Victus",
                new BigDecimal("346"),
                LocalDate.of(2026, 1, 19)
        );
    }

    @PostMapping
    public ComputerResponse create(
            @RequestBody ComputerCreateRequest request
    ) {
        return computerService.createComputer(
                request.name(),
                request.usdCost(),
                request.date()
        );
    }

    @PostMapping("/invoice")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void generateInvoice() {
        computerService.generateInvoice();
    }

    @GetMapping
    public List<ComputerResponse> getAll() {
        return computerService.getAllComputers();
    }

    @GetMapping("/search")
    public List<ComputerResponse> searchByName(@RequestParam String name) {
        return computerService.searchByName(name);
    }

    @GetMapping("/by-date")
    public List<ComputerResponse> findByDate(@RequestParam LocalDate date) {
        return computerService.findByDate(date);
    }

    @GetMapping("/sorted")
    public List<ComputerResponse> getSorted(@RequestParam String sort) {
        return computerService.getSorted(sort);
    }
}