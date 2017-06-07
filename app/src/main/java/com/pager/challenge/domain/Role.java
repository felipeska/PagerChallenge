package com.pager.challenge.domain;

import java.util.Objects;

public class Role {

  private final String id;
  private final String name;

  public Role(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Role)) return false;
    Role role = (Role) o;
    return Objects.equals(id, role.id) && Objects.equals(name, role.name);
  }

  @Override public int hashCode() {
    return Objects.hash(id, name);
  }
}
