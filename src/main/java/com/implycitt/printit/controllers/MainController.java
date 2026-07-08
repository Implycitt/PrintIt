package com.implycitt.printit.controllers;

import com.implycitt.printit.components.AddCategory;
import com.implycitt.printit.components.AddLabel;
import com.implycitt.printit.components.LabelComponent;
import com.implycitt.printit.models.ItemLabel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

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
  private Button clearSearchButton;

  @FXML
  private Tab labelTab;

  @FXML
  private AddLabel addLabelPane;

  @FXML
  private AddCategory addCategoryPane;

  public void initialize() {

    refreshCategories();
    refreshLabels();

    clearSearchButton.visibleProperty().bind(searchBar.textProperty().isNotEmpty());
    clearSearchButton.managedProperty().bind(clearSearchButton.visibleProperty());

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

    labelsList.getChildren().clear();
    boolean anyResults = false;

    try {
      if (searchText != null && !searchText.isEmpty()) {
        for (String table : Database.getAllTables()) {
          for (ItemLabel itemLabel : Database.searchForEntries(table, searchText)) {
            addLabelCard(itemLabel, table);
            anyResults = true;
          }
        }
      } else if (selectedCategory != null) {
        for (ItemLabel itemLabel : Database.getAllEntriesInTable(selectedCategory)) {
          addLabelCard(itemLabel, selectedCategory);
          anyResults = true;
        }
      } else {
        for (String table : Database.getAllTables()) {
          for (ItemLabel itemLabel : Database.getAllEntriesInTable(table)) {
            addLabelCard(itemLabel, table);
            anyResults = true;
          }
        }
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return;
    }

    if (!anyResults) {
      labelsList.getChildren().add(buildEmptyState(searchText, selectedCategory));
    }
  }

  private Text buildEmptyState(String searchText, String selectedCategory) {
    String message;
    if (searchText != null && !searchText.isEmpty()) {
      message = "No labels match \"" + searchText + "\".";
    } else if (selectedCategory != null) {
      message = "No labels in \"" + selectedCategory + "\" yet.";
    } else {
      message = "No labels yet, add one from the Label tab.";
    }
    Text placeholder = new Text(message);
    placeholder.getStyleClass().add("empty-state");
    return placeholder;
  }

  private void addLabelCard(ItemLabel itemLabel, String category) {
    LabelComponent newLabel = new LabelComponent(itemLabel, category);
    newLabel.setOnEditRequested(this::handleEditRequest);
    newLabel.setOnDeleteRequested(this::handleDeleteRequest);
    labelsList.getChildren().add(newLabel);
  }

  @FXML
  protected void clearCategorySelection() {
    categoryList.getSelectionModel().clearSelection();
    refreshLabels();
  }

  @FXML
  protected void clearSearch() {
    searchBar.clear();
  }

  private void handleEditRequest(ItemLabel label, String category) {
    addLabelPane.populateForEdit(label, category);
    labelTab.getTabPane().getSelectionModel().select(labelTab);
  }

  private void handleDeleteRequest(ItemLabel label, String category) {
    Database.deleteByID(label.id, category);
    refreshLabels();
  }

}
