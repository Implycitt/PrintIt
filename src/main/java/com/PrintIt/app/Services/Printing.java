package com.PrintIt.app.Services;

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

  public void startPrintQueue()
  {
    while (printerQueue.size() != 0)
    {
      printSingle();
    }
  }

  public static void printSingle()
  {
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
    return PrinterAttributes.getSupportedPaperSources(); 
  }

  public static void setPrinterSource(PaperSource newSource)
  {
    printerSource = newSource;
  }
}
