package edu.wisc.my.messages.model;

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang.Validate;

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
