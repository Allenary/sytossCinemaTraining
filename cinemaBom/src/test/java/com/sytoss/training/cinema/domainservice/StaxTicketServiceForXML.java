package com.sytoss.training.cinema.domainservice;

public class StaxTicketServiceForXML extends AbstactTicketServiceTestForXML {

  @Override
  public IReader getReader() {
    return new StaxReader();
  }

  @Override
  public IWriter getWriter() {
    return new JdomXmlWriter();
  }

}
