module com.implycitt.printit {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.web;

  requires org.controlsfx.controls;
  requires org.kordamp.bootstrapfx.core;
  requires eu.hansolo.tilesfx;
  requires java.desktop;
  requires org.apache.pdfbox;
  requires java.sql;
  requires javafx.graphics;

  opens com.implycitt.printit to javafx.fxml;
  exports com.implycitt.printit;

  exports com.implycitt.printit.controllers;
  opens com.implycitt.printit.controllers to javafx.fxml;

  exports com.implycitt.printit.components;
  opens com.implycitt.printit.components to javafx.fxml;

  exports com.implycitt.printit.models;
  opens com.implycitt.printit.models to javafx.fxml;

  exports com.implycitt.printit.services;
}
