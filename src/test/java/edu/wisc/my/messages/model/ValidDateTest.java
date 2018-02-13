package edu.wisc.my.messages.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

}