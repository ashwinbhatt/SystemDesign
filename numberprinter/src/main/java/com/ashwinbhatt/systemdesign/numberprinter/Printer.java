package com.ashwinbhatt.systemdesign.numberprinter;

public class Printer implements Runnable {

    private final State state;
    private final PrinterTypeEnum printerType;
    private final PrinterTypeEnum nextPrinterType;
    private int currentVal;
    private final int incrementer;
    private final int maxVal;

    public Printer(int currentVal, int incrementer, int maxVal, State state, PrinterTypeEnum printerType, PrinterTypeEnum nextPrinterType) {
        this.currentVal = currentVal;
        this.incrementer = incrementer;
        this.state = state;
        this.printerType = printerType;
        this.nextPrinterType = nextPrinterType;
        this.maxVal = maxVal;
    }

    @Override
    public void run() {
        while(currentVal <= maxVal) {
            synchronized (state) {
                if(!printerType.equals(state.getNextPrinterType())) {
                    try {
                        state.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(printerType + " " + currentVal);
                currentVal+=incrementer;
                state.setNextPrinterType(nextPrinterType);
                state.notifyAll();
            }
        }
    }
}
