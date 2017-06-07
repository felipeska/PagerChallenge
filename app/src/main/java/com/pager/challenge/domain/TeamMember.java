package com.pager.challenge.domain;

import java.util.List;
import java.util.Objects;

public class TeamMember {

  private final String name;
  private final String avatar;
  private final String github;
  private final Role role;
  private final String gender;
  private final List<String> languages;
  private final List<String> tags;
  private final String location;

  public TeamMember(String name, String avatar, String github, Role role, String gender,
      List<String> languages, List<String> tags, String location) {
    this.name = name;
    this.avatar = avatar;
    this.github = github;
    this.role = role;
    this.gender = gender;
    this.languages = languages;
    this.tags = tags;
    this.location = location;
  }

  public String getName() {
    return name;
  }

  public String getAvatar() {
    return avatar;
  }

  public String getGithub() {
    return github;
  }

  public Role getRole() {
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

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TeamMember)) return false;
    TeamMember member = (TeamMember) o;
    return Objects.equals(name, member.name) && Objects.equals(avatar, member.avatar)
        && Objects.equals(github, member.github) && Objects.equals(role, member.role)
        && Objects.equals(gender, member.gender) && Objects.equals(languages, member.languages)
        && Objects.equals(tags, member.tags) && Objects.equals(location, member.location);
  }

  @Override public int hashCode() {
    return Objects.hash(name, avatar, github, role, gender, languages, tags, location);
  }
}
