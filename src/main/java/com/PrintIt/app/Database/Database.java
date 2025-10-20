package com.PrintIt.app.Database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

public class Database
{
  public static void createDatabase()
  {
    String url = "jdbc:sqlite:my.db";

    try (var conn = DriverManager.getConnection(url))
    {
      if (conn != null)
      {
        System.out.println("A new database connected");
      }
    } catch (SQLException e)
    {
      System.err.println(e.getMessage());
    }
  }
  
  public static void createTable(String tableName)
  {
    // TODO
  }
  public static void upsert(String tableName)
  {
    // TODO
  }
  public static void getById(UUID Id)
  {
    // TODO
  }
}
