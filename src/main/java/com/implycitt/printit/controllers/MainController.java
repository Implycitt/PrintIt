package com.implycitt.printit.controllers;

import com.implycitt.printit.components.AddCategory;
import com.implycitt.printit.components.AddLabel;
import com.implycitt.printit.components.LabelComponent;
import com.implycitt.printit.models.ItemLabel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;

import com.implycitt.printit.services.Database;
import javafx.scene.text.Text;

public class MainController {

  @FXML
  private ListView<String> categoryList;

  @FXML
  private VBox labelsList;

  @FXML
  private TextField searchBar;

  @FXML
  private Tab labelTab;

  @FXML
  private AddLabel addLabelPane;

  @FXML
  private AddCategory addCategoryPane;

  ArrayList<ItemLabel> allLabels = new ArrayList<>();

  public void initialize() {

    refreshCategories();
    refreshLabels();

    categoryList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      refreshLabels();
    });

    searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
      refreshLabels();
    });

    addLabelPane.setOnLabelAdded(this::refreshLabels);
    addCategoryPane.setOnCategoryAdded(() -> {
      refreshCategories();
      addLabelPane.refreshCategories();
    });
  }

  /** Reloads the category list from the database. */
  private void refreshCategories() {
    String previouslySelected = categoryList.getSelectionModel().getSelectedItem();
    categoryList.getItems().setAll(Database.getAllTables());
    if (previouslySelected != null && categoryList.getItems().contains(previouslySelected)) {
      categoryList.getSelectionModel().select(previouslySelected);
    }
  }

  private void refreshLabels() {
    String searchText = searchBar.getText();
    String selectedCategory = categoryList.getSelectionModel().getSelectedItem();

    try {
      if (searchText != null && !searchText.isEmpty()) {
        allLabels = Database.genericSearch(searchText);
      } else if (selectedCategory != null) {
        allLabels = Database.getAllEntriesInTable(selectedCategory);
      } else {
        allLabels = new ArrayList<>();
        for (String table : Database.getAllTables()) {
          allLabels.addAll(Database.getAllEntriesInTable(table));
        }
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return;
    }

    labelsList.getChildren().clear();
    if (allLabels.isEmpty()) {
      labelsList.getChildren().add(new Text("No labels found..."));
      return;
    }
    for (ItemLabel itemLabel : allLabels) {
      LabelComponent newLabel = new LabelComponent(itemLabel);
      newLabel.setOnEditRequested(this::handleEditRequest);
      labelsList.getChildren().add(newLabel);
    }
  }

  @FXML
  protected void clearCategorySelection() {
    categoryList.getSelectionModel().clearSelection();
    refreshLabels();
  }

  private void handleEditRequest(ItemLabel label) {
    addLabelPane.populateForEdit(label, (String) categoryList.getSelectionModel().getSelectedItem());
    labelTab.getTabPane().getSelectionModel().select(labelTab);
  }

}
