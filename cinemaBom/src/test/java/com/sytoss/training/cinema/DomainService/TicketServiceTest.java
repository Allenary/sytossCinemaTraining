package com.sytoss.training.cinema.DomainService;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.Ticket;

public class TicketServiceTest {

  @Test
  public void shouldReturnTrueIfAllTicketsSoldBySameCashOffice() {

    Ticket ticket1 = new Ticket();
    ticket1.setCashOffice(new CashOffice(1));
    Ticket ticket2 = new Ticket();
    ticket2.setCashOffice(new CashOffice(1));
    Ticket ticket3 = new Ticket();
    ticket3.setCashOffice(new CashOffice(1));

    List<Ticket> tickets = new ArrayList<Ticket>();
    tickets.add(ticket1);
    tickets.add(ticket2);
    tickets.add(ticket3);

    assertTrue(new TicketService().equalsCashOfficeID(tickets));
  }

  @Test
  public void shouldReturnFalseIfNotAllTicketsSoldBySameCashOffice() {

    Ticket ticket1 = new Ticket();
    ticket1.setCashOffice(new CashOffice(1));
    Ticket ticket2 = new Ticket();
    ticket2.setCashOffice(new CashOffice(1));
    Ticket ticket3 = new Ticket();
    ticket3.setCashOffice(new CashOffice(2));

    List<Ticket> tickets = new ArrayList<Ticket>();
    tickets.add(ticket1);
    tickets.add(ticket2);
    tickets.add(ticket3);

    assertFalse(new TicketService().equalsCashOfficeID(tickets));
  }

  @Test
  public void shouldReturnFalseIfListEmpty() {
    List<Ticket> tickets = new ArrayList<Ticket>();

    assertFalse(new TicketService().equalsCashOfficeID(tickets));
  }

  @Test
  public void shouldReturnFalseIfNoCashOfficeNumber() {
    List<Ticket> tickets = new ArrayList<Ticket>();
    Ticket ticket1 = new Ticket();
    ticket1.setCashOffice(new CashOffice(1));
    Ticket ticket2 = new Ticket();

    tickets.add(ticket1);
    tickets.add(ticket2);

    assertFalse(new TicketService().equalsCashOfficeID(tickets));
  }

  /*
   * end-to-end. With data files.
   */
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
