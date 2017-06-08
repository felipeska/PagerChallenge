package com.pager.challenge.domain;

import java.util.List;
import java.util.Objects;

public class TeamMember {

  private final String name;
  private final String avatar;
  private final String username;
  private final String role;
  private final String gender;
  private final List<String> languages;
  private final List<String> tags;
  private final String location;
  private final String status;

  public TeamMember(String name, String avatar, String username, String role, String gender,
      List<String> languages, List<String> tags, String location, String status) {
    this.name = name;
    this.avatar = avatar;
    this.username = username;
    this.role = role;
    this.gender = gender;
    this.languages = languages;
    this.tags = tags;
    this.location = location;
    this.status = status;
  }

  public String getName() {
    return name;
  }

  public String getAvatar() {
    return avatar;
  }

  public String getUsername() {
    return username;
  }

  public String getRole() {
    return role;
  }

  public String getGender() {
    return gender;
  }

  public List<String> getLanguages() {
    return languages;
  }

  public List<String> getTags() {
    return tags;
  }

  public String getLocation() {
    return location;
  }

  public String getStatus() {
    return status;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TeamMember)) return false;
    TeamMember member = (TeamMember) o;
    return Objects.equals(name, member.name) && Objects.equals(avatar, member.avatar)
        && Objects.equals(username, member.username) && Objects.equals(role, member.role) && Objects
        .equals(gender, member.gender) && Objects.equals(languages, member.languages)
        && Objects.equals(tags, member.tags) && Objects.equals(location, member.location);
  }

  @Override public int hashCode() {
    return Objects.hash(name, avatar, username, role, gender, languages, tags, location);
  }
}
