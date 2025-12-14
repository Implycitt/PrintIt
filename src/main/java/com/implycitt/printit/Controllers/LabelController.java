package com.implycitt.printit.Controllers;

import com.implycitt.printit.Models.ItemLabel;
import com.implycitt.printit.Services.Database;
import com.implycitt.printit.Services.Printing;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.awt.print.PrinterException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LabelController {
  @FXML
  private Text LabelName;

  @FXML
  private Text Count;

  @FXML
  private ListView categoryList;

  private int counter;

  @FXML
  protected void Increment()
  {
    counter++;
    Count.setText(Integer.toString(counter));
  }

  @FXML
  protected void Decrement()
  {
    if (counter > 1) counter--;
    Count.setText(Integer.toString(counter));
  }

  @FXML
  protected void PrintLabel() throws SQLException, PrinterException, IOException {
    ArrayList<ItemLabel> label = Database.genericSearch(LabelName.getText());
    Printing.addToQueue(label.getFirst());
    Printing.startPrintQueue();
  }

  @FXML
  protected void RemoveLabel()
  {
    System.out.println(LabelName);
  }

  @FXML
  protected void EditLabel()
  {
    System.out.println(LabelName);
  }

  public void setLabelName(String name)
  {
    LabelName.setText(name);
  }
}
