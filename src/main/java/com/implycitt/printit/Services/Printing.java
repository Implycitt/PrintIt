package com.implycitt.printit.Services;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.*;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import com.implycitt.printit.Models.ItemLabel;
import javafx.print.Printer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.printing.PDFPageable;

import javafx.print.PaperSource;
import javafx.print.PrinterAttributes;

import java.awt.print.PrinterJob;
import java.io.File;

  public class Printing
  {

    public static Queue<ItemLabel> printerQueue = new ArrayDeque<ItemLabel>();
    public static Printer defaultPrinter = Printer.getDefaultPrinter();
    public static Set<PaperSource> supportedSources = getPrinterSources();
    public static PaperSource defaultSource = supportedSources.iterator().next();

    public void addToQueue(ItemLabel itemLabel)
    {
      printerQueue.add(itemLabel);
    }

    public void startPrintQueue() throws PrinterException, IOException
    {
      while (!printerQueue.isEmpty())
      {
        ItemLabel topLabel = printerQueue.remove();
        printSingle(topLabel.url);
      }
    }

    public static void printSingle(String location) throws IOException, PrinterException {
      File printedFile = new File(location);
      PDDocument document = Loader.loadPDF(printedFile);

      PrinterJob printJob = PrinterJob.getPrinterJob();
      printJob.setPageable(new PDFPageable(document));
      printJob.print();
    }

    public static PrintService[] getPrintersAvailable()
    {
      return PrintServiceLookup.lookupPrintServices(null, null);
    }

    public static void setPrinter(Printer printer)
    {
      defaultPrinter = printer;
    }

    public static Set<PaperSource> getPrinterSources()
    {
      return defaultPrinter.getPrinterAttributes().getSupportedPaperSources();
    }

    public static void setPrinterSource(PaperSource newSource)
    {
      defaultSource = newSource;
    }
}
