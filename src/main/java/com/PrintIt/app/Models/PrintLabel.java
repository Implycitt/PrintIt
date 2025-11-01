package com.PrintIt.app.Models;

import java.util.Arrays;
import java.util.UUID;

public class PrintLabel 
{

  public UUID id;
  public String name;
  public String url;
  public String[] subcategories;
  public String type;

  public PrintLabel(UUID id, String name, String url, String[] subcategories, String type)
  {
    this.id = id;
    this.name = name;
    this.url = url;
    this.subcategories = subcategories;
    this.type = type;
  }  

  public String toString()
  {
    return String.format("id: %s, name: %s, url: %s, subcategories: %s, type: %s", 
        this.id, this.name, this.url, Arrays.toString(this.subcategories), this.type);
  }
}
