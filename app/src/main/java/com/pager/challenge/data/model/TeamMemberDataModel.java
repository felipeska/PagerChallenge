package com.pager.challenge.data.model;

import java.util.List;

public class TeamMemberDataModel {

  public final String name;
  public final String github;
  public final String avatar;
  public final String location;
  public final int role;
  public final String gender;
  public final List<String> languages;
  public final List<String> tags;

  public TeamMemberDataModel(String name, String avatar, String github, int role, String gender,
      String location, List<String> languages, List<String> tags) {
    this.name = name;
    this.github = github;
    this.avatar = avatar;
    this.role = role;
    this.gender = gender;
    this.location = location;
    this.languages = languages;
    this.tags = tags;
  }
}
