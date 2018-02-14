package edu.wisc.my.messages.time;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import org.junit.Test;

public class IsoDateTimeStringAfterPredicateTest {

  IsoDateTimeStringAfterPredicate afterNow =
    new IsoDateTimeStringAfterPredicate(LocalDateTime.now());

  /**
   * Test that null is not after now.
   */
  @Test
  public void nullIsNotAfter() {
    assertFalse(afterNow.test(null));
  }

  /**
   * Test that "" is not after now.
   */
  @Test
  public void emptyIsNotAfter() {
    assertFalse(afterNow.test(""));
  }

  /**
   * Test that whitespace is not after now.
   */
  @Test
  public void whitespaceIsNotAfter() {
    assertFalse(afterNow.test("\t     \t"));
  }

  /**
   * Test that input that cannot be parsed results in RuntimeException.
   */
  @Test(expected = RuntimeException.class)
  public void garbageThrows() {
    afterNow.test("Garbage");
  }

  @Test
  public void uwWasNotIncorporatedAfterNow() {
    assertFalse(afterNow.test("1848-07-26"));
  }

  @Test
  public void uwWasNotIncorporatedAfterNowWhenTimeSpecified() {
    assertFalse(afterNow.test("1848-07-26T13:45:22"));
  }

  @Test
  public void uwTercentenialIsAfterNow() {
    assertTrue(afterNow.test("2148-07-26"));
  }

  @Test
  public void uwTercentenialIsAfterNowWhenTimeSpecified() {
    assertTrue(afterNow.test("2148-07-26T04:17:32"));
  }

}
