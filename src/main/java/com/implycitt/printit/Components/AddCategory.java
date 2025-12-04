package com.implycitt.printit.Components;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.Console;
import java.io.IOException;

public class AddCategory extends VBox
{
  public AddCategory()
  {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddCategory.fxml"));
    fxmlLoader.setController(this);
    fxmlLoader.setRoot(this);

    try {
      fxmlLoader.load();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
