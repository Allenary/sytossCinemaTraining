package com.sytoss.training.cinema.DomainService;

public class SplitStringTokenizerStrategyTest extends AbstractCsvParserTest {

  @Override
  public ISplitStategy getStrategy() {
    return new SplitStringTokenizerStrategy();
  }
}
