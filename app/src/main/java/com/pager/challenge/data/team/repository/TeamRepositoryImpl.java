package com.pager.challenge.data.team.repository;

import android.support.annotation.NonNull;
import com.pager.challenge.data.api.PagerTeamApiClient;
import com.pager.challenge.data.socket.SocketService;
import com.pager.challenge.domain.TeamMember;
import com.pager.challenge.domain.event.EventListener;
import com.pager.challenge.domain.repository.TeamRepository;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class TeamRepositoryImpl implements TeamRepository {

  private final PagerTeamApiClient apiClient;
  private final SocketService socketServiceDelegate;

  @Inject TeamRepositoryImpl(PagerTeamApiClient apiClient, SocketService socketServiceDelegate) {
    this.apiClient = apiClient;
    this.socketServiceDelegate = socketServiceDelegate;
  }

  @Override public Observable<List<TeamMember>> teamMembers() {
    return apiClient.members();
  }

  @Override public void open(@NonNull EventListener eventListener) {
    socketServiceDelegate.open(eventListener);
  }

  @Override public void sendUpdateStatus(String username, String state) {
    socketServiceDelegate.sendUpdateStatus(username, state);
  }

  @Override public void close() {
    socketServiceDelegate.close();
  }
}
