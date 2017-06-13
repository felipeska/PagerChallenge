package com.pager.challenge.domain.interactor;

import android.support.annotation.NonNull;
import com.pager.challenge.domain.TeamMember;
import com.pager.challenge.domain.event.EventListener;
import com.pager.challenge.domain.executor.PostExecutionThread;
import com.pager.challenge.domain.executor.ThreadExecutor;
import com.pager.challenge.domain.repository.TeamRepository;
import io.reactivex.Observable;

import java.util.List;
import javax.inject.Inject;

public class GetTeamMembersUseCase extends BaseUseCase<List<TeamMember>, Void> {

  private final TeamRepository repository;

  @Inject GetTeamMembersUseCase(ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread, TeamRepository repository) {
    super(threadExecutor, postExecutionThread);
    this.repository = repository;
  }

  @Override Observable<List<TeamMember>> buildUseCaseObservable(Void aVoid) {
    return repository.teamMembers();
  }

  public void registerListener(@NonNull final EventListener eventListener) {
    repository.registerListenerEvents(eventListener);
  }

  public void unregisterListener(@NonNull final EventListener eventListener){
    repository.unregisterListenerEvents(eventListener);
  }

  public void closeConnection() {
    repository.close();
  }

  public void updateMemberStatus(String username, String state) {
    repository.sendUpdateStatus(username, state);
  }
}
