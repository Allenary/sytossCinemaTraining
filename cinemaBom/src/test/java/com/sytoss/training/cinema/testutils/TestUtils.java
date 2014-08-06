package com.sytoss.training.cinema.testutils;

import static org.junit.Assert.assertArrayEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestUtils {

  Logger logger = LoggerFactory.getLogger(this.getClass());

  public void checkFiles(String fileName1, String fileName2) throws IOException {
    logger.info("CheckFiles " + fileName1 + " " + fileName2);
    assertArrayEquals(getFileBytes(fileName1), getFileBytes(fileName2));
  }

  private byte[] getFileBytes(String fileName) throws IOException {
    FileInputStream fis = new FileInputStream(fileName);
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    byte[] buff = new byte[1024];
    int i;
    try {
      i = fis.read(buff);
      while (i != -1) {
        bos.write(buff, 0, i);
        i = fis.read(buff);
      }
      bos.flush();
      return bos.toByteArray();
    } catch (IOException e) {
      System.out.print("getFileBytes Error");
      e.printStackTrace();

    } finally {
      fis.close();
      bos.close();
    }
    return null;
  }
}
