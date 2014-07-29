package com.sytoss.training.cinema.DomainService;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CsvParserTest {

  @Test
  public void shouldParseValidRow() {
    String[] ticketAttributes = new CsvParser().parse("cinema-name,room-name,movie-name,date,row,place,price,cashoffice_ID");
    assertEquals("cinema-name", ticketAttributes[0]);
    assertEquals("room-name", ticketAttributes[1]);
    assertEquals("movie-name", ticketAttributes[2]);
    assertEquals("date", ticketAttributes[3]);
    assertEquals("row", ticketAttributes[4]);
    assertEquals("place", ticketAttributes[5]);
    assertEquals("price", ticketAttributes[6]);
    assertEquals("cashoffice_ID", ticketAttributes[7]);
  }

  @Test
  public void shouldParseValidRowWithCommasInName() {
    String[] ticketAttributes = new CsvParser().parse("Kronverk,red,\"Run, Forest, Run!\",20.08.2014,5,6,60.00,8");
    assertEquals("Kronverk", ticketAttributes[0]);
    assertEquals("red", ticketAttributes[1]);
    assertEquals("\"Run, Forest, Run!\"", ticketAttributes[2]);
    assertEquals("20.08.2014", ticketAttributes[3]);
    assertEquals("5", ticketAttributes[4]);
    assertEquals("6", ticketAttributes[5]);
    assertEquals("60.00", ticketAttributes[6]);
    assertEquals("8", ticketAttributes[7]);
  }

  @Test
  public void shouldParseValidRowWithQuotesInName() {
    String[] ticketAttributes = new CsvParser().parse("Kronverk,red,\"Корпорация \"Марионетки\"\",20.08.2014,5,6,60.00,8");
    assertEquals("Kronverk", ticketAttributes[0]);
    assertEquals("red", ticketAttributes[1]);
    assertEquals("\"Корпорация \"Марионетки\"\"", ticketAttributes[2]);
    assertEquals("20.08.2014", ticketAttributes[3]);
    assertEquals("5", ticketAttributes[4]);
    assertEquals("6", ticketAttributes[5]);
    assertEquals("60.00", ticketAttributes[6]);
    assertEquals("8", ticketAttributes[7]);
  }
}
