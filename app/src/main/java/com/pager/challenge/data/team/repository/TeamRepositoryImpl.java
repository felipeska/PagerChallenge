package com.pager.challenge.data.team.repository;

import com.pager.challenge.data.api.PagerTeamApiClient;
import com.pager.challenge.domain.TeamMember;
import com.pager.challenge.domain.repository.TeamRepository;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class TeamRepositoryImpl implements TeamRepository {

  private final PagerTeamApiClient apiClient;

  @Inject TeamRepositoryImpl(PagerTeamApiClient apiClient) {
    this.apiClient = apiClient;
  }

  @Override public Observable<List<TeamMember>> teamMembers() {
    return apiClient.members();
  }
}
