module com.implycitt.printit {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.implycitt.printit to javafx.fxml;
    exports com.implycitt.printit;
}