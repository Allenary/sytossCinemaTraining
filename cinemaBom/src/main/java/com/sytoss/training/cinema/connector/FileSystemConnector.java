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
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
    String encoding = "UTF-8";
    xmlOutputter.getFormat().setEncoding(encoding);
    xmlOutputter.output(document, new OutputStreamWriter(new FileOutputStream(fileNameDestination), encoding));
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

  public void writeSTAX(XmlSerializer serializer, String outputFileName)
      throws IllegalArgumentException,
      IllegalStateException,
      IOException {
    serializer.setOutput(new FileWriter(outputFileName));
  }

}
