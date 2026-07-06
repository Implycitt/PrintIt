package com.implycitt.printit.controllers;

import com.implycitt.printit.models.ItemLabel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;

import com.implycitt.printit.services.Database;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddLabelController implements Initializable
{
  @FXML
  private ChoiceBox<String> categoryChoiceBox;

  @FXML
  private TextField nameField;

  @FXML
  private TextField categoryField;

  @FXML
  private TextField typeField;

  @FXML
  private Button filePickerButton;

  @FXML
  private Button addLabelButton;

  @FXML
  private Text filePickerText;

  private File pickedFile;

  private Runnable onLabelAdded;

  private Integer editingId;

  private String editingCategory;

  @Override
  public void initialize(URL url, ResourceBundle rb)
  {
    refreshCategories();

    filePickerButton.setOnAction(e -> {
      FileChooser fileChooser = new FileChooser();
      FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("*", "*");
      fileChooser.getExtensionFilters().add(extFilter);

      Window window = filePickerButton.getScene().getWindow();
      File chosen = fileChooser.showOpenDialog(window);

      if (chosen != null) {
        pickedFile = chosen;
        filePickerText.setText("File: " + pickedFile.getName());
      }
    });
  }

  public void setOnLabelAdded(Runnable callback)
  {
    this.onLabelAdded = callback;
  }

  public void refreshCategories()
  {
    String previouslySelected = categoryChoiceBox.getValue();
    categoryChoiceBox.getItems().clear();
    categoryChoiceBox.getItems().addAll(Database.getAllTables());
    if (previouslySelected != null && categoryChoiceBox.getItems().contains(previouslySelected)) {
      categoryChoiceBox.setValue(previouslySelected);
    }
  }

  public void populateForEdit(ItemLabel label, String category)
  {
    editingId = label.id;
    editingCategory = category;

    nameField.setText(label.primaryName);
    categoryField.setText(label.turnArrayIntoText(label.subcategories));
    typeField.setText(label.type);

    if (category != null && categoryChoiceBox.getItems().contains(category)) {
      categoryChoiceBox.setValue(category);
    }

    if (label.url != null && !label.url.isEmpty()) {
      pickedFile = new File(label.url);
      filePickerText.setText("File: " + pickedFile.getName());
    }

    addLabelButton.setText("Save changes");
  }

  private void resetForm()
  {
    nameField.clear();
    categoryField.clear();
    typeField.clear();
    pickedFile = null;
    filePickerText.setText("No file selected");
    editingId = null;
    editingCategory = null;
    addLabelButton.setText("Add label");
  }

  @FXML
  protected void createLabel()
  {
    String labelName = nameField.getText();

    if (labelName == null || labelName.isEmpty()) {
      System.out.println("Field can not be empty!");
      return;
    }

    if (pickedFile == null) {
      System.out.println("Please choose a file first!");
      return;
    }

    ItemLabel itemLabel = new ItemLabel(labelName, "", pickedFile.getAbsolutePath(), categoryField.getText(), typeField.getText());

    if (editingId != null) {
      itemLabel.id = editingId;
      Database.upsertToDatabase(editingId, itemLabel, editingCategory);
    } else {
      Database.addToDatabase(itemLabel, categoryChoiceBox.getValue());
    }

    resetForm();

    if (onLabelAdded != null) {
      onLabelAdded.run();
    }
  }

}
