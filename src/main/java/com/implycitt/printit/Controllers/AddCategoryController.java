package com.implycitt.printit.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import com.implycitt.printit.Services.Database;

public class AddCategoryController
{
  @FXML
  private Button addCategory;

  @FXML
  private TextField nameTextField;

  @FXML
  protected void createCategory()
  {
    String categoryName = nameTextField.getText();
    if(categoryName != "")
    {
      Database.createTable(categoryName);
    } else {
      System.out.println("Field can not be empty!");
    }
  }
}
