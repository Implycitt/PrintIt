package com.PrintIt.app;

import com.PrintIt.app.Database.*;

public class App 
{
    public static void main( String[] args )
    {
      Database.createConnection();
      Window.WindowStart();
    }
}
