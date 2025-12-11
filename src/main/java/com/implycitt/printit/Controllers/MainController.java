package com.implycitt.printit.Controllers;

import com.implycitt.printit.Components.AddCategory;
import com.implycitt.printit.Components.AddLabel;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainController {

  @FXML
  private Tab labelTab;

  @FXML
  private Tab categoryTab;

  @FXML
  private AnchorPane rightSidebar;

  public void initialize()
  {
    labelTab.selectedProperty().addListener((observable, oldValue, newValue) -> {
      if(newValue)
      {
        AddLabel label = new AddLabel();
        rightSidebar.getChildren().clear();
        rightSidebar.getChildren().add(label);
      }
    });

    categoryTab.selectedProperty().addListener((observable, oldValue, newValue) -> {
      if(newValue)
      {
        AddCategory category = new AddCategory();
        rightSidebar.getChildren().clear();
        rightSidebar.getChildren().add(category);
      }
    });

  }
}
