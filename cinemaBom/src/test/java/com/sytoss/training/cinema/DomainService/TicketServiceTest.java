package com.sytoss.training.cinema.DomainService;

import static org.junit.Assert.assertArrayEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TicketServiceTest {

  @Test
  public void shouldMerge2files() throws IOException, URISyntaxException {
    String folder = "/shouldMerge2files";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/1ticket.csv").toURI()).getAbsolutePath(), new File(
      getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath());

    new TicketService().mergeCSV(inputFiles, new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath());

    areFilesEqual(
      new File(getClass().getResource(folder + "/Standard.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMerge1file() throws IOException, URISyntaxException {
    String folder = "/shouldMerge1file";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/1ticket.csv").toURI()).getAbsolutePath());

    new TicketService().mergeCSV(inputFiles, new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath());

    areFilesEqual(
      new File(getClass().getResource(folder + "/Standard.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath());
  }

  @Test
  public void shouldNotMergeFileWithDifferentCashOffice() throws IOException, URISyntaxException {
    String folder = "/shouldNotMergeFileWithDifferentCashOffice";
    List<String> inputFiles = Arrays.asList(
      new File(getClass().getResource(folder + "/validTicket.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/emptyCashOffice.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/anotherCashOffice.csv").toURI()).getAbsolutePath());

    new TicketService().mergeCSV(inputFiles, new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath());

    areFilesEqual(
      new File(getClass().getResource(folder + "/Standard.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath());
  }

  @Test
  public void shouldNotMergeFileWithRowsWithWrongLength() throws IOException, URISyntaxException {
    String folder = "/shouldNotMergeFileWithRowsWithWrongLength";
    List<String> inputFiles = Arrays.asList(
      new File(getClass().getResource(folder + "/validTicket.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/lessParamsInRow.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/moreParamsInRow.csv").toURI()).getAbsolutePath());

    new TicketService().mergeCSV(inputFiles, new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath());

    areFilesEqual(
      new File(getClass().getResource(folder + "/Standard.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath());
  }

  @Test
  public void shouldNotMergeFileWithRowsWithWrongParams() throws IOException, URISyntaxException {
    String folder = "/shouldNotMergeFileWithRowsWithWrongParams";
    List<String> inputFiles = Arrays.asList(
      new File(getClass().getResource(folder + "/validTicket.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/wrongDate.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/wrongPrice.csv").toURI()).getAbsolutePath());

    new TicketService().mergeCSV(inputFiles, new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath());

    areFilesEqual(
      new File(getClass().getResource(folder + "/Standard.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMergeFileWithQuotesInParams() throws IOException, URISyntaxException {
    String folder = "/shouldMergeFileWithQuotesInParams";
    List<String> inputFiles = Arrays.asList(
      new File(getClass().getResource(folder + "/CommasInQuotes.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/QuotesInQuotes.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/invalidTicket.csv").toURI()).getAbsolutePath());

    new TicketService().mergeCSV(inputFiles, new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath());

    areFilesEqual(
      new File(getClass().getResource(folder + "/Standard.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath());
  }

  private void areFilesEqual(String fileName1, String fileName2) throws IOException {
    assertArrayEquals(getFileBytes(fileName1), getFileBytes(fileName2));
  }

  private byte[] getFileBytes(String fileName) throws IOException {
    FileInputStream fis = new FileInputStream(fileName);
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    byte[] buff = new byte[1024];
    int i;
    try {
      i = fis.read(buff);
      while (i != -1) {
        bos.write(buff, 0, i);
        i = fis.read(buff);
      }
      bos.flush();
      return bos.toByteArray();
    } catch (IOException e) {
      System.out.print("getFileBytes Error");
      e.printStackTrace();

    } finally {
      fis.close();
      bos.close();
    }
    return null;
  }
}
