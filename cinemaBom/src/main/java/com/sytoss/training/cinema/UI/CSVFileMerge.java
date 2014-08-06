package com.sytoss.training.cinema.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sytoss.training.cinema.domainservice.TicketService;

public class CSVFileMerge {

  public static void main(String[] args) throws IOException {
    if (args == null || args.length < 2) {
      printHelp();
      throw new IllegalArgumentException("Minimum 2 file pathes needed");
    }
    List<String> inputFileNames = new ArrayList<String>();
    for (int i = 0; i < args.length - 1; i++ ) {
      inputFileNames.add(args[i]);
    }
    String outputFileName = args[args.length - 1];
    if (isXML(outputFileName)) {
      new TicketService().mergeCSVToXML(inputFileNames, outputFileName);
    } else if (isCSV(outputFileName)) {
      new TicketService().mergeCSV(inputFileNames, outputFileName);
    } else {
      printHelp();
      throw new IllegalArgumentException("Extension should be .XML or .CSV");
    }
  }

  private static void printHelp() {
    System.out.println("CSVFileMerge <source> [<source>] <destination> ");
    System.out.println("<source> - input file name");
    System.out.println("<destination> - output file name could be CSV or XML");
  }

  private static boolean isCSV(String outputFileName) {
    return outputFileName.matches(".*(\\.csv|\\.CSV)");
  }

  private static boolean isXML(String outputFileName) {
    return outputFileName.matches(".*(\\.xml|\\.XML)");
  }

}
