package edu.wisc.my.messages.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import org.junit.Test;


public class ValidDateTest {
    
    @Test
    public void filterOutExpiredDates() {
        Message message = new Message();
        String longAgoDate = "1999-12-31";
        message.setExpireDate(longAgoDate);
        assertFalse(message.isValidToday());
    }

    @Test
    public void filterOutNotYetLiveDates() {
        Message message = new Message();
        String futureDate = "2100-12-31";
        message.setGoLiveDate(futureDate);
        assertFalse(message.isValidToday());
    }

    @Test
    public void filterOutImproperDates() {
        Message message = new Message();
        String nonsenseDate = "Not a date";
        message.setGoLiveDate(nonsenseDate);
        assertFalse(message.isValidToday());
    }

    @Test
    public void nullDatesPass() {
        Message message = new Message();
        message.setExpireDate(null);
        message.setGoLiveDate(null);
        assertTrue(message.isValidToday());
    }

    @Test
    public void ignoresEmptyStringDates() {
      Message message = new Message();
      message.setExpireDate("");
      message.setGoLiveDate("");
      assertTrue(message.isValidToday());
    }

  /**
   * Test that messages that expire later today are not considered expired.
   * That is, that expiration supports the THH:MM suffix on ISO date-times.
   */
  @Test
  public void expiringLaterTodayIsNotExpired() throws InterruptedException {

    // assumption: this test will not take more than 2 seconds.

    // this test relies on two seconds from now being in the same date, so that an implementation
    // that respects time will notice that the that timestamp is not yet expired whereas an
    // implementation that only respects dates will consider that timestamp expired.
    //
    // therefore edge case: it is less than four seconds before the date rolls over
    // in this case wait four seconds to escape the edge case

    LocalDateTime now = LocalDateTime.now();

    if (now.getHour() == 23 && now.getSecond() > 56) {
      wait(4000);
    }

    // two seconds from now is within the current date, either because not in the edge case
    // or because waited for the edge case to pass

    LocalDateTime twoSecondsLaterThanNow = LocalDateTime.now().plusSeconds(2);

    Message message = new Message();
    message.setExpireDate(twoSecondsLaterThanNow.toString());
    message.setGoLiveDate("1900-01-01T03:00");
    assertTrue("Should not be expired because two seconds from now is not expired.",
      message.isValidToday());
  }

  @Test
  public void goingLiveLaterTodayIsNotLive() throws InterruptedException {

    // assumption: this test will not take more than 2 seconds.

    // this test relies on two seconds from now being in the same date, so that an implementation
    // that respects time will notice that the that timestamp is not yet expired whereas an
    // implementation that only respects dates will consider that timestamp expired.
    //
    // therefore edge case: it is less than four seconds before the date rolls over
    // in this case wait four seconds to escape the edge case

    LocalDateTime now = LocalDateTime.now();

    if (now.getHour() == 23 && now.getSecond() > 56) {
      wait(4000);
    }

    // two seconds from now is within the current date, either because not in the edge case
    // or because waited for the edge case to pass

    LocalDateTime twoSecondsLaterThanNow = LocalDateTime.now().plusSeconds(2);

    Message message = new Message();
    message.setGoLiveDate(twoSecondsLaterThanNow.toString());
    assertFalse("Should not be valid because two seconds from now is not gone live.",
      message.isValidToday());

  }
}
