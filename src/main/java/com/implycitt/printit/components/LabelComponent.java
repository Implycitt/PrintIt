package com.implycitt.printit.components;

import com.implycitt.printit.controllers.LabelController;
import com.implycitt.printit.models.ItemLabel;
import com.implycitt.printit.Window;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.function.Consumer;

public class LabelComponent extends VBox
{
  private final LabelController controller;

  public LabelComponent(ItemLabel itemLabel) {
    FXMLLoader fxmlLoader = new FXMLLoader(Window.class.getResource("labelComponent.fxml"));
    fxmlLoader.setRoot(this);

    try {
      fxmlLoader.load();
    } catch (IOException e) {
      throw new UncheckedIOException("Failed to load labelComponent.fxml", e);
    }

    this.controller = fxmlLoader.getController();
    this.controller.setItemLabel(itemLabel);
  }

  public void setOnEditRequested(Consumer<ItemLabel> callback)
  {
    controller.setOnEditRequested(callback);
  }
}
