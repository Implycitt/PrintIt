package com.implycitt.printit.Controllers;

import com.implycitt.printit.Components.AddCategory;
import com.implycitt.printit.Components.AddLabel;
import com.implycitt.printit.Components.LabelComponent;
import com.implycitt.printit.Models.ItemLabel;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;

import com.implycitt.printit.Services.Database;
import javafx.scene.text.Text;

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

  @FXML
  private TextField searchBar;

  ArrayList<String> tables = Database.getAllTables();
  ArrayList<ItemLabel> itemLabels;
  ArrayList<ItemLabel> allLabels = new ArrayList<>();

  public void initialize() {

    AddLabel label = new AddLabel();
    rightSidebar.getChildren().add(label);

    try {
      for (String table : Database.getAllTables())
      {
        allLabels.addAll(Database.getAllEntriesInTable(table));
      }
      for (ItemLabel itemLabel : allLabels)
      {
        LabelComponent currLabel = new LabelComponent(itemLabel.primaryName);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    for (ItemLabel itemLabel : allLabels) {
      LabelComponent newLabel = new LabelComponent(itemLabel.primaryName);
      labelsList.getChildren().add(newLabel);
    }

    labelTab.selectedProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue) {
        rightSidebar.getChildren().clear();
        rightSidebar.getChildren().add(label);
      }
    });

    categoryTab.selectedProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue) {
        AddCategory category = new AddCategory();
        rightSidebar.getChildren().clear();
        rightSidebar.getChildren().add(category);
      }
    });

    categoryList.getItems().addAll(tables);

    categoryList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      try {
        itemLabels = Database.getAllEntriesInTable(categoryList.getSelectionModel().getSelectedItem());
        labelsList.getChildren().clear();
        for (ItemLabel itemLabel : itemLabels) {
          LabelComponent newLabel = new LabelComponent(itemLabel.primaryName);
          labelsList.getChildren().add(newLabel);
        }
      } catch (SQLException e) {
        System.err.println(e.getMessage());
      }
    });

    searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.length() > 0) {
        labelsList.getChildren().clear();
        try {
          allLabels = Database.genericSearch(newValue);
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
        if (allLabels.size() == 0)
        {
          Text noLabels = new Text("No labels found...");
          labelsList.getChildren().clear();
          labelsList.getChildren().add(noLabels);
        }
        for (ItemLabel itemLabel : allLabels) {
          LabelComponent newLabel = new LabelComponent(itemLabel.primaryName);
          labelsList.getChildren().add(newLabel);
        }
      } else {
        try {
          labelsList.getChildren().clear();
          for (String table : Database.getAllTables())
          {
            allLabels.addAll(Database.getAllEntriesInTable(table));
          }
          for (ItemLabel itemLabel : allLabels)
          {
            LabelComponent currLabel = new LabelComponent(itemLabel.primaryName);
          }
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }
    });
  }

}


