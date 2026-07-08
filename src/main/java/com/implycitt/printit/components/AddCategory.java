package com.implycitt.printit.components;

import com.implycitt.printit.controllers.AddCategoryController;

import com.implycitt.printit.Window;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AddCategory extends AnchorPane
{

  private final AddCategoryController controller;

  public AddCategory()
  {
    FXMLLoader fxmlLoader = new FXMLLoader(Window.class.getResource("addCategory.fxml"));
    fxmlLoader.setRoot(this);

    try {
      fxmlLoader.load();
    } catch (IOException e) {
      System.out.println(e);
    }

    this.controller = fxmlLoader.getController();
  }

  public void setOnCategoryAdded(Runnable callback)
  {
    controller.setOnCategoryAdded(callback);
  }
}
