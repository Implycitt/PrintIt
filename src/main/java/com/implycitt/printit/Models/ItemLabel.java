package com.implycitt.printit.Models;

import java.util.Arrays;

public class ItemLabel {
  public Integer id;
  public String[] names;
  public String url;
  public String[] subcategories;
  public String type;

  public ItemLabel(Integer id, String[] names, String url, String[] subcategories, String type)
  {
    this.id = id;
    this.names = names;
    this.url = url;
    this.subcategories = subcategories;
    this.type = type;
  }

  public static String[] turnTextIntoArray(String text)
  {
    // arrays are not supported in sqlite so I will steal idea behind csv except with pipe
    return text.split("|");
  }

  public static String turnArrayIntoText(String[] array)
  {
    return String.join("|", array);
  }

  public String toString()
  {
    return String.format("id: %s, name: %s, url: %s, subcategories: %s, type: %s",
      this.id, this.names, this.url, Arrays.toString(this.subcategories), this.type);
  }
}
