package com.sytoss.training.cinema.connector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.Cinema;
import com.sytoss.training.cinema.bom.Seance;
import com.sytoss.training.cinema.bom.Ticket;
import com.sytoss.training.cinema.translator.MovieTranslator;
import com.sytoss.training.cinema.translator.PlaceTranslator;
import com.sytoss.training.cinema.translator.RoomTranslator;
import com.sytoss.training.cinema.translator.RowTranslator;
import com.sytoss.training.cinema.translator.SeanceTranslator;

public class FileSystemConnector {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  public void write(List<String> csvStrings, String fileNameDestination) throws IOException {
    File file = new File(fileNameDestination);
    logger.info("outfilename=" + fileNameDestination);
    if ( !file.exists()) {
      file.createNewFile();

    }

    FileWriter fileWriter = new FileWriter(file, false);
    fileWriter.write(""); //clear file content
    fileWriter.close();
    fileWriter = new FileWriter(file, true);
    PrintWriter printWriter = new PrintWriter(fileWriter);

    for (String row : csvStrings) {
      printWriter.println(row);
      logger.info("row added to output: " + row);
    }

    try {
      printWriter.close();
      fileWriter.close();
    } catch (IOException e) {
      logger.error("Writer could not be closed.");
    }
  }

  public List<String> read(String fileName) throws IOException {
    List<String> csvStrings = new ArrayList<String>();
    FileReader fileReader = new FileReader(fileName);
    BufferedReader bufReader = new BufferedReader(fileReader);
    String line;

    while ((line = bufReader.readLine()) != null) {
      csvStrings.add(line);
    }

    bufReader.close();
    return csvStrings;

  }

  public void writeJDOM(Document document, String fileNameDestination) throws IOException {
    XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
    xmlOutputter.output(document, new FileWriter(fileNameDestination));
  }

  public Document readXMLFileJDOM(String fileName) throws JDOMException, IOException {
    return new SAXBuilder().build(new File(fileName));
  }

  public XmlPullParser readXMLFileSTAX(String fileName) throws XmlPullParserException, FileNotFoundException {
    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    XmlPullParser xpp = factory.newPullParser();
    InputStream inStream = new FileInputStream(fileName);

    xpp.setInput(inStream, null);
    return xpp;
  }

  public void writeSTAX(List<Cinema> cinemas, String outputFileName)
      throws FileNotFoundException,
      UnsupportedEncodingException,
      XMLStreamException,
      FactoryConfigurationError {
    OutputStream outputStream = new FileOutputStream(new File(outputFileName));
    XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(new OutputStreamWriter(outputStream, "utf-8"));
    out.writeStartDocument("UTF-8", "1.0");
    out.writeCharacters("\r\n");
    out.writeStartElement("cinemas");

    for (Cinema cinema : cinemas) {
      out.writeCharacters("\r\n  ");
      out.writeStartElement("cinema");
      out.writeAttribute("name", cinema.getName());

      for (Iterator<CashOffice> iterator = cinema.cashOfficeIterator(); iterator.hasNext();) {
        out.writeCharacters("\r\n    ");

        CashOffice cashOffice = iterator.next();
        out.writeStartElement("cashOffice");
        out.writeAttribute("id", Integer.toString(cashOffice.getNumber()));
        out.writeCharacters("\r\n      ");

        for (Seance seance : cashOffice.getSeances()) {
          out.writeStartElement("seance");
          out.writeAttribute("startDateTime", new SeanceTranslator(SeanceTranslator.XML_DATE_FORMAT).toDTO(seance));
          out.writeCharacters("\r\n        ");

          out.writeStartElement("movie");
          out.writeCharacters(new MovieTranslator().toDTO(seance.getMovie()));
          out.writeEndElement();
          out.writeCharacters("\r\n        ");

          out.writeStartElement("room");
          out.writeCharacters(new RoomTranslator().toDTO(seance.getRoom()));
          out.writeEndElement();
          out.writeCharacters("\r\n        ");

          out.writeStartElement("tickets");
          for (Ticket ticket : seance.getTicketsByCOid(cashOffice.getNumber())) {
            out.writeCharacters("\r\n          ");
            out.writeEmptyElement("ticket");
            out.writeAttribute("row", new RowTranslator().toDTO(ticket.getPlace().getRow()));
            out.writeAttribute("place", new PlaceTranslator().toDTO(ticket.getPlace()));
            out.writeAttribute("price", Double.toString(ticket.getPrice()));
          }
          out.writeCharacters("\r\n        ");
          out.writeEndElement(); //end tickets
          out.writeCharacters("\r\n      ");
          out.writeEndElement(); // end seance
        }
        out.writeCharacters("\r\n    ");
        out.writeEndElement();// end CO
      }
      out.writeCharacters("\r\n  ");
      out.writeEndElement();// end cinema
    }
    out.writeCharacters("\r\n");
    out.writeEndElement(); // end cinemas
    out.writeCharacters("\r\n");
    out.writeEndDocument();
    out.close();
  }

}
