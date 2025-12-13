package com.implycitt.printit.Controllers;

import com.implycitt.printit.Components.AddCategory;
import com.implycitt.printit.Components.AddLabel;
import com.implycitt.printit.Components.LabelComponent;
import com.implycitt.printit.Models.ItemLabel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.ArrayList;

import static com.implycitt.printit.Services.Database.getAllEntriesInTable;
import static com.implycitt.printit.Services.Database.getAllTables;

public class MainController {

  @FXML
  private Tab labelTab;

  @FXML
  private Tab categoryTab;

  @FXML
  private AnchorPane rightSidebar;

  @FXML
  private ListView<String> categoryList;

  @FXML
  private VBox labelsList;

  ArrayList<String> tables = getAllTables();
  ArrayList<ItemLabel> itemLabels;

  public void initialize()
  {

    AddLabel label = new AddLabel();
    rightSidebar.getChildren().add(label);

    LabelComponent labelComponent = new LabelComponent();
    labelsList.getChildren().add(labelComponent);

    LabelComponent labelComponent2 = new LabelComponent();
    labelsList.getChildren().add(labelComponent2);

    labelTab.selectedProperty().addListener((observable, oldValue, newValue) -> {
      if(newValue)
      {
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

    categoryList.getItems().addAll(tables);

    categoryList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      try{
        itemLabels = getAllEntriesInTable(categoryList.getSelectionModel().getSelectedItem());
      } catch (SQLException e) {
        System.err.println(e.getMessage());
      }
    });
  }
}
