package com.ashwinbhatt.systemdesign.numberprinter;

public class State {

    private PrinterTypeEnum nextToPrint;

    public State (PrinterTypeEnum nextToPrint) {
        this.nextToPrint = nextToPrint;
    }

    public PrinterTypeEnum getNextPrinterType() {
        return this.nextToPrint;
    }

    public void setNextPrinterType(PrinterTypeEnum nextPrinterType) {
        this.nextToPrint = nextPrinterType;
    }
}
