package com.sytoss.training.cinema.domainservice.csvparser;

import com.sytoss.training.cinema.domainservice.csvparcer.ISplitStrategy;
import com.sytoss.training.cinema.domainservice.csvparcer.SplitStringTokenizerStrategy;

public class SplitStringTokenizerStrategyTest extends AbstractCsvParserTest {

  @Override
  public ISplitStrategy getStrategy() {
    return new SplitStringTokenizerStrategy();
  }
}
