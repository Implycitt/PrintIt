package com.implycitt.printit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import com.implycitt.printit.services.Database;

public class AddCategoryController
{
  @FXML
  private Button addCategory;

  @FXML
  private TextField nameTextField;

  private Runnable onCategoryAdded;

  public void setOnCategoryAdded(Runnable callback)
  {
    this.onCategoryAdded = callback;
  }

  @FXML
  protected void createCategory()
  {
    String categoryName = nameTextField.getText();
    if (categoryName != null && !categoryName.isEmpty())
    {
      Database.createTable(categoryName);
      nameTextField.clear();
      if (onCategoryAdded != null) {
        onCategoryAdded.run();
      }
    } else {
      System.out.println("Field can not be empty!");
    }
  }
}
