package com.implycitt.printit.Services;

import com.implycitt.printit.Models.ItemLabel;

import java.io.File;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {

  // wont open when it goes in the c drive -> fix later;
  public static String url = "jdbc:sqlite:labels.db";

  public static void createDatabase()
  {
    try (var conn = DriverManager.getConnection(url))
    {
      if (conn != null) System.out.println("Connection successful");
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  public static void checkDatabase()
  {
    File database = new File(url);

    if (database.exists())
    {
      return;
    }
    createDatabase();
  }

  public static void createTable(String table)
  {
    String sql = String.format("CREATE TABLE IF NOT EXISTS %s(id INTEGER PRIMARY KEY, primaryName text NOT NULL, otherNames text, subcategories text, url text NOT NULL, tags text)", table);
    executeQuery(sql);
  }

  public static ItemLabel GenericGet(String fieldSearching, String table, String labelField) {
    String sql = String.format("SELECT * FROM %s WHERE %s = ?", table, fieldSearching);
    try (var connection = DriverManager.getConnection(url);
         var statement = connection.prepareStatement(sql)) {
      statement.setString(1, labelField);
      var rs = statement.executeQuery();
      return getAndTransform(rs).getFirst();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  public static ItemLabel getByID(Integer labelID, String table) {
    String sql = String.format("SELECT * FROM %s WHERE id = ?", table);
    try (var connection = DriverManager.getConnection(url);
         var statement = connection.prepareStatement(sql)) {
      statement.setInt(1, labelID);
      var rs = statement.executeQuery();
      return getAndTransform(rs).getFirst();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  public static void addToDatabase(ItemLabel label, String table)
  {
    String sql = String.format("INSERT INTO %s(id, primaryName, otherNames, subcategories, url, tags) VALUES(?, ?, ?, ?, ?, ?)", table);
    try (var connection = DriverManager.getConnection(url);
         var ps = connection.prepareStatement(sql)) {
      ps.setInt(1, label.id);
      ps.setString(2, label.primaryName);
      ps.setString(3, label.turnArrayIntoText(label.otherNames));
      ps.setString(4, label.turnArrayIntoText(label.subcategories));
      ps.setString(5, label.url);
      ps.setString(6, label.type);
      ps.executeUpdate();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  public static void upsertToDatabase(int id, ItemLabel newLabel, String table)
  {
    if (getByID(id, table) == null)
    {
      addToDatabase(newLabel, table);
    } else {
      String sql = String.format("UPDATE %s SET primaryName = ?, otherNames = ?, subcategories = ?, url = ?, tags = ? WHERE id = ?", table);
      try (var connection = DriverManager.getConnection(url);
           var ps = connection.prepareStatement(sql)) {
        ps.setString(1, newLabel.primaryName);
        ps.setString(2, newLabel.turnArrayIntoText(newLabel.otherNames));
        ps.setString(3, newLabel.turnArrayIntoText(newLabel.subcategories));
        ps.setString(4, newLabel.url);
        ps.setString(5, newLabel.type);
        ps.setInt(6, newLabel.id);
        ps.executeUpdate();
      } catch (SQLException e) {
        System.err.println(e.getMessage());
      }
    }

  }

  public static void deleteByID(int LabelID, String table)
  {
    String sql = String.format("DELETE FROM %s WHERE id = ?", table);
    try (var connection = DriverManager.getConnection(url);
         var prepareStatement = connection.prepareStatement(sql)) {
      prepareStatement.setInt(1, LabelID);
      prepareStatement.executeUpdate();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  public static ArrayList<String> getAllTables()
  {
    ArrayList<String> tables = new ArrayList<>();
    try (var connection = DriverManager.getConnection(url)) {
      DatabaseMetaData metaData = connection.getMetaData();
      ResultSet rs = metaData.getTables(null, null, null, new String[]{"TABLE"});
      while (rs.next())
      {
        tables.add(rs.getString("TABLE_NAME"));
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return tables;
  }

  public static ArrayList<ItemLabel> getAllEntriesInTable(String table) throws SQLException {
    String sql = String.format("SELECT * FROM %s", table);
    return getAndTransform(executeQuery(sql));
  }

  public static ArrayList<ItemLabel> searchForEntries(String table, String searchText) throws SQLException {
    // this is really ugly but like it works so, maybe fix at some point: searching parameter has to be surrounded with %
    String sql = String.format("SELECT * FROM %s WHERE primaryName LIKE ", table) + "%" + String.format("%s",searchText) + "%";
    return getAndTransform(executeQuery(sql));
  }

  public static ResultSet executeQuery(String query)
  {
    try (var connection = DriverManager.getConnection(url);
         var prepareStatement = connection.createStatement()) {
      return prepareStatement.executeQuery(query);
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return null;
  }

  public static ArrayList<ItemLabel> getAndTransform(ResultSet rs) throws SQLException {
    if (rs == null) return null;

    ArrayList<ItemLabel> labelList = new ArrayList<ItemLabel>();
    while (rs.next()) {
      ItemLabel currentLabel = new ItemLabel(
        rs.getInt("id"),
        rs.getString("primaryName"),
        rs.getString("otherNames"),
        rs.getString("url"),
        rs.getString("subcategories"),
        rs.getString("type")
      );
      labelList.add(currentLabel);
    }
    return labelList;
  }
}
