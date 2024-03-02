package com.ashwinbhatt.systemdesign.numberprinter;

public class Main {

    public static void main(String[] args) {

        int n = 10;
        State state = new State(PrinterTypeEnum.ODD);
        Printer oddPrinter = new Printer(1, 2, n, state, PrinterTypeEnum.ODD, PrinterTypeEnum.EVEN);
        Printer evenPrinter = new Printer(2, 2, n, state, PrinterTypeEnum.EVEN, PrinterTypeEnum.ODD);

        Thread oddThread = new Thread(oddPrinter);
        Thread evenThread = new Thread(evenPrinter);

        evenThread.start();
        oddThread.start();

    }
}
