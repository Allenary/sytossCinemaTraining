package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;


public class PlaceTest {

  static Place place;
  
  @BeforeClass
  public static void initializePlace() {
    place = new Place();
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void  testSetStatusIsNull() {
    place.setStatus(null);
  }
  
   @Test(expected = IllegalArgumentException.class)
    public void  testSetStatusIsEmpty() {
     place.setStatus("");
    }
  
  @Test(expected = IllegalArgumentException.class)
  public void  testSetStatusIncorrect() {
    place.setStatus("skipped");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetNumberIsNegative() {
    place.setNumber(-8);
  }

  @Test
  public void testSetNumberCorrect() {
    int placeNum = 12;
    place.setNumber(placeNum);
    assertEquals(placeNum, place.getNumber());
  }

}
