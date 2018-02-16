package edu.wisc.my.messages.model;

import org.junit.Test;

public class UserTest {

  @Test(expected = IllegalArgumentException.class)
  public void cannotSetGroupsToNull() {
    User user = new User();
    user.setGroups(null);
  }

}
