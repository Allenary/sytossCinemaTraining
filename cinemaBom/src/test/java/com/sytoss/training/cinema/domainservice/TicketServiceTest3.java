package com.sytoss.training.cinema.domainservice;

import java.util.List;
import java.util.Map.Entry;

public class TicketServiceTest3 extends abstractTicketServiceTest {

  private Entry<String, List<String>> inputData;

  public TicketServiceTest3(Entry<String, List<String>> inputData) {
    this.inputData = inputData;
  }

  @Override
  public Entry<String, List<String>> getTestData() {
    return inputData;
  }

}
