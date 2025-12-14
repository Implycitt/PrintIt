package com.implycitt.printit.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;

import com.implycitt.printit.Services.Database;

public class AddLabelController 
{
  @FXML
  private ChoiceBox categoryChoiceBox;

  @FXML
  private TextField nameField;

  @FXML
  private TextField categoryField;

  @FXML
  private TextField typeField;

  @FXML
  protected void createLabel()
  {
    String labelName = (String) nameField.getText();
    if(labelName != "")
    {
      System.out.println("this works");
      //Database.addToDatabase(labelName);
    } else {
      System.out.println("Field can not be empty!");
    }
  }
}
