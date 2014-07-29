package com.sytoss.training.cinema.translator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sytoss.training.cinema.bom.Row;

public class RowTranslatorTest {

  @Test
  public void shouldTranslateToDTO() {
    String rowDTO = new RowTranslator().toDTO(new Row(2));
    assertEquals("2", rowDTO);
  }

  @Test
  public void shouldTranslateFromDTO() {
    Row row = new RowTranslator().fromDTO("8");
    assertEquals(8, row.getNumber());
  }
}
