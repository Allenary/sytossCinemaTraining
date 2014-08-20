package com.sytoss.training.cinema.domainservice;

import com.sytoss.training.cinema.domainservice.reader.IReader;
import com.sytoss.training.cinema.domainservice.reader.StaxReader;
import com.sytoss.training.cinema.domainservice.writer.IWriter;
import com.sytoss.training.cinema.domainservice.writer.JdomXmlWriter;

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
