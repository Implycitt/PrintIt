package com.implycitt.printit.Controllers;

import com.implycitt.printit.Models.ItemLabel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;

import com.implycitt.printit.Services.Database;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddLabelController implements Initializable
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
  private Button filePickerButton;

  @FXML
  private Text filePickerText;

  Window primaryStage;

  File pickedFile;

  ArrayList<String> categories = Database.getAllTables();

  @Override
  public void initialize(URL url, ResourceBundle rb)
  {
    categoryChoiceBox.getItems().addAll(categories);


    filePickerButton.setOnAction(e -> {
      FileChooser fileChooser = new FileChooser();
      FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("*", "*");
      fileChooser.getExtensionFilters().add(extFilter);
      pickedFile = fileChooser.showOpenDialog(primaryStage);
      filePickerText.setText("file: " + pickedFile.getAbsolutePath());
    });
  }

  @FXML
  protected void createLabel()
  {
    String labelName = nameField.getText();
    if(labelName != "")
    {
      ItemLabel itemLabel = new ItemLabel(nameField.getText(), "", pickedFile.getAbsolutePath(), categoryField.getText(), typeField.getText());
      System.out.println(categoryChoiceBox.getValue());
      Database.addToDatabase(itemLabel, (String) categoryChoiceBox.getValue());
    } else {
      System.out.println("Field can not be empty!");
    }
  }

}
