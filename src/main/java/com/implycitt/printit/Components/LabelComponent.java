package com.implycitt.printit.Components;

import com.implycitt.printit.Controllers.LabelController;
import com.implycitt.printit.Window;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class LabelComponent extends VBox
{
  private LabelController controller;

  public LabelComponent(String name) {
    FXMLLoader fxmlLoader = new FXMLLoader(Window.class.getResource("labelComponent.fxml"));
    fxmlLoader.setRoot(this);

    try {
      fxmlLoader.load();
    } catch (IOException e) {
      System.out.println(e);
    }

    this.controller = fxmlLoader.getController();
    this.controller.setLabelName(name);
  }
}