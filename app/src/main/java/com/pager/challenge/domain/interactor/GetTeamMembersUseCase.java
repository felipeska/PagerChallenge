package com.pager.challenge.domain.interactor;

import com.pager.challenge.domain.TeamMember;
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
}
