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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CSVFileSystemConnector {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  public void write(List<String> csvStrings, String fileNameDestination) {
    File file = new File(fileNameDestination);
    logger.info("outfilename=" + fileNameDestination);
    try {
      if ( !file.exists()) {
        file.createNewFile();

      }

      new FileWriter(file, false).write(""); //clear file content
      PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));

      for (String row : csvStrings) {
        //        printWriter.append(row);
        printWriter.println(row);
        logger.info("row added to output: " + row);
      }
      printWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<String> read(String fileName) {
    List<String> csvStrings = new ArrayList<String>();
    if ( !new File(fileName).exists()) {
      logger.warn("File " + fileName + " does not exist!");
    }

    try {
      csvStrings = Files.readAllLines(Paths.get(fileName), Charset.forName("UTF-8"));

    } catch (IOException e) {
      logger.error("unable read file " + fileName);
    }
    return csvStrings;

  }

}
