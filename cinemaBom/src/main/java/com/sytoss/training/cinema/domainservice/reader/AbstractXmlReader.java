package com.sytoss.training.cinema.domainservice.reader;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public abstract class AbstractXmlReader extends AbstractReader {

  public boolean isValidXML(String fileName) {
    try {
      File xsdFile = new File(getClass().getResource("/cinemas.xsd").toURI());
      Source xmlFile = new StreamSource(new File(fileName));
      SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      Schema schema;
      schema = schemaFactory.newSchema(xsdFile);
      Validator validator = schema.newValidator();
      validator.validate(xmlFile);
      return true;
    } catch (Exception e) {
      return false;
    }

  }
}
