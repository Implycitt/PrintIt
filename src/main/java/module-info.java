module com.implycitt.printit {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.web;

  requires org.controlsfx.controls;
  requires org.kordamp.bootstrapfx.core;
  requires eu.hansolo.tilesfx;
  requires java.desktop;
  requires org.apache.pdfbox;

  opens com.implycitt.printit to javafx.fxml;
    exports com.implycitt.printit;
  exports com.implycitt.printit.controllers;
  opens com.implycitt.printit.controllers to javafx.fxml;
}