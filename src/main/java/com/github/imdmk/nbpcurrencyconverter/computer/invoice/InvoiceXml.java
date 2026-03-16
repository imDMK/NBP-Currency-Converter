package com.github.imdmk.nbpcurrencyconverter.computer.invoice;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "faktura")
public final class InvoiceXml {

    @JacksonXmlElementWrapper(localName = "komputer", useWrapping = false)
    private List<ComputerXml> computer;

    public InvoiceXml() {}

    public InvoiceXml(List<ComputerXml> computer) {
        this.computer = computer;
    }

    public List<ComputerXml> getComputer() {
        return computer;
    }

    public void setComputer(List<ComputerXml> computer) {
        this.computer = computer;
    }
}