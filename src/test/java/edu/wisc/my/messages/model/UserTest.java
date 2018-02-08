package edu.wisc.my.messages.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test(expected = IllegalArgumentException.class)
    public void cannotSetGroupsToNull() {
        User user = new User();
        user.setGroups(null);
    }

}