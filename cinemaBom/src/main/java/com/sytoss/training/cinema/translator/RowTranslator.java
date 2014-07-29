package com.sytoss.training.cinema.translator;

import com.sytoss.training.cinema.bom.Row;

public class RowTranslator {

  public String toDTO(Row row) {
    return Integer.toString(row.getNumber());
  }

  public Row fromDTO(String rowDTO) {
    return new Row(Integer.parseInt(rowDTO));
  }
}
