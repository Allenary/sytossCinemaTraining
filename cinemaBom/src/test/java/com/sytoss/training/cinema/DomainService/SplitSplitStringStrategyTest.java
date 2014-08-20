package com.sytoss.training.cinema.domainservice;

import com.sytoss.training.cinema.domainservice.csvparcer.ISplitStategy;
import com.sytoss.training.cinema.domainservice.csvparcer.SplitSplitStringStrategy;

public class SplitSplitStringStrategyTest extends AbstractCsvParserTest {

  @Override
  public ISplitStategy getStrategy() {
    return new SplitSplitStringStrategy();
  }

}
