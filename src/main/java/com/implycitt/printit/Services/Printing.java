package com.implycitt.printit.Services;

import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.*;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.printing.PDFPageable;

import javafx.print.PaperSource;
import javafx.print.PrinterAttributes;

import java.awt.print.PrinterJob;
import java.io.File;

  public class Printing
  {

    public static Queue<String> printerQueue = new ArrayDeque<String>();
    public static PrinterJob printJob = PrinterJob.getPrinterJob();
    public static PrintService defaultPrinter = null;
    public static PaperSource printerSource = null;

    public void addToQueue(String path)
    {
      printerQueue.add(path);
    }

    public void startPrintQueue() throws PrinterException, IOException {
      while (!printerQueue.isEmpty())
      {
        printSingle();
      }
    }

    public static void printSingle() throws PrinterException, IOException {
      File file = new File(printerQueue.remove());
      PDDocument document = Loader.loadPDF(file);
      printJob.setPageable(new PDFPageable(document));
      printJob.setPrintService(defaultPrinter);
      printJob.print();
    }


    public static PrintService[] getPrintersAvailable()
    {
      return PrintServiceLookup.lookupPrintServices(null, null);
    }

    public static void setPrinter(PrintService printer)
    {
      defaultPrinter = printer;
    }

    public static Set<PaperSource> getPrinterSources()
    {
      return null;
    }

    public static void setPrinterSource(PaperSource newSource)
    {
      printerSource = newSource;
    }
}
