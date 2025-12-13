package com.implycitt.printit.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class LabelController {
  @FXML
  private Text LabelName;

  @FXML
  private Text Count;

  @FXML
  private ListView categoryList;

  private int counter;

  @FXML
  private void initialize()
  {
    System.out.println(LabelName);
  }

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
  protected void PrintLabel()
  {
    System.out.println("going to have to rewrite some stuff");
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
}
