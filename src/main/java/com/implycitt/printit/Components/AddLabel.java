package com.implycitt.printit.Components;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AddLabel extends VBox
{

  public AddLabel()
  {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddLabel.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    try {
      fxmlLoader.load();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
