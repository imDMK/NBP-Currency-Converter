package com.github.imdmk.nbpcurrencyconverter.computer.invoice;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.imdmk.nbpcurrencyconverter.computer.Computer;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
public final class InvoiceXmlService {

    private final XmlMapper xmlMapper;

    public InvoiceXmlService(XmlMapper xmlMapper) {
        this.xmlMapper = xmlMapper;
    }

    public void export(List<Computer> computers) {
        try {
            List<ComputerXml> xmlComputers = computers.stream()
                    .map(c -> new ComputerXml(
                            c.getName(),
                            c.getAccountingDate(),
                            c.getCostUsd(),
                            c.getCostPln()
                    ))
                    .collect(Collectors.toList());

            InvoiceXml invoice = new InvoiceXml(xmlComputers);
            xmlMapper.writeValue(new File("invoice.xml"), invoice);
        } catch (Exception e) {
            throw new RuntimeException("Failed to export XML", e);
        }
    }
}