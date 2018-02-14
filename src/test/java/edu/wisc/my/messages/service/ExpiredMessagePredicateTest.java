package edu.wisc.my.messages.service;

import static org.junit.Assert.*;

import edu.wisc.my.messages.model.Message;
import java.time.LocalDateTime;
import org.junit.Test;

public class ExpiredMessagePredicateTest {

  ExpiredMessagePredicate predicate = new ExpiredMessagePredicate(LocalDateTime.now());

  @Test(expected = RuntimeException.class)
  public void throwsOnNull() {
    predicate.test(null);
  }

  @Test
  public void messageWithGarbageExpirationDateIsConsideredExpired() {
    Message brokenMessage = new Message();
    brokenMessage.setExpireDate("Garbage");
    assertTrue(predicate.test(brokenMessage));
  }

  @Test
  public void expiredMessageIsExpired() {
    Message expiredMessage = new Message();
    expiredMessage.setExpireDate("2010-01-01");
    assertTrue(predicate.test(expiredMessage));
  }

  @Test
  public void preciselyExpiredMessageIsExpired() {
    Message preciselyExpiredMessage = new Message();
    preciselyExpiredMessage.setExpireDate("2001-04-12T17:35:24");
    assertTrue(predicate.test(preciselyExpiredMessage));
  }

  @Test
  public void notYetExpiredMessageIsNotExpired() {
    ExpiredMessagePredicate expiredAsOfMillenium =
      new ExpiredMessagePredicate(LocalDateTime.parse("2000-01-01T00:00:00"));

    Message expiresAfterMillenium = new Message();
    expiresAfterMillenium.setExpireDate("2012-11-29");

    assertFalse(expiredAsOfMillenium.test(expiresAfterMillenium));
  }

  @Test
  public void preciselyNotYetExpiredMessageIsNotExpired() {
    ExpiredMessagePredicate expiredAsOfMillenium =
      new ExpiredMessagePredicate(LocalDateTime.parse("2000-01-01T00:00:00"));

    Message preciselyExpiresAfterMillenium = new Message();
    preciselyExpiresAfterMillenium.setExpireDate("2015-12-22T09:00:00");

    assertFalse(expiredAsOfMillenium.test(preciselyExpiresAfterMillenium));
  }

}
