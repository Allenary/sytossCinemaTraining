package com.sytoss.training.cinema.ui;

import java.util.ArrayList;
import java.util.List;

import com.sytoss.training.cinema.DomainService.TicketService;

public class CSVFileMerge {

  public static void main(String[] args) {
    if (args == null || args.length < 2) {
      throw new IllegalArgumentException("Minimum 2 file pathes needed");
    }
    List<String> inputFileNames = new ArrayList<String>();
    for (int i = 0; i < args.length - 1; i++ ) {
      inputFileNames.add(args[i]);
    }
    String outputFileName = args[args.length - 1];
    new TicketService().mergeCSV(inputFileNames, outputFileName);
  }
}
