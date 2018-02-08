package edu.wisc.my.messages.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.*;
/**
 * AudienceFilter
 */

public class AudienceFilter   {
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
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  public boolean matches(User user) {

    Set<String> requireAtLeastOneOfTheseGroups = new HashSet<>();
    requireAtLeastOneOfTheseGroups.addAll(this.groups);

    requireAtLeastOneOfTheseGroups.retainAll(user.getGroups());

    return ( ! requireAtLeastOneOfTheseGroups.isEmpty());
  }
}
