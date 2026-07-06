package com.implycitt.printit.components;

import com.implycitt.printit.Window;
import com.implycitt.printit.controllers.AddLabelController;
import com.implycitt.printit.models.ItemLabel;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.io.UncheckedIOException;

public class AddLabel extends AnchorPane
{
  private final AddLabelController controller;

  public AddLabel()
  {
    FXMLLoader fxmlLoader = new FXMLLoader(Window.class.getResource("addLabel.fxml"));
    fxmlLoader.setRoot(this);

    try {
      fxmlLoader.load();
    } catch (IOException e) {
      throw new UncheckedIOException("Failed to load addLabel.fxml", e);
    }

    this.controller = fxmlLoader.getController();
  }

  public void setOnLabelAdded(Runnable callback)
  {
    controller.setOnLabelAdded(callback);
  }

  public void refreshCategories()
  {
    controller.refreshCategories();
  }

  public void populateForEdit(ItemLabel label, String category)
  {
    controller.populateForEdit(label, category);
  }
}
