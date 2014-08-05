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

import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSystemConnector {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  public void write(List<String> csvStrings, String fileNameDestination) throws IOException {
    File file = new File(fileNameDestination);
    logger.info("outfilename=" + fileNameDestination);
    if ( !file.exists()) {
      file.createNewFile();

    }

    FileWriter fileWriter = new FileWriter(file, false);
    fileWriter.write(""); //clear file content
    fileWriter.close();
    fileWriter = new FileWriter(file, true);
    PrintWriter printWriter = new PrintWriter(fileWriter);

    for (String row : csvStrings) {
      printWriter.println(row);
      logger.info("row added to output: " + row);
    }
    printWriter.close();
    fileWriter.close();
  }

  public List<String> read(String fileName) throws IOException {
    List<String> csvStrings = new ArrayList<String>();

    csvStrings = Files.readAllLines(Paths.get(fileName), Charset.forName("UTF-8"));

    return csvStrings;

  }

  public void write(Document document, String fileNameDestination) throws IOException {
    XMLOutputter xmlOutputter = new XMLOutputter();
    xmlOutputter.output(document, new FileWriter(fileNameDestination));

  }
}
