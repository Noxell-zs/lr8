package ru.sfu.apps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.ibatis.jdbc.ScriptRunner;

/** Auxiliary class for creating tables */
public final class CreateTables {
  /**
   * Entry point
   * @param args Command Line Arguments
   */
  public static void main(String[] args) {
    try {
      String url = "jdbc:sqlite:coffee_machines.db";
      Connection con = DriverManager.getConnection(url);
      System.out.println("Connection established......");
      ScriptRunner sr = new ScriptRunner(con);

      Reader reader = new BufferedReader(new FileReader("create_tables.sql"));

      sr.runScript(reader);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
