package com.sytoss.training.cinema.domainservice;

import com.sytoss.training.cinema.domainservice.reader.IReader;
import com.sytoss.training.cinema.domainservice.reader.JdomReader;
import com.sytoss.training.cinema.domainservice.writer.IWriter;
import com.sytoss.training.cinema.domainservice.writer.JdomXmlWriter;

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
