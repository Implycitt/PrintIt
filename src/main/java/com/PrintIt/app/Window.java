package com.PrintIt.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.UUID;
import com.PrintIt.app.Models.*;

public class Window extends Application 
{
  @Override
  public void start(Stage stage) 
  {
    String javaVersion = System.getProperty("java.version");
    String javafxVersion = System.getProperty("javafx.version");

    Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
    Scene scene = new Scene(new StackPane(l), 640, 480);
    stage.setScene(scene);
    stage.show();
  }

  public static void WindowStart() 
  {
    launch();
  } 

  public static void PrintHello()
  {
    UUID id = UUID.randomUUID();
    String[] cats = {"sub", "category"};
    PrintLabel newLabel = new PrintLabel(id, "name", "url", cats, "type");
    System.out.println(newLabel.toString());
  }
}
