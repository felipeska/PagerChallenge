package com.pager.challenge.domain.event.model;

public class UpdateStatusEvent extends Event {
  public final String user;
  public final String state;

  public final static String STATE_CHANGE = "state_change";

  public UpdateStatusEvent(String user, String state) {
    this(STATE_CHANGE, user, state);
  }

  public UpdateStatusEvent(String event, String user, String state) {
    super(event);
    this.user = user;
    this.state = state;
  }
}
