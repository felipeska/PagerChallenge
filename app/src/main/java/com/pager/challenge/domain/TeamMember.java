package com.pager.challenge.domain;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
import java.util.Objects;

public class TeamMember implements Parcelable {

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

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.name);
    dest.writeString(this.avatar);
    dest.writeString(this.username);
    dest.writeString(this.location);
    dest.writeString(this.role);
    dest.writeString(this.status);
    dest.writeString(this.gender);
    dest.writeStringList(this.languages);
    dest.writeStringList(this.tags);
  }

  public static final Parcelable.Creator<TeamMember> CREATOR =
      new Parcelable.Creator<TeamMember>() {
        public TeamMember createFromParcel(Parcel in) {
          return new TeamMember(in);
        }

        public TeamMember[] newArray(int size) {
          return new TeamMember[size];
        }
      };

  private TeamMember(Parcel in) {
    this.name = in.readString();
    this.avatar = in.readString();
    this.username = in.readString();
    this.location = in.readString();
    this.role = in.readString();
    this.status = in.readString();
    this.gender = in.readString();
    this.languages = in.createStringArrayList();
    this.tags = in.createStringArrayList();
  }
}
