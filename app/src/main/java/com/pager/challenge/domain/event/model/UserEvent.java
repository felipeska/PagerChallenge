package com.pager.challenge.domain.event.model;

import com.pager.challenge.data.model.TeamMemberDataModel;

public class UserEvent extends Event {

  public final static String USER_NEW = "user_new";

  public final TeamMemberDataModel user;

  public UserEvent(TeamMemberDataModel user) {
    super(USER_NEW);
    this.user = user;
  }
}
