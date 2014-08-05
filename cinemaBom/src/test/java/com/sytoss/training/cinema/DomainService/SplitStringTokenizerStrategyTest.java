package com.sytoss.training.cinema.domainservice;

import com.sytoss.training.cinema.domainservice.ISplitStategy;
import com.sytoss.training.cinema.domainservice.SplitStringTokenizerStrategy;

public class SplitStringTokenizerStrategyTest extends AbstractCsvParserTest {

  @Override
  public ISplitStategy getStrategy() {
    return new SplitStringTokenizerStrategy();
  }
}
