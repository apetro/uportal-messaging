package edu.wisc.my.messages.model;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class AudienceFilterTest {

    @Test
    public void matchesWhenUserIsInAMatchingGroup() {
        AudienceFilter filter = new AudienceFilter();
        filter.addGroupsItem("matchingGroup");
        filter.addGroupsItem("someOtherGroup");

        final User user = new User();
        Set<String> groups = new HashSet<>();
        groups.add("matchingGroup");
        groups.add("unrelatedGroup");
        user.setGroups(groups);

        assertTrue(filter.test(user));
    }

    @Test
    public void doesNotMatchWhenUserIsInNoMatchingGroup() {
        AudienceFilter filter = new AudienceFilter();
        filter.addGroupsItem("someGroup");
        filter.addGroupsItem("someOtherGroup");

        final User user = new User();
        Set<String> groups = new HashSet<>();
        groups.add("notMatchingGroup");
        groups.add("unrelatedGroup");
        user.setGroups(groups);

        assertFalse(filter.test(user));
    }

}
