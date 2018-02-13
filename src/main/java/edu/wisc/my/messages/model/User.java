package edu.wisc.my.messages.model;

import org.apache.commons.lang.Validate;

import java.util.HashSet;
import java.util.Set;

public class User {

  /**
   * Get the groups of which the user is a member.
   *
   * @return potentially empty Set
   */
  public Set<String> getGroups() {
    return groups;
  }

  /**
   * Set the groups of which the user is a member.
   *
   * @param groups non-null potentially empty Set
   */
  public void setGroups(Set<String> groups) {
    Validate.notNull(groups);
    this.groups = groups;
  }

  /**
   * The groups of which the user is a member.
   */
  private Set<String> groups = new HashSet<>();

}
