package com.sytoss.training.cinema.domainservice;

import com.sytoss.training.cinema.domainservice.ISplitStategy;
import com.sytoss.training.cinema.domainservice.SplitSplitStringStrategy;

public class SplitSplitStringStrategyTest extends AbstractCsvParserTest {

  @Override
  public ISplitStategy getStrategy() {
    return new SplitSplitStringStrategy();
  }

}
