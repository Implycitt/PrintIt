package com.implycitt.printit.Components;

import com.implycitt.printit.Window;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AddLabel extends AnchorPane
{
  public AddLabel()
  {
    FXMLLoader fxmlLoader = new FXMLLoader(Window.class.getResource("addLabel.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
