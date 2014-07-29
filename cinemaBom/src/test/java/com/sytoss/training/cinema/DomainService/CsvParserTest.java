package com.sytoss.training.cinema.DomainService;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sytoss.training.cinema.exception.CsvStringParseException;

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
    String[] ticketAttributes = new CsvParser().parse("Kronverk,\"red\",\"Корпорация \"Марионетки\"\",20.08.2014,5,6,60.00,8");
    assertEquals("Kronverk", ticketAttributes[0]);
    assertEquals("\"red\"", ticketAttributes[1]);
    assertEquals("\"Корпорация \"Марионетки\"\"", ticketAttributes[2]);
    assertEquals("20.08.2014", ticketAttributes[3]);
    assertEquals("5", ticketAttributes[4]);
    assertEquals("6", ticketAttributes[5]);
    assertEquals("60.00", ticketAttributes[6]);
    assertEquals("8", ticketAttributes[7]);
  }

  @Test(expected = CsvStringParseException.class)
  public void shouldRaiseParseExceptionWhenQuotesCountIsOdd() {
    new CsvParser().parse("Kronverk,\"red\",\"Корпорация \"Марионетки\",20.08.2014,5,6,60.00,8");
  }

  @Test
  public void shouldDeparseSimpleArray() {
    String[] ticketAttributes = new String[] {"cinema-name", "room-name", "movie-name", "date", "row", "place", "price", "cashoffice_ID"};
    String csvRow = new CsvParser().deParse(ticketAttributes);
    assertEquals("cinema-name,room-name,movie-name,date,row,place,price,cashoffice_ID", csvRow);
  }

  @Test
  public void shouldDeparseArrayWithQuotes() {
    String[] ticketAttributes = new String[] {"Kronverk", "red", "Corporation \"Monsters\"", "20.08.2014", "5", "6", "60.00", "8"};
    String csvRow = new CsvParser().deParse(ticketAttributes);
    assertEquals("Kronverk,red,\"Corporation \"Monsters\"\",20.08.2014,5,6,60.00,8", csvRow);
  }

  @Test
  public void shouldDeparseArrayWithRussianLetters() {
    String[] ticketAttributes = new String[] {"ПаркZ", "red", "Corporation \"Monsters\"", "20.08.2014", "5", "6", "60.00", "8"};
    String csvRow = new CsvParser().deParse(ticketAttributes);
    assertEquals("\"ПаркZ\",red,\"Corporation \"Monsters\"\",20.08.2014,5,6,60.00,8", csvRow);
  }

  @Test
  public void shouldDeparseArrayWithCommas() {
    String[] ticketAttributes = new String[] {"Cinema", "red", "Run, Forest, Run!", "20.08.2014", "5", "6", "60.00", "8"};
    String csvRow = new CsvParser().deParse(ticketAttributes);
    assertEquals("Cinema,red,\"Run, Forest, Run!\",20.08.2014,5,6,60.00,8", csvRow);
  }
}