package com.implycitt.printit.services;

import com.implycitt.printit.models.ItemLabel;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Media;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

public class Printing
{
  public static final Queue<ItemLabel> printerQueue = new ArrayDeque<>();

  private static PrintService selectedPrinter;

  private static Media selectedMedia;

  public static void addToQueue(ItemLabel itemLabel)
  {
    printerQueue.add(itemLabel);
  }

  public static void startPrintQueue() throws PrinterException, IOException
  {
    while (!printerQueue.isEmpty())
    {
      ItemLabel topLabel = printerQueue.remove();
      printSingle(topLabel.url);
    }
  }

  public static void printSingle(String location) throws IOException, PrinterException
  {
    File printedFile = new File(location);

    try (PDDocument document = Loader.loadPDF(printedFile)) {
      PrinterJob printJob = PrinterJob.getPrinterJob();
      printJob.setJobName(printedFile.getName());
      printJob.setPageable(new PDFPageable(document));

      PrintService printService = getSelectedPrinter();
      if (printService != null) {
        printJob.setPrintService(printService);
      }

      printJob.print(buildPrintRequestAttributes());
    }
  }

  public static PrintService[] getPrintersAvailable()
  {
    return PrintServiceLookup.lookupPrintServices(null, null);
  }

  public static PrintService getSelectedPrinter()
  {
    if (selectedPrinter == null) {
      selectedPrinter = PrintServiceLookup.lookupDefaultPrintService();
    }
    return selectedPrinter;
  }

  public static void setPrinter(PrintService printer)
  {
    selectedPrinter = printer;
    selectedMedia = null;
  }

  public static Media[] getSupportedMedia()
  {
    PrintService printer = getSelectedPrinter();
    if (printer == null) {
      return new Media[0];
    }
    Object supported = printer.getSupportedAttributeValues(Media.class, null, null);
    return supported instanceof Media[] ? (Media[]) supported : new Media[0];
  }

  public static Media getSelectedMedia()
  {
    if (selectedMedia == null) {
      PrintService printer = getSelectedPrinter();
      if (printer != null) {
        selectedMedia = (Media) printer.getDefaultAttributeValue(Media.class);
      }
    }
    return selectedMedia;
  }

  public static void setMedia(Media media)
  {
    selectedMedia = media;
  }

  private static PrintRequestAttributeSet buildPrintRequestAttributes()
  {
    PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
    Media media = getSelectedMedia();
    if (media != null) {
      attributes.add(media);
    }
    return attributes;
  }
}
