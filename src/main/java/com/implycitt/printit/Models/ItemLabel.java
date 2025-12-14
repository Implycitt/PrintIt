package com.implycitt.printit.Models;

public class ItemLabel
{
  public Integer id;
  public String primaryName;
  public String[] otherNames;
  public String url;
  public String[] subcategories;
  public String type;

  public ItemLabel(String primaryName, String otherNames, String url, String subcategories, String type)
  {
    this.id = (int) (Math.random() * 100000000);
    this.primaryName = primaryName;
    this.otherNames = turnTextIntoArray(otherNames);
    this.url = url;
    this.subcategories = turnTextIntoArray(subcategories);
    this.type = type;
  }

  public String[] turnTextIntoArray(String text)
  {
    // arrays are not supported in sqlite so I will steal idea behind csv except with pipe
    return text.split("|");
  }

  public String turnArrayIntoText(String[] array)
  {
    return String.join("|", array);
  }
}
