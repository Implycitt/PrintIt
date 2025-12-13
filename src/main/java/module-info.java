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
  exports com.implycitt.printit.Controllers;
  opens com.implycitt.printit.Controllers to javafx.fxml;
}