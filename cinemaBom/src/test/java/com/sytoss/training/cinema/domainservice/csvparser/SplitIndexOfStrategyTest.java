package com.sytoss.training.cinema.domainservice.csvparser;

import com.sytoss.training.cinema.domainservice.csvparcer.ISplitStrategy;
import com.sytoss.training.cinema.domainservice.csvparcer.SplitIndexOfStrategy;

public class SplitIndexOfStrategyTest extends AbstractCsvParserTest {

  @Override
  public ISplitStrategy getStrategy() {
    return new SplitIndexOfStrategy();
  }

}
