package com.PrintIt.app.Database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{
  public static void createConnection()
  {
    String url = "jdbc:sqlite:my.db";

    try (var conn = DriverManager.getConnection(url))
    {
      if (conn != null)
      {
        var meta = conn.getMetaData();
        System.out.println("The driver name: " + meta.getDriverName());
        System.out.println("A new database connected");
      }
    } catch (SQLException e)
    {
      System.err.println(e.getMessage());
    }
  }
}
