package com.pager.challenge.domain.event;

import com.pager.challenge.domain.event.model.Event;

public interface EventListener {

  void onEventStart();

  void onEventFailure(Throwable t);

  void onNewEvent(Event event);
}
