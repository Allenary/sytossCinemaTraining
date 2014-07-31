package com.sytoss.training.cinema.DomainService;

public class SplitSplitStringStrategyTest extends AbstractCsvParserTest {

  @Override
  public ISplitStategy getStrategy() {
    return new SplitSplitStringStrategy();
  }

}
