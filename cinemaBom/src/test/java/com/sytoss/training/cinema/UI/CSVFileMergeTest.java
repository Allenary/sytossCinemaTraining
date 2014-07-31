package com.sytoss.training.cinema.UI;

import org.junit.Test;

public class CSVFileMergeTest {

  @Test
  public void shouldMergeFile() {

  }

  @Test(expected = IllegalArgumentException.class)
  public void ShouldRaiseExceptionWhenListIsBlank() {
    new CSVFileMerge().main(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ShouldRaiseExceptionWhenListContainsOnly1Param() {
    new CSVFileMerge().main(new String[] {"filename"});
  }
}
