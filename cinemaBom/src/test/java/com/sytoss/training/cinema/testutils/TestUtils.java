package com.sytoss.training.cinema.testutils;

import static org.junit.Assert.assertArrayEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public final class TestUtils {

  private TestUtils() {

  }

  public static void checkFiles(String fileName1, String fileName2) throws IOException {
    assertArrayEquals(getFileBytes(fileName1), getFileBytes(fileName2));
  }

  private static byte[] getFileBytes(String fileName) throws IOException {
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
      throw new IOException(e);
    } finally {
      fis.close();
      bos.close();
    }
  }

}
