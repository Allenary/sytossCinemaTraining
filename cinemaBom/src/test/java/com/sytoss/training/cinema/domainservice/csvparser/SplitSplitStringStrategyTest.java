package com.sytoss.training.cinema.domainservice.csvparser;

import com.sytoss.training.cinema.domainservice.csvparcer.ISplitStrategy;
import com.sytoss.training.cinema.domainservice.csvparcer.SplitSplitStringStrategy;

public class SplitSplitStringStrategyTest extends AbstractCsvParserTest {

  @Override
  public ISplitStrategy getStrategy() {
    return new SplitSplitStringStrategy();
  }

}
