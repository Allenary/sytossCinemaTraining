package com.sytoss.training.cinema.domainservice;

public class JdomTicketServiceForXML extends AbstactTicketServiceTestForXML {

  @Override
  public IReader getReader() {
    return new JdomReader();
  }

  @Override
  public IWriter getWriter() {
    return new JdomXmlWriter();
  }

}
