package com.implycitt.printit.Components;

import com.implycitt.printit.Window;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.lang.classfile.Label;

public class LabelComponent extends VBox
{
  public LabelComponent()
  {
    FXMLLoader fxmlLoader = new FXMLLoader(Window.class.getResource("labelComponent.fxml"));
    fxmlLoader.setRoot(this);

    try {
      fxmlLoader.load();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
