package com.pager.challenge.domain.event.model;

import com.pager.challenge.domain.TeamMember;

public class NewUserEvent extends Event {

  public final TeamMember teamMember;

  public NewUserEvent(String event, TeamMember teamMember) {
    super(event);
    this.teamMember = teamMember;
  }
}
