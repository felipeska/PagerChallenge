package com.pager.challenge.data.socket;

import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import com.pager.challenge.data.cache.RolesCache;
import com.pager.challenge.data.model.TeamMemberDataModel;
import com.pager.challenge.domain.TeamMember;
import com.pager.challenge.domain.event.EventListener;
import com.pager.challenge.domain.event.model.Event;
import com.pager.challenge.domain.event.model.NewUserEvent;
import com.pager.challenge.domain.event.model.UserEvent;
import com.pager.challenge.domain.event.model.UpdateStatusEvent;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class SocketServiceImpl implements SocketService {

  private final String socketUrl;
  private final OkHttpClient okHttpClient;
  private final Gson gson;

  private WebSocket webSocket;

  private final RolesCache rolesCache;

  private List<EventListener> listeners;

  public SocketServiceImpl(String socketUrl, OkHttpClient okHttpClient, Gson gson,
      RolesCache rolesCache) {
    this.socketUrl = socketUrl;
    this.okHttpClient = okHttpClient;
    this.gson = gson;
    this.rolesCache = rolesCache;
    this.listeners = new ArrayList<>();
    connectWithSocket();
  }

  private void connectWithSocket() {
    this.webSocket = okHttpClient.newWebSocket(new Request.Builder().url(socketUrl).build(),
        new WebSocketListener() {
          @Override public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            for (EventListener listener : listeners) {
              listener.onEventStart();
            }
          }

          @Override public void onMessage(WebSocket webSocket, String text) {
            super.onMessage(webSocket, text);
            for (EventListener listener : listeners) {
              listener.onNewEvent(eventParser(text));
            }
          }

          @Override public void onClosed(WebSocket webSocket, int code, String reason) {
            super.onClosed(webSocket, code, reason);
          }

          @Override public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            super.onFailure(webSocket, t, response);
            for (EventListener listener : listeners) {
              listener.onEventFailure(t);
            }
            connectWithSocket();
          }
        });
  }

  private Event eventParser(String json) throws JsonSyntaxException {
    Event newEvent = gson.fromJson(json, Event.class);
    switch (newEvent.event) {
      case UpdateStatusEvent.STATE_CHANGE:
        newEvent = gson.fromJson(json, UpdateStatusEvent.class);
        break;
      case UserEvent.USER_NEW:
        UserEvent newUserEvent = gson.fromJson(json, UserEvent.class);
        newEvent = new NewUserEvent(newUserEvent.event, map(newUserEvent.user));
        break;
    }
    return newEvent;
  }

  private TeamMember map(TeamMemberDataModel teamMemberDataModel) {
    String roleName = rolesCache.get(Integer.toString(teamMemberDataModel.role));
    return new TeamMember(teamMemberDataModel.name, teamMemberDataModel.avatar,
        teamMemberDataModel.github, roleName, teamMemberDataModel.gender,
        teamMemberDataModel.languages, teamMemberDataModel.tags, teamMemberDataModel.location, "");
  }

  @Override public void registerListenerEvents(@NonNull EventListener eventListener) {
    this.listeners.add(eventListener);
  }

  @Override public void unregisterListenerEvents(@NonNull EventListener eventListener) {
    this.listeners.remove(eventListener);
  }

  @Override public void sendUpdateStatus(String username, String state) {
    if (webSocket == null) connectWithSocket();
    UpdateStatusEvent updateStatusEvent = new UpdateStatusEvent(username, state);
    String event = gson.toJson(updateStatusEvent);
    webSocket.send(event);
  }

  @Override public void close() {
    if (webSocket == null) return;
    webSocket.close(1000, "Closing socket...");
    listeners.clear();
  }
}
