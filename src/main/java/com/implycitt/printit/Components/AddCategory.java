package com.implycitt.printit.Components;

import com.implycitt.printit.Window;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AddCategory extends AnchorPane
{
  public AddCategory()
  {
    FXMLLoader fxmlLoader = new FXMLLoader(Window.class.getResource("addCategory.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
