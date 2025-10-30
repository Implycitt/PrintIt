package com.PrintIt.app.Services;

import java.util.*;
import java.awt.print.PrinterJob;

public class Printing
{

  static Queue<String> printerQueue = new ArrayDeque<String>();

  public void addToQueue(String path)
  {
    printerQueue.add(path);
  }

  public void removeFromQueue()
  {
    printerQueue.remove();
  }

  public void startPrintQueue()
  {
    while (printerQueue.size() != 0)
    {
      printSingle();
      removeFromQueue();
    }
  }

  public static void printSingle()
  {
    // making sure that this pushes
  }

  public static void setPrinterProperties()
  {
    // TODO
  }
}
