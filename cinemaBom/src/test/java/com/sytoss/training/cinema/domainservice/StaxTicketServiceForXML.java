package com.sytoss.training.cinema.domainservice;

public class StaxTicketServiceForXML extends AbstactTicketServiceTestForXML {

  @Override
  public IXmlReader getReader() {
    return new StaxReader();
  }

  @Override
  public IXmlWriter getWriter() {
    return new JdomXmlWriter();
  }

}
