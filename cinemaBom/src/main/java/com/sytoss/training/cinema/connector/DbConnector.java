package com.sytoss.training.cinema.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

  public void connect() {
    try {
      Connection c = DriverManager.getConnection(
        "jdbc:hsqldb:file:C:\\Users\\school\\Ninak+katen\\repo\\sytossCinemaTraining\\cinemaBom\\cinema.db;ifexists=true",
        "SA",
        "");
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
