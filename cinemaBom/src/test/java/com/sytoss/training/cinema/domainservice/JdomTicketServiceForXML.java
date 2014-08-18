package com.sytoss.training.cinema.domainservice;

public class JdomTicketServiceForXML extends AbstactTicketServiceTestForXML {

  @Override
  public IXmlReader getReader() {
    return new JdomReader();
  }

  @Override
  public IXmlWriter getWriter() {
    return new JdomXmlWriter();
  }

}
