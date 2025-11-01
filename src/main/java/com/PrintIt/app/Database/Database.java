package com.PrintIt.app.Database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;
import com.PrintIt.app.Models.*;

public class Database
{
  public static String url = "jdbc:sqlite:labels.db";
  
  public static void createDatabase()
  {
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
    String sql = String.format("CREATE TABLE IF NOT EXISTS %s (id INTEGER PRIMARY KEY, url text NOT NULL, tags text)", tableName);
    
    try (var connection = DriverManager.getConnection(url);
        var statement = connection.createStatement()) {
      statement.execute(sql);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void getById(UUID id)
  {
    // TODO
  }

  public static void addToDatabase(PrintLabel label) 
  {
    String sql = "";
    try (var connection = DriverManager.getConnection(url);
        var prepStatement = connection.prepareStatement(sql)) {
      System.out.println("hello world");
      // TODO
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  public static void upsertToDatabase(UUID id, PrintLabel newLabel)
  {
    // TODO
  }

  public static void deleteFromDatabase(int id, String table)
  {
    String sql = String.format("DELETE FROM %s WHERE id = ?", table);
    try (var connection = DriverManager.getConnection(url);
        var prepareStatement = connection.prepareStatement(sql)) {
      prepareStatement.setInt(1, id);
      prepareStatement.executeUpdate();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }
}
