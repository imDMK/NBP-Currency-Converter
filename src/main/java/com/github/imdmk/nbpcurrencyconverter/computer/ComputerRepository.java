package com.github.imdmk.nbpcurrencyconverter.computer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
interface ComputerRepository extends JpaRepository<Computer, Long> {

    List<Computer> findByNameContainingIgnoreCase(String name);

    List<Computer> findByAccountingDate(LocalDate accountingDate);

}
