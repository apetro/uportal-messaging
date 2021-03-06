package edu.wisc.my.messages.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Predicate over User, answering whether the User is or is not in the Audience. <p> Currently
 * AudienceFilter only knows how to consider whether a User is a member of at least one of the
 * required groups for a message. Conceptually other varieties of AudienceFilters might be
 * possible.
 */
public class AudienceFilter
  implements Predicate<User> {

  @JsonProperty("groups")
  private List<String> groups = new ArrayList<String>();

  public AudienceFilter groups(List<String> groups) {
    this.groups = groups;
    return this;
  }

  public AudienceFilter addGroupsItem(String groupsItem) {
    this.groups.add(groupsItem);
    return this;
  }

  public List<String> getGroups() {
    return groups;
  }

  public void setGroups(List<String> groups) {
    this.groups = groups;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AudienceFilter audienceFilter = (AudienceFilter) o;
    return Objects.equals(this.groups, audienceFilter.groups);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groups);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AudienceFilter {\n");

    sb.append("    groups: ").append(toIndentedString(groups)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first
   * line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  @Override
  public boolean test(User user) {

    Set<String> requireAtLeastOneOfTheseGroups = new HashSet<>();
    requireAtLeastOneOfTheseGroups.addAll(this.groups);

    requireAtLeastOneOfTheseGroups.retainAll(user.getGroups());

    return (!requireAtLeastOneOfTheseGroups.isEmpty());
  }
}
