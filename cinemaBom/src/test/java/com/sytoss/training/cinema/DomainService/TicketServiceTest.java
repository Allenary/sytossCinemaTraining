package com.sytoss.training.cinema.DomainService;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
  public void shouldMerge2files() throws IOException {
    List<String> inputFiles = Arrays.asList(
      "C:\\Users\\school\\Ninak+katen\\repo\\sytossCinemaTraining\\testData\\shouldMerge2files\\1ticket.csv",
      "C:\\Users\\school\\Ninak+katen\\repo\\sytossCinemaTraining\\testData\\shouldMerge2files\\3tickets.csv");

    new TicketService().mergeCSV(
      inputFiles,
      "C:\\Users\\school\\Ninak+katen\\repo\\sytossCinemaTraining\\testData\\shouldMerge2files\\testRunResult.csv");

    areFilesEqual(
      "C:\\Users\\school\\Ninak+katen\\repo\\sytossCinemaTraining\\testData\\shouldMerge2files\\Standard.csv",
      "C:\\Users\\school\\Ninak+katen\\repo\\sytossCinemaTraining\\testData\\shouldMerge2files\\testRunResult.csv");
  }

  @Test
  public void shouldMerge1file() throws IOException {
    List<String> inputFiles = Arrays
      .asList("C:\\Users\\school\\Ninak+katen\\repo\\sytossCinemaTraining\\testData\\shouldMerge1file\\1ticket.csv");

    new TicketService().mergeCSV(
      inputFiles,
      "C:\\Users\\school\\Ninak+katen\\repo\\sytossCinemaTraining\\testData\\shouldMerge1file\\testRunResult.csv");

    areFilesEqual(
      "C:\\Users\\school\\Ninak+katen\\repo\\sytossCinemaTraining\\testData\\shouldMerge1file\\Standard.csv",
      "C:\\Users\\school\\Ninak+katen\\repo\\sytossCinemaTraining\\testData\\shouldMerge1file\\testRunResult.csv");
  }

  private void areFilesEqual(String fileName1, String fileName2) throws IOException {
    assertArrayEquals(getFileBytes(fileName1), (getFileBytes(fileName2)));
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
      e.printStackTrace();
    } finally {
      fis.close();
      bos.close();
    }
    return null;
  }
}
