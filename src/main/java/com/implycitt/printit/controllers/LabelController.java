package com.implycitt.printit.controllers;

import com.implycitt.printit.models.ItemLabel;
import com.implycitt.printit.services.Printing;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.function.BiConsumer;

public class LabelController {
  @FXML
  private Text LabelName;

  @FXML
  private Text categoryName;

  @FXML
  private Text Count;

  @FXML
  private ListView categoryList;

  private int counter;

  private ItemLabel itemLabel;
  private String category;

  private BiConsumer<ItemLabel, String> onEditRequested;
  private BiConsumer<ItemLabel, String> onDeleteRequested;

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
    if (itemLabel == null) return;

    if (Printing.getSelectedPrinter() == null) {
      showAlert(Alert.AlertType.WARNING, "No printer available",
        "No printer was detected. Connect a printer and try again.");
      return;
    }

    Printing.addToQueue(itemLabel);
    try {
      Printing.startPrintQueue();
      showAlert(Alert.AlertType.INFORMATION, "Print job sent",
        "\"" + itemLabel.primaryName + "\" was sent to " + Printing.getSelectedPrinter().getName() + ".");
    } catch (PrinterException | IOException e) {
      showAlert(Alert.AlertType.ERROR, "Print failed",
        "Could not print \"" + itemLabel.primaryName + "\": " + e.getMessage());
    }
  }

  private void showAlert(Alert.AlertType type, String title, String message)
  {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

  @FXML
  protected void RemoveLabel()
  {
    if (onDeleteRequested != null && itemLabel != null) {
      onDeleteRequested.accept(itemLabel, category);
    }
  }

  @FXML
  protected void EditLabel()
  {
    if (onEditRequested != null && itemLabel != null) {
      onEditRequested.accept(itemLabel, category);
    }
  }

  public void setOnEditRequested(BiConsumer<ItemLabel, String> callback)
  {
    this.onEditRequested = callback;
  }

  public void setOnDeleteRequested(BiConsumer<ItemLabel, String> callback)
  {
    this.onDeleteRequested = callback;
  }

  public void setItemLabel(ItemLabel itemLabel, String category)
  {
    this.itemLabel = itemLabel;
    this.category = category;
    setLabelName(itemLabel.primaryName);
    categoryName.setText(category != null ? "· " + category : "");
  }

  public void setLabelName(String name)
  {
    LabelName.setText(name);
  }
}
