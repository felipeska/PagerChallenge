package com.pager.challenge.data.model;

public class TeamMemberDataModel {

  private final String name;
  private final String githubUsername;
  private final String avatarUrl;
  private final String location;

  public TeamMemberDataModel(String name, String githubUsername, String avatarUrl,
      String location) {
    this.name = name;
    this.githubUsername = githubUsername;
    this.avatarUrl = avatarUrl;
    this.location = location;
  }

  public String getGithubUsername() {
    return githubUsername;
  }

  public String getName() {
    return name;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }
}
