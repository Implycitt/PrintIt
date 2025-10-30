package com.PrintIt.app.Models;

public class Error
{

  // Eventually use this model as a way to handle errors
  // Thinking of doing something very similar to how GO handles errors => something, response := function()
  String response = "";

  public Error()
  {
    this.response = "Success";
  }

}
