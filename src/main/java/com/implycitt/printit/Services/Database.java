package com.implycitt.printit.Services;

import com.implycitt.printit.Models.ItemLabel;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

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

  public static void createTable(String table)
  {
    String sql = String.format("CREATE TABLE IF NOT EXISTS %s (id INTEGER PRIMARY KEY, names text NOT NULL, subcategories text, url text NOT NULL, tags text)", table);

    try (var connection = DriverManager.getConnection(url);
         var statement = connection.createStatement()) {
      statement.execute(sql);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void getById(int id, String table) throws SQLException {
    String sql = String.format("SELECT * FROM %s WHERE id IS %s", table, id);
    try (var connection = DriverManager.getConnection(url);
         var statement = connection.createStatement();
         var response = statement.executeQuery(sql)) {
      System.out.println(response);
    }
  }

  public static void addToDatabase(ItemLabel label, String table)
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

  public static void upsertToDatabase(int id, ItemLabel newLabel)
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
