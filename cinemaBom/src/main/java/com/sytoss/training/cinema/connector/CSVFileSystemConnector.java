package com.sytoss.training.cinema.connector;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVFileSystemConnector {

  private boolean appendFile;

  public CSVFileSystemConnector(boolean appendFile) {
    setAppendFile(appendFile);
  }

  public CSVFileSystemConnector() {
    this(false);
  }

  public boolean isAppendFile() {
    return appendFile;
  }

  public void setAppendFile(boolean appendFile) {
    this.appendFile = appendFile;
  }

  public void write(List<String> csvStrings, String fileNameDestination) {
    File file = new File(fileNameDestination);
    try {
      if ( !file.exists()) {
        file.createNewFile();
      }

      FileWriter fileWriter = new FileWriter(file, appendFile);
      PrintWriter printWriter = new PrintWriter(fileWriter);

      for (String row : csvStrings) {
        printWriter.println(row);
      }
      printWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<String> read(String fileName) {
    List<String> csvStrings = new ArrayList<String>();

    try {
      csvStrings = Files.readAllLines(Paths.get(fileName), Charset.forName("UTF-8"));

    } catch (IOException e) {
      e.printStackTrace();
    }
    return csvStrings;

  }

}
