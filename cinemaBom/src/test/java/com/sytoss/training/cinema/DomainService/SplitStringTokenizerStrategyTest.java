package com.sytoss.training.cinema.domainservice;

import com.sytoss.training.cinema.domainservice.csvparcer.ISplitStategy;
import com.sytoss.training.cinema.domainservice.csvparcer.SplitStringTokenizerStrategy;

public class SplitStringTokenizerStrategyTest extends AbstractCsvParserTest {

  @Override
  public ISplitStategy getStrategy() {
    return new SplitStringTokenizerStrategy();
  }
}
