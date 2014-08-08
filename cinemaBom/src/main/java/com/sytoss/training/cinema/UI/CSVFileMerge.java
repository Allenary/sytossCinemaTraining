package com.sytoss.training.cinema.ui;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.JDOMException;

import com.sytoss.training.cinema.domainservice.TicketService;

public class CSVFileMerge {

  public static void main(String[] args) throws IOException, JDOMException, ParseException {
    if (args == null || args.length < 2) {
      printHelp();
      throw new IllegalArgumentException("Minimum 2 file pathes needed");
    }
    List<String> inputFileNames = new ArrayList<String>();
    for (int i = 0; i < args.length - 1; i++ ) {
      inputFileNames.add(args[i]);
    }
    String outputFileName = args[args.length - 1];
    FileFormat inputFileFormat = checkFileFormat(inputFileNames);
    FileFormat outputFileFormat = checkFileFormat(outputFileName);
    if (inputFileFormat == FileFormat.CSV) {
      if (outputFileFormat == FileFormat.CSV) {
        new TicketService().mergeCSV(inputFileNames, outputFileName);
      } else if (outputFileFormat == FileFormat.XML) {
        new TicketService().mergeCSVToXML(inputFileNames, outputFileName);
      } else {
        printHelp();
        throw new IllegalArgumentException("Output file has unsupported format");
      }
    } else if (inputFileFormat == FileFormat.XML) {
      if (outputFileFormat == FileFormat.XML) {
        new TicketService().mergeXML(inputFileNames, outputFileName);
      } else {
        printHelp();
        throw new IllegalArgumentException("XML have to merge in XML");
      }
    } else {
      printHelp();
      throw new IllegalArgumentException("Input file have invalid format");
    }
  }

  private static void printHelp() {
    System.out.println("CSVFileMerge <source> [<source>] <destination> ");
    System.out.println("<source> - input file name");
    System.out.println("<destination> - output file name could be CSV or XML");
  }

  private static boolean isCSV(String fileName) {
    return fileName.matches(".*(\\.csv|\\.CSV)");
  }

  private static boolean isXML(String fileName) {
    return fileName.matches(".*(\\.xml|\\.XML)");
  }

  private static FileFormat checkFileFormat(String fileName) {
    if (isCSV(fileName)) {
      return FileFormat.CSV;
    }
    if (isXML(fileName)) {
      return FileFormat.XML;
    }
    return FileFormat.UNSUPPORTED;
  }

  private static FileFormat checkFileFormat(List<String> fileNames) {
    FileFormat firstFile = checkFileFormat(fileNames.get(0));
    for (String fileName : fileNames) {
      if (checkFileFormat(fileName) != firstFile) {
        return FileFormat.UNSUPPORTED;
      }
    }
    return firstFile;
  }

}
